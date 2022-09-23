package JavaFxCustomClasses;

import Database.SqlCommands;
import FileSystem.INode;
import Models.Directory;

import FileSystemSimulator.TerminalController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

import FileSystemSimulator.TerminalController;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.util.Objects;

public class DirIcon extends VBox {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";
    SqlCommands sql = new SqlCommands();
    private INode parINode;
    private int inodeNumber;

    public DirIcon(INode parInode, String name, int inodeNumber, boolean setbit) {

        this.inodeNumber = inodeNumber;
        this.parINode = parInode;
        this.setMinHeight(80);
        this.setMaxWidth(80);
        this.setAlignment(Pos.TOP_CENTER);
        ImageView icon = new ImageView(new File("src/main/java/icons/directory-icon.png").toURI().toString());
        icon.setPreserveRatio(true);
        icon.pickOnBoundsProperty().set(true);
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        icon.setStyle("-fx-border-color: black");

        DirectoryRightClickOptions directoryRightClickOptions = new DirectoryRightClickOptions(name, inodeNumber);


//
//        ImageView image = new ImageView();
//        image.setFitHeight(100);
//        image.setPreserveRatio(true);
//        setGraphic(image);
        setStyle(STYLE_NORMAL);

//        this.getChildren().get(0).setContextMenu(new DirectoryRightClickOptions(name, inodeNumber));
//
//        setOnMousePressed(event -> setStyle(STYLE_PRESSED));
//        setOnMouseReleased(event -> setStyle(STYLE_NORMAL));


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


//        setContentDisplay(ContentDisplay.TOP);
        this.getChildren().get(0).setOnMouseClicked(event -> {

        });
        this.getChildren().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.SECONDARY)) {
                    System.out.println("happening");
                    directoryRightClickOptions.show(icon, event.getScreenX(), event.getScreenY());
                } else if (event.getClickCount() == 2) {
                    TerminalController.guiCommands("cd " + name);
                    INode inode = parInode;
                    Directory d = (Directory) inode.getFileReference();

                    StringBuilder sb = new StringBuilder();
                    while (setbit) {
                        try {
                            if (d.getName().equals("/")) {
                                sb.append("/");
                                break;
                            }

                            inode = (INode) sql.retrieveObject(d.getContents().get(1).getInodeNumber());
                            d = (Directory) inode.getFileReference();
                            sb.append(d.getName());
                            if (!Objects.equals(d.getName(), "")) sb.append("/");
                        } catch (Exception e) {
                            break;
                        }
                    }
                    System.out.println(sb.reverse().toString());
                }
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