package JavaFxCustomClasses;

import Database.SqlCommands;
import FileSystem.INode;
import Models.Directory;
import com.example.file_system_simulator.TerminalController;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DirIcon extends Button {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";
    SqlCommands sql = new SqlCommands();
    private INode parINode;
    private int inodeNumber;

    public DirIcon(INode parInode, String name, int inodeNumber,boolean setbit) {
        super(name);
        this.inodeNumber = inodeNumber;
        this.parINode = parInode;




        ImageView image = new ImageView("https://previews.123rf.com/images/jovanas/jovanas1810/jovanas181001136/110959550-file-icon-folder-icon.jpg");
        image.setFitHeight(100);
        image.setPreserveRatio(true);
        setGraphic(image);
        setStyle(STYLE_NORMAL);
        this.setContextMenu(new DirectoryRightClickOptions(name, inodeNumber));
        setOnMousePressed(event -> setStyle(STYLE_PRESSED));
        setOnMouseReleased(event -> setStyle(STYLE_NORMAL));
        setContentDisplay(ContentDisplay.TOP);
        setOnAction((ae) -> {
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
                    if(!Objects.equals(d.getName(), "")) sb.append("/");
                } catch (Exception e) {
                    break;
                }
            }
            System.out.println(sb.reverse().toString());
        });
    }

}