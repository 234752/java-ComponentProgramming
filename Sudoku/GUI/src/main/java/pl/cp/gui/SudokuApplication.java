package pl.cp.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

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
    }

    public static void main(String[] args) {
        launch();
    }
}