package com.example.file_system_simulator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class TerminalController {

    @FXML
    private TextField textField;
    private static TextField anotherTextField;
    @FXML
    private VBox vBox;
    @FXML
    public void initialize(){
        anotherTextField=textField;
    }

    @FXML
    protected void onTextClick(){
        textField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                String command = textField.getText();
                if(Objects.equals(command, "clear")){
                    clearTerminal();
                    return;
                }
                if(Objects.equals(command,"gedit")){

                }
                vBox.getChildren().add(vBox.getChildren().size()-1,new Text("kptries@kptries-IdeaPad-5-15ITL05-Ua:"+command));
                String s="\n"+FileSystem.commands.run(command,FileSystem.superNode);
                vBox.getChildren().add(vBox.getChildren().size()-1,new Text(s));
                GuiController controller = FileSystem.fxmlLoaderGUI.getController();
                controller.updateGui();
                textField.setText("");
            }
        });

    }

    public static void guiCommands(String command){
        anotherTextField.setText(command);
        KeyEvent enterEvent=new KeyEvent(null,anotherTextField,KeyEvent.KEY_PRESSED,"ENTER","ENTER",KeyCode.ENTER,false,false,false,false);
        anotherTextField.fireEvent(enterEvent);

    }
    public void clearTerminal(){
        vBox.getChildren().removeAll();
    }
}