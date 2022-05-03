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
import pl.cp.solver.BacktrackingSudokuSolver;
import javafx.stage.Stage;
import pl.cp.model.SudokuBoard;

public class SudokuApplication extends Application {

    private Button startButton;
    private ChoiceBox difficultyChoice;
    private SudokuBoard mainBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private TextField fields[][] = new TextField[9][9];

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();

        //setup
        startButton = (Button) scene.lookup("#b1");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearBoard();
                mainBoard.solveGame();
                printBoard();
            }
        });

        difficultyChoice = (ChoiceBox) scene.lookup("#cb1");
        difficultyChoice.setItems(FXCollections.observableArrayList("Easy","Medium","Hard"));

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fields[x][y] = (TextField) scene.lookup("#tf"+Integer.toString(x)+Integer.toString(y));
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void printBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fields[x][y].setText(Integer.toString(mainBoard.get(x, y)));
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