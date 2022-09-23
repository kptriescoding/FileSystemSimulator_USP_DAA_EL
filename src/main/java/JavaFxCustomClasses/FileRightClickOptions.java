package JavaFxCustomClasses;

import FileSystemSimulator.FileSystem;
import FileSystemSimulator.TerminalController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class FileRightClickOptions extends ContextMenu {
    int inodeNumber;
    String name;
    FileRightClickOptions(String name, int inodeNumber){
        this.name=name;
        this.inodeNumber=inodeNumber;
        MenuItem open=new MenuItem("Open " +name);
        MenuItem delete=new MenuItem("Delete "+name);
        open.setOnAction((ae)->{
            FileSystem.newTextEditor(inodeNumber,name);
        });
        delete.setOnAction((ae)->{
            TerminalController.guiCommands("rm "+name);
        });
        this.getItems().addAll(open,delete);
    }
}
