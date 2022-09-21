package com.example.file_system_simulator;


import Database.SqlCommands;
import Models.Directory;

import FileSystem.SuperNode;
import FileSystem.INode;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Objects;
import java.util.Stack;

public class TerminalController {

    public Label present_directory_path;
    public Label system_name;
    @FXML
    private TextField textField;
    private static TextField anotherTextField;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private HBox commands_till_now;

    @FXML
    public void initialize() {
        anotherTextField = textField;
        textField.setAlignment(Pos.BASELINE_LEFT);
    }

    public static Stack<String> previousCommands = new Stack<>();
    static int top = 0;
    @FXML
    protected void onTextClick() {
        textField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String command = textField.getText();
                if (Objects.equals(command, "clear")) {
                    clearTerminal();
                    return;
                }
                if (Objects.equals(command, "gedit")) {

                }
                System.out.println(textField.getText());
                previousCommands.push(command);
                top = previousCommands.size()-1;
//                vBox.getChildren().add(vBox.getChildren().size() - 1, new Text("kptries@kptries-IdeaPad-5-15ITL05-Ua:" + command));

                vBox.getChildren().add(vBox.getChildren().size() - 1, getTextForPreviousCommand(command));

                String s = "\n" + FileSystem.commands.run(command, FileSystem.superNode);
                String path = reverseTracePath(FileSystem.superNode);
                present_directory_path.setText(path);
                Text nextText = new Text(s);
                nextText.setFill(Color.rgb(244, 244, 244));
                nextText.setFont(Font.font("ubuntu", FontWeight.BOLD, 14));
                vBox.getChildren().add(vBox.getChildren().size() - 1, nextText);
                GuiController controller = FileSystem.fxmlLoaderGUI.getController();
                controller.updateGui();
                textField.setText("");
            }
            if(event.getCode().equals(KeyCode.UP)){

                if(top>=0 && top<previousCommands.size())
                textField.setText(previousCommands.get(top--));
                if(top>previousCommands.size()-1)top = previousCommands.size()-1;
                textField.positionCaret(textField.getText().toString().length());
            }
            if(event.getCode().equals(KeyCode.DOWN)){

                if(top<previousCommands.size() && top>=0) textField.setText(previousCommands.get(top++));
                if(top<0){ top = 0;}
//                if(top>previousCommands.size()-1)top = previousCommands.size()-1;
            }
        });




    }

    private HBox getTextForPreviousCommand(String cmd) {
        HBox ret = new HBox();

        Text t = new Text();
        t.setText("dileep@dileep-HP-Laptop-14s-dr1xxx:~$ ");
        t.setStyle("-fx-font-family: ubuntu");
        t.setFill(Color.rgb(76, 153, 6));
        t.setFont(Font.font("ubuntu", FontWeight.BOLD, 14));
        Text t2 = new Text();
        String path = reverseTracePath(FileSystem.superNode);
        t2.setText(path + "\n");
        t2.setStyle("-fx-text-fill: #3465a3;");
        t2.setFill(Color.rgb(52, 99, 163));
        t2.setFont(Font.font("ubuntu", FontWeight.BOLD, 14));
        Text t3 = new Text(cmd);
        t3.setFill(Color.rgb(255, 254, 243));
        t3.setText(cmd);
        t3.setFont(Font.font("ubuntu", FontWeight.BOLD, 14));

        ret.getChildren().addAll(t, t2, t3);

        HBox.setHgrow(ret.getChildren().get(2), Priority.ALWAYS);

        ret.setBackground(Background.fill(Color.rgb(56, 12, 42)));
        return ret;

    }


    public static void guiCommands(String command) {
        anotherTextField.setText(command);
        KeyEvent enterEvent = new KeyEvent(null, anotherTextField, KeyEvent.KEY_PRESSED, "ENTER", "ENTER", KeyCode.ENTER, false, false, false, false);
        anotherTextField.fireEvent(enterEvent);

    }

    private String reverseTracePath(SuperNode superNode) {

        SqlCommands sql = new SqlCommands();
        int currentNode = superNode.getCurrentNode();
        INode iNode;
        Directory dir;
        StringBuilder s = new StringBuilder();
        boolean rootEncountered = false;
        while (!rootEncountered) {
            try {
                iNode = (INode) sql.retrieveObject(currentNode);
            } catch (Exception e) {
                return s.toString();
            }
            dir = (Directory) iNode.getFileReference();
            s.insert(0, "/" + dir.getName());
            currentNode = dir.getContents().get(1).getInodeNumber();
            if (currentNode == 2) {
                s.append("/");
                rootEncountered = true;
            }
        }
        return s.toString();
    }

    public void clearTerminal() {
        vBox.getChildren().removeAll();
    }


}