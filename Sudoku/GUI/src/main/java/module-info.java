module pl.cp.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.cp.gui to javafx.fxml;
    exports pl.cp.gui;
}