package Icons;

import FileSystem.INode;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class FileIcon extends Button {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";
    private INode curINode;
    public FileIcon(INode curINode,String name) {
        super(name);
        this.curINode=curINode;
        ImageView image = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVENxI54U9ljaiM6bCAEWA4iq072kE5K4H9IU2Ach4uQ&s");
        image.setFitHeight(90);
        image.setPreserveRatio(true);
        setGraphic(image);
        setStyle(STYLE_NORMAL);

        setOnMousePressed(event -> setStyle(STYLE_PRESSED));
        setOnMouseReleased(event -> setStyle(STYLE_NORMAL));
    }

}