package com.example.file_system_simulator;

import Commands.Commands;
import FileSystem.SuperNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class TerminalController {

    @FXML
    private TextField textField;

    @FXML
    protected void onTextClick(){
        textField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                String command = textField.getText();
                System.out.println(command);
                FileSystem.commands.run(command,FileSystem.superNode);
            }
            GuiController controller = FileSystem.fxmlLoaderGUI.getController();
            controller.updateGui();
        });
    }
}