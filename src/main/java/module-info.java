module com.example.file_system_simulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.file_system_simulator to javafx.fxml;
    exports com.example.file_system_simulator;
}