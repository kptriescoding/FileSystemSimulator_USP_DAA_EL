package JavaFxCustomClasses;

import Database.SqlCommands;
import Models.File;
import FileSystemSimulator.FileSystem;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import FileSystem.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TextEditor extends VBox {
    private int fileInodeNumber;
    private String fileName;

    public TextEditor(int fileInodeNumber, String fileName) {
        this.fileInodeNumber = fileInodeNumber;
        this.fileName = fileName;


        SqlCommands sql = new SqlCommands();
        INode inode = (INode) sql.retrieveObject(fileInodeNumber);
        File f = (File) inode.getFileReference();
        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #3e3e3e;");
        hBox.setMinHeight(35);
        hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #5d5d5d;");
        saveButton.setPadding(new Insets(5, 9, 5, 9));
        hBox.getChildren().add(saveButton);
        saveButton.setFont(Font.font("ubuntu", FontWeight.BOLD, 15));
        saveButton.setTextFill(Color.rgb(255,255,255));
        HBox.setMargin(saveButton, new Insets(4, 3, 4, 30));
        hBox.alignmentProperty().set(Pos.CENTER_LEFT);
        saveButton.setOnMousePressed(event -> {saveButton.setStyle("-fx-background-color: #2f2f2f;");});
        saveButton.setOnMouseReleased(event -> {saveButton.setStyle("-fx-background-color: #5d5d5d;");});

        TextArea textArea = new TextArea();
        textArea.setFont(Font.font("ubuntu", FontWeight.BOLD, 14));
        ;
//        textArea.setBackground(Background.fill(Color.rgb(0,27,51)));
        textArea.setStyle("-fx-control-inner-background:#001b33; -fx-background-color: #001b33;");

        textArea.setText(f.getContents());
        saveButton.setOnAction((ae) -> {
            FileSystem.commands.saveTextFile(fileInodeNumber, textArea.getText());
        });
        textArea.setPrefColumnCount(20);
        textArea.setPrefRowCount(20);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        this.setBackground(Background.fill(Color.rgb(0, 27, 51)));
        this.getChildren().addAll(hBox, textArea);
    }
}
