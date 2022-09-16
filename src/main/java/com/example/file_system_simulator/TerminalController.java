package com.example.file_system_simulator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class TerminalController {

    @FXML
    private TextField textField;
    @FXML
    private VBox vBox;

    @FXML
    protected void onTextClick(){
        textField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                String command = textField.getText();
                if(Objects.equals(command, "clear")){
                    clearTerminal();
                    return;
                }
                vBox.getChildren().add(vBox.getChildren().size()-1,new Text("kptries@kptries-IdeaPad-5-15ITL05-Ua:"+command));
                String s="\n"+FileSystem.commands.run(command,FileSystem.superNode);
                vBox.getChildren().add(vBox.getChildren().size()-1,new Text(s));
                GuiController controller = FileSystem.fxmlLoaderGUI.getController();
                controller.updateGui();
            }
        });
    }
    public void clearTerminal(){
        vBox.getChildren().removeAll();
    }
}