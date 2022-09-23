package JavaFxCustomClasses;

import FileSystem.INode;
import FileSystemSimulator.FileSystem;
import FileSystemSimulator.TerminalController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import FileSystemSimulator.FileSystem;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;

public class FileIcon extends VBox {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";
    private INode parINode;
    private int inodeNumber;
    TextField tf = new TextField();

    public FileIcon(INode parINode, String name, int inodeNumber) {



        this.inodeNumber = inodeNumber;
        this.parINode = parINode;
        this.setMinHeight(80);
        this.setMaxWidth(80);
        this.setAlignment(Pos.TOP_CENTER);


        ImageView icon = new ImageView(new File("src/main/java/icons/file-icon.png").toURI().toString());
        icon.setPreserveRatio(true);
        icon.pickOnBoundsProperty().set(true);
        icon.setFitHeight(50);
        icon.setFitWidth(50);


        TextField editName = new TextField();
//        editName.disableProperty().set(true);
        editName.setAlignment(Pos.CENTER);
        editName.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-inner-color: white;");
        editName.setText(name);
        editName.setFont(Font.font("ubuntu", FontWeight.BOLD, 14));

        this.getChildren().addAll(icon, editName);
        VBox.setVgrow(icon, Priority.ALWAYS);

        this.getChildren().get(1).setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {

                editName.setText(editName.getText());
                TerminalController.guiCommands("mv" + " " + name + " " + editName.getText());
                editName.disableProperty().set(true);
//
            }
        });

        this.getChildren().get(1).setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                FileSystem.newTextEditor(inodeNumber,name);
            }

        });
        this.getChildren().get(0).onMouseEnteredProperty().set(event -> {
            this.setBackground(Background.fill(Color.GREY));
        });
        this.getChildren().get(0).onMouseExitedProperty().set(event -> {
            this.setBackground(Background.fill(Color.valueOf("#2b2b2b")));
        });


    }

}