package pl.cp.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.cp.difficulty.Difficulty;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;



public class SudokuApplication extends Application {

    private Button startButton;
    private ChoiceBox difficultyChoice;
    private SudokuBoard mainBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private TextField[][] fields = new TextField[9][9];

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
        startButton = (Button) scene.lookup("#b1");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearBoard();
                mainBoard.solveGame();
                Difficulty difficulty = fetchDifficulty();
                difficulty.removeFields(mainBoard);
                printBoard();
            }
        });

        //difficulty box
        difficultyChoice = (ChoiceBox) scene.lookup("#cb1");
        difficultyChoice.setItems(FXCollections.observableArrayList("Easy","Medium","Hard"));
        difficultyChoice.getSelectionModel().select(0);

        //fields
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fields[x][y] = (TextField) scene.lookup("#tf" + Integer.toString(x) + Integer.toString(y));
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

    private void printBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (mainBoard.get(x, y) != 0) {
                    fields[x][y].setText(Integer.toString(mainBoard.get(x, y)));
                } else {
                    fields[x][y].setText("");
                }
            }
        }
    }

    private void clearBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                mainBoard.set(x, y, 0);
            }
        }
    }
}