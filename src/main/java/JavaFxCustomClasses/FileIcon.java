package JavaFxCustomClasses;

import FileSystem.INode;
<<<<<<< HEAD
import com.example.file_system_simulator.FileSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
=======
import FileSystemSimulator.FileSystem;
>>>>>>> 30bc80bdc3fbd92fbeaee0be4de43be60c761c77
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FileIcon extends Button {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";
    private INode parINode;
    private int inodeNumber;
    TextField tf = new TextField();

    public FileIcon(INode parINode, String name, int inodeNumber) {
        super(name);
        setText(name);
        setOnMouseClicked(e -> {

        });
        tf.setOnAction(ae -> {
//              if (validateText(tf.getText())) {// this is where you would validate the text
            setText(tf.getText());
//            setGraphic(null);
//            }
        });
        this.parINode=parINode;
        this.inodeNumber=inodeNumber;
        ImageView image = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVENxI54U9ljaiM6bCAEWA4iq072kE5K4H9IU2Ach4uQ&s");
        image.setFitHeight(90);
        image.setPreserveRatio(true);
        setGraphic(image);
        setStyle(STYLE_NORMAL);
        setOnMousePressed(event -> setStyle(STYLE_PRESSED));
        setOnMouseReleased(event -> setStyle(STYLE_NORMAL));
        setContentDisplay(ContentDisplay.TOP);
//        this.setOnAction((ae)->{
//
//
//        });
        this.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                FileSystem.newTextEditor(inodeNumber,name);
            }
            else {
                tf.setText(getText());
                setText("");
                setGraphic(tf);
            }
        });
        this.setContextMenu(new FileRightClickOptions(name, inodeNumber));
    }

}