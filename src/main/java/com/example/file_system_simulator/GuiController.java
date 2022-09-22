package com.example.file_system_simulator;

import Database.SqlCommands;
import FileSystem.SuperNode;
import FileSystem.INode;

import JavaFxCustomClasses.DirIcon;
import JavaFxCustomClasses.FileIcon;
import Models.DirContents;
import Models.Directory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class GuiController {
    public Button back;
    public HBox current_directory_path;
    @FXML
    private GridPane gridPane;
    public void updateGui(){
        if (gridPane.getChildren().size() > 0) {
            gridPane.getChildren().subList(0, gridPane.getChildren().size()).clear();
        }
    SqlCommands sql=new SqlCommands();
    INode iNode=(INode) sql.retrieveObject(FileSystem.superNode.getCurrentNode());
    Directory curDir= (Directory) iNode.getFileReference();
    int i=0,j=0;
    boolean pathset = false;
    for(DirContents dirContents:curDir.getContents()){
        if(Objects.equals(dirContents.getName(), ".") || Objects.equals(dirContents.getName(), "..")||Objects.equals(dirContents.getName(),"/"))continue;
        if(dirContents.getFileType()==1){
            gridPane.add(new DirIcon(iNode,dirContents.getName(),dirContents.getInodeNumber(),pathset),j,i);
            pathset=true;
        }
        else{
            gridPane.add(new FileIcon(iNode, dirContents.getName(),dirContents.getInodeNumber()),j,i);
        }
        j++;
        if(j==10){
            i++;
            j=0;
        }
    }
}

    public void backPressestarted(MouseEvent mouseEvent) {
        back.setStyle("-fx-background-color: #000000;");
    }
    public void backPressedHover(MouseEvent mouseEvent) {
        back.setStyle("-fx-background-color: #2f2f2f;");

    }
    public void backPressed(MouseEvent mouseEvent) {
        back.setStyle("-fx-background-color: #2f2f2f;");

//        "-fx-border-color: #000000; -fx-border-width: 0.3; -fx-background-color: #424648"
//TODO go to previous directory if possible and fetch the path till now one by one and send it


    }


}
