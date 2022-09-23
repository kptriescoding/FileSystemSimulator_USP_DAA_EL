module FileSystemSimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens FileSystemSimulator to javafx.fxml;
    exports FileSystemSimulator;
}