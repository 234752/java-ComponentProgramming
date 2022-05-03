package pl.cp.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pl.cp.solver.BacktrackingSudokuSolver;
import javafx.stage.Stage;
import pl.cp.model.SudokuBoard;

public class SudokuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
        ChoiceBox pog = (ChoiceBox) scene.lookup("#pog");
        pog.setItems(FXCollections.observableArrayList("Small Pog","Medium Pog","Big Pog"));
        TextField fields[][] = new TextField[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fields[x][y] = (TextField) scene.lookup("#tf"+Integer.toString(x)+Integer.toString(y));
            }
        }
        SudokuBoard mainBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        mainBoard.solveGame();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fields[x][y].setText(Integer.toString(mainBoard.get(x, y)));
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}