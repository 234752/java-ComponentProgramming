module GUI {
    requires Model;
    requires javafx.controls;
    requires javafx.fxml;
    requires slf4j.api;

    exports pl.cp.difficulty;
    exports pl.cp.gui;
}