package com.example.file_system_simulator;

import Commands.Commands;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import FileSystem.SuperNode;

public class FileSystem extends Application {
    public static SuperNode superNode;
    public static Commands commands;
    public static FXMLLoader fxmlLoaderTerminal;
    public static FXMLLoader fxmlLoaderGUI;
    @Override
    public void start(Stage stage) throws IOException {
        superNode=new SuperNode();
        commands=new Commands();
        Stage terminal=new Stage();
        fxmlLoaderTerminal = new FXMLLoader(FileSystem.class.getResource("terminal.fxml"));
        Scene sceneTerminal = new Scene(fxmlLoaderTerminal.load(), 600, 600);
        terminal.setTitle("Terminal");
        terminal.setScene(sceneTerminal);
        terminal.show();
        Stage gui=new Stage();
        fxmlLoaderGUI = new FXMLLoader(FileSystem.class.getResource("gui.fxml"));
        Scene sceneGUI = new Scene(fxmlLoaderGUI.load(), 600, 600);
        gui.setTitle("GUI");
        gui.setScene(sceneGUI);
        gui.show();
        GuiController controller=fxmlLoaderGUI.getController();
        controller.updateGui();
    }

    public static void main(String[] args) {
        launch();
    }
}