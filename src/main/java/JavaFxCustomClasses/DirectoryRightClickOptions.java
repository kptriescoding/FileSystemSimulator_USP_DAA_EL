package JavaFxCustomClasses;

import FileSystemSimulator.TerminalController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class DirectoryRightClickOptions extends ContextMenu {
    int inodeNumber;
    String name;
    DirectoryRightClickOptions(String name, int inodeNumber){
        this.name=name;
        this.inodeNumber=inodeNumber;
        MenuItem open=new MenuItem("Open " +name);
        MenuItem delete=new MenuItem("Delete "+name);
        open.setOnAction((ae)->{
            TerminalController.guiCommands("cd "+name);
        });
        delete.setOnAction((ae)->{
            TerminalController.guiCommands("rmdir "+name);
        });
        this.getItems().addAll(open,delete);
    }
}
