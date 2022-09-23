package JavaFxCustomClasses;

import FileSystemSimulator.FileSystem;
import FileSystemSimulator.TerminalController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class GUIRightClickOptions extends ContextMenu {
    int numberOfFiles;
    public GUIRightClickOptions(){
        MenuItem newFileBtn=new MenuItem("Create new file");
        MenuItem newFolderBtn=new MenuItem("Create new folder");
        numberOfFiles=FileSystem.superNode.getNumberofNodes();
        newFileBtn.setOnAction((ae)->{
            TerminalController.guiCommands("touch "+ "file"+numberOfFiles);
            numberOfFiles++;
        });
       newFolderBtn.setOnAction((ae)->{
            TerminalController.guiCommands("mkdir "+"dir"+numberOfFiles);
            numberOfFiles++;
        });
        this.getItems().addAll(newFileBtn,newFolderBtn);
    }
}
