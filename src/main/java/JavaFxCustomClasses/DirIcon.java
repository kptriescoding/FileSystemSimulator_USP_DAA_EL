package JavaFxCustomClasses;

import FileSystem.INode;
import com.example.file_system_simulator.TerminalController;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;

public class DirIcon extends Button {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";
    private INode parINode;
    private int inodeNumber;
    public DirIcon(INode parInode, String name, int inodeNumber) {
        super(name);
        this.inodeNumber=inodeNumber;
        this.parINode=parInode;
        ImageView image = new ImageView("https://previews.123rf.com/images/jovanas/jovanas1810/jovanas181001136/110959550-file-icon-folder-icon.jpg");
        image.setFitHeight(100);
        image.setPreserveRatio(true);
        setGraphic(image);
        setStyle(STYLE_NORMAL);
        this.setContextMenu(new DirectoryRightClickOptions(name,inodeNumber));
        setOnMousePressed(event -> setStyle(STYLE_PRESSED));
        setOnMouseReleased(event -> setStyle(STYLE_NORMAL));
        setContentDisplay(ContentDisplay.TOP);
        setOnAction((ae)-> TerminalController.guiCommands("cd "+name));
    }

}