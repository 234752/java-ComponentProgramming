package pl.cp.gui;

import java.io.IOException;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import pl.cp.dao.Dao;
import pl.cp.dao.FileSudokuBoardDao;
import pl.cp.dao.SudokuBoardDaoFactory;
import pl.cp.difficulty.Difficulty;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;



public class SudokuApplication extends Application {

    private Button startButton;
    private Button saveButton;
    private Button loadButton;
    private ChoiceBox difficultyChoice;
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
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();

        //setup
        initializeBoardElements(scene);
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeBoardElements(Scene scene) {

        //button
        startButton = (Button) scene.lookup("#startButton");
        startButton.setOnAction(actionEvent -> {
            clearBoard();
            mainBoard.solveGame();
            Difficulty difficulty = fetchDifficulty();
            difficulty.removeFields(mainBoard);
        });
        saveButton = (Button) scene.lookup("#saveButton");
        saveButton.setOnAction(actionEvent -> {
            try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("src/test/java/sb.txt")) {
                dao.write(mainBoard);
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
        saveButton = (Button) scene.lookup("#loadButton");
        saveButton.setOnAction(actionEvent -> {
            try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("src/test/java/sb.txt")) {
                mainBoard = dao.read();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });

        //difficulty box
        difficultyChoice = (ChoiceBox) scene.lookup("#cb1");
        difficultyChoice.setItems(FXCollections.observableArrayList("Easy","Medium","Hard"));
        difficultyChoice.getSelectionModel().select(0);

        //fields
        mainBoard.initializeProperties();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fields[x][y] = (TextField) scene.lookup("#tf" + x + y);
                Bindings.bindBidirectional(fields[x][y].textProperty(), mainBoard.getProperty(x,y));

                //validation of text fields - integer only
                fields[x][y].setTextFormatter(new TextFormatter<String>(new DefaultStringConverter(),"",  integerFilter));
            }
        }
    }

    private Difficulty fetchDifficulty() {
        Difficulty difficulty = Difficulty.EASY; //default is easy
        String level = difficultyChoice.getValue().toString();

        if (level == "Medium") {
            difficulty = Difficulty.MEDIUM;
        } else if (level == "Hard") {
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
}