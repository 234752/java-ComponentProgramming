package pl.cp.gui;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cp.dao.JdbcDao;
import pl.cp.dao.SudokuBoardDaoFactory;
import pl.cp.difficulty.Difficulty;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;



public class SudokuApplication extends Application {

    private ResourceBundle resourceBundle;
    private static Logger logger = LoggerFactory.getLogger(SudokuApplication.class.getName());

    private Button startButton;
    private Button saveButton;
    private Button loadButton;
    private Button englishButton;
    private Button polishButton;
    private Button checkButton;
    private Button dropButton;
    private Label checkLabel;
    private ChoiceBox difficultyChoice;
    private ChoiceBox databaseChoiceBox;
    private TextField databaseTextField;
    private SudokuBoard mainBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private TextField[][] fields = new TextField[9][9];

    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("[1-9]") || newText.matches("")) {
            return change;
        }
        return null;
    };

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        resourceBundle = ResourceBundle.getBundle("Language_EN");
        stage.setScene(scene);
        stage.show();

        //setup
        initializeBoardElements(scene);
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeBoardElements(Scene scene) {

        //label with results
        checkLabel = (Label) scene.lookup("#checkLabel");

        //buttons
        startButton = (Button) scene.lookup("#startButton");
        startButton.setOnAction(actionEvent -> {
            clearBoard();
            mainBoard.solveGame();
            Difficulty difficulty = fetchDifficulty();
            difficulty.removeFields(mainBoard);
            blockFields();
        });
        saveButton = (Button) scene.lookup("#saveButton");
        saveButton.setOnAction(actionEvent -> {
            try (JdbcDao dao = SudokuBoardDaoFactory.getJdbcDao()) {
                String name = databaseTextField.getText();
                dao.connect();
                dao.createTables();
                if (!dao.verifyBoardName(name)) {
                    checkLabel.setText(resourceBundle.getString("nameTakenLabel"));
                    return;
                }
                dao.createNewBoard(name);
                dao.selectBoard(name);
                dao.write(mainBoard);
                updateDatabaseChoiceBox();
                checkLabel.setText("");
            } catch (Exception exception) {
                logger.info(exception.getMessage());
            }
        });
        loadButton = (Button) scene.lookup("#loadButton");
        loadButton.setOnAction(actionEvent -> {
            try (JdbcDao dao = SudokuBoardDaoFactory.getJdbcDao()) {
                String name = databaseChoiceBox.getSelectionModel().getSelectedItem().toString();
                dao.connect();
                dao.selectBoard(name);
                mainBoard = dao.read();
                bindBoardProperties();
                updateDatabaseChoiceBox();
            } catch (Exception exception) {
                logger.info(exception.getMessage());
            }
            blockFields();
        });
        englishButton = (Button) scene.lookup("#englishButton");
        englishButton.setOnAction(actionEvent -> {
            resourceBundle = ResourceBundle.getBundle("Language_EN");
            updateLanguage();
        });
        polishButton = (Button) scene.lookup("#polishButton");
        polishButton.setOnAction(actionEvent -> {
            resourceBundle = ResourceBundle.getBundle("Language_PL");
            updateLanguage();
        });
        checkButton = (Button) scene.lookup("#checkButton");
        checkButton.setOnAction(actionEvent -> {
            if (mainBoard.checkBoard()) {
                checkLabel.setText(resourceBundle.getString("resultPositive"));
            } else {
                checkLabel.setText(resourceBundle.getString("resultNegative"));
            }
        });
        dropButton = (Button) scene.lookup("#dropButton");
        dropButton.setOnAction(actionEvent -> {
            try (JdbcDao dao = SudokuBoardDaoFactory.getJdbcDao()) {
                dao.connect();
                dao.nukeDatabase();
                updateDatabaseChoiceBox();
            } catch (Exception exception) {
                logger.info(exception.getMessage());
            }
        });

        //difficulty box
        difficultyChoice = (ChoiceBox) scene.lookup("#cb1");

        //database boards list
        databaseChoiceBox = (ChoiceBox) scene.lookup("#cb2");
        updateDatabaseChoiceBox();

        //fields
        databaseTextField = (TextField) scene.lookup("#tfDatabase");
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fields[x][y] = (TextField) scene.lookup("#tf" + x + y);
                fields[x][y].textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                        checkLabel.setText("");
                    }
                });

                //validation of text fields - integer only
                fields[x][y].setTextFormatter(new TextFormatter<String>(new DefaultStringConverter(),"",  integerFilter));
            }
        }
        bindBoardProperties();
        updateLanguage();
    }

    private void bindBoardProperties() {
        mainBoard.initializeProperties();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Bindings.bindBidirectional(fields[x][y].textProperty(), mainBoard.getProperty(x,y));
            }
        }
    }

    private Difficulty fetchDifficulty() {
        Difficulty difficulty = Difficulty.EASY; //default is easy
        String level = difficultyChoice.getValue().toString();

        if (Objects.equals(level, resourceBundle.getString("difficulty1"))) {
            difficulty = Difficulty.MEDIUM;
        } else if (Objects.equals(level, resourceBundle.getString("difficulty2"))) {
            difficulty = Difficulty.HARD;
        }

        return difficulty;
    }

    private void clearBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                mainBoard.set(x, y, 0);
            }
        }
    }

    private void blockFields() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (mainBoard.isLocked(x, y)) {
                    fields[x][y].setDisable(true);
                } else {
                    fields[x][y].setDisable(false);
                }
            }
        }
    }

    private void updateDatabaseChoiceBox() {
        try (JdbcDao dao = SudokuBoardDaoFactory.getJdbcDao()) {
            dao.connect();
            databaseChoiceBox.setItems(dao.getAllBoardNames());
            databaseChoiceBox.getSelectionModel().select(0);
        } catch (Exception exception) {
            databaseChoiceBox.setItems(FXCollections.observableArrayList());
            logger.info(exception.getMessage());
        }
    }

    private void updateLanguage() {
        startButton.setText(resourceBundle.getString("startButtonLabel"));
        saveButton.setText(resourceBundle.getString("saveButtonLabel"));
        loadButton.setText(resourceBundle.getString("loadButtonLabel"));
        englishButton.setText(resourceBundle.getString("englishButtonLabel"));
        polishButton.setText(resourceBundle.getString("polishButtonLabel"));
        checkButton.setText(resourceBundle.getString("checkButtonLabel"));
        dropButton.setText(resourceBundle.getString("dropButtonLabel"));
        checkLabel.setText("");
        difficultyChoice.setItems(FXCollections.observableArrayList(
                resourceBundle.getString("difficulty0"),
                resourceBundle.getString("difficulty1"),
                resourceBundle.getString("difficulty2")));
        difficultyChoice.getSelectionModel().select(0);
    }
}