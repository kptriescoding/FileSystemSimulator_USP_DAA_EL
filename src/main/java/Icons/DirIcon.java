package Icons;

import FileSystem.INode;
import Models.Directory;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;

public class DirIcon extends Button {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";
    private INode curINode;
    public DirIcon(INode curInode,String name) {
        super(name);
        this.curINode=curInode;
        ImageView image = new ImageView("https://previews.123rf.com/images/jovanas/jovanas1810/jovanas181001136/110959550-file-icon-folder-icon.jpg");
        image.setFitHeight(100);
        image.setPreserveRatio(true);
        setGraphic(image);
        setStyle(STYLE_NORMAL);

        setOnMousePressed(event -> setStyle(STYLE_PRESSED));
        setOnMouseReleased(event -> setStyle(STYLE_NORMAL));
        setContentDisplay(ContentDisplay.TOP);
    }

}