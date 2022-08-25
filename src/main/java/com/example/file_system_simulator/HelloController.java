package com.example.file_system_simulator;

import Commands.Commands;
import FileSystem.SuperNode;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HelloController {
    private Commands commands =new Commands();
    private SuperNode superNode=new SuperNode();
    @FXML
    private Label welcomeText;

    @FXML
    private TextField textField;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onTextClick(){
        textField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                String command = textField.getText();
                System.out.println(command);
                commands.run(command,superNode);
            }
        });
    }
}