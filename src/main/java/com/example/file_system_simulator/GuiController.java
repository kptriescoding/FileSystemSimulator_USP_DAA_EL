package com.example.file_system_simulator;

import Database.SqlCommands;
import FileSystem.INode;
import Icons.DirIcon;
import Icons.FileIcon;
import Models.DirContents;
import Models.Directory;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class GuiController {
    @FXML
    private GridPane gridPane;
    public void updateGui(){
        gridPane.getChildren().removeAll();
    SqlCommands sql=new SqlCommands();
    INode iNode=(INode) sql.retrieveObject(FileSystem.superNode.getCurrentNode());
    Directory curDir= (Directory) iNode.getFileReference();
    int i=0,j=0;
    for(DirContents dirContents:curDir.getContents()){
        if(Objects.equals(dirContents.getName(), ".") || Objects.equals(dirContents.getName(), ".."))continue;
        if(dirContents.getFileType()==1){
            gridPane.add(new DirIcon(iNode,dirContents.getName()),j,i);
        }
        else{
            gridPane.add(new FileIcon(iNode, dirContents.getName()),j,i);
        }
        j++;
        if(j==10){
            i++;
            j=0;
        }
    }
}
}
