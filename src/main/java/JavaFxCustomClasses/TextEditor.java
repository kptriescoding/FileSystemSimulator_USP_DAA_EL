package JavaFxCustomClasses;

import Database.SqlCommands;
import Models.File;
import com.example.file_system_simulator.FileSystem;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import FileSystem.*;

public class TextEditor extends AnchorPane {
    private int fileInodeNumber;
    private String fileName;
    public TextEditor(int fileInodeNumber, String fileName){
        this.fileInodeNumber=fileInodeNumber;
        this.fileName=fileName;
        Button saveButton=new Button("Save");
        SqlCommands sql =new SqlCommands();
        INode inode=(INode)sql.retrieveObject(fileInodeNumber);
        File f=(File)inode.getFileReference();
        TextArea textArea=new TextArea();
        textArea.setText(f.getContents());
        saveButton.setOnAction((ae)->{
            FileSystem.commands.saveTextFile(fileInodeNumber,textArea.getText());
        });
        textArea.setPrefColumnCount(20);
        textArea.setPrefRowCount(20);
        this.getChildren().addAll(textArea,saveButton);
    }
}
