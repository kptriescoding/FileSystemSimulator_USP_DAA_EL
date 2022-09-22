package com.example.file_system_simulator;

import Database.SqlCommands;
import FileSystem.SuperNode;
import FileSystem.INode;

import JavaFxCustomClasses.DirIcon;
import JavaFxCustomClasses.FileIcon;
import Models.DirContents;
import Models.Directory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class GuiController {
    public Button back;
    public HBox current_directory_path;
    @FXML
    private GridPane gridPane;

    public void updateGui() {
        if (gridPane.getChildren().size() > 0) {
            gridPane.getChildren().subList(0, gridPane.getChildren().size()).clear();
        }
        SqlCommands sql = new SqlCommands();
        INode iNode = (INode) sql.retrieveObject(FileSystem.superNode.getCurrentNode());
        Directory curDir = (Directory) iNode.getFileReference();
        int i = 0, j = 0;
        boolean pathset = false;
        for (DirContents dirContents : curDir.getContents()) {
            if (Objects.equals(dirContents.getName(), ".") || Objects.equals(dirContents.getName(), "..") || Objects.equals(dirContents.getName(), "/"))
                continue;
            if (dirContents.getFileType() == 1) {
                setCurrent_directory_path();
                gridPane.add(new DirIcon(iNode, dirContents.getName(), dirContents.getInodeNumber(), pathset), j, i);
                pathset = true;
            } else {
                gridPane.add(new FileIcon(iNode, dirContents.getName(), dirContents.getInodeNumber()), j, i);
            }
            j++;
            if (j == 10) {
                i++;
                j = 0;
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

        setCurrent_directory_path();


//TODO go to previous directory if possible and fetch the path till now one by one and send it

    }

    private void setCurrent_directory_path() {
        current_directory_path.getChildren().remove(2, current_directory_path.getChildren().size());
//        "-fx-border-color: #000000; -fx-border-width: 0.3; -fx-background-color: #424648"
        ArrayList<Label> labels = reverseTracePath(FileSystem.superNode);
        for (Label l : labels) current_directory_path.getChildren().add(l);
    }

    private ArrayList<Label> reverseTracePath(SuperNode superNode) {
        ArrayList<Label> ret = new ArrayList<>();
        if (superNode.getCurrentNode() == 2) {
            ret.add(new Label("/"));
            return ret;
        }
        SqlCommands sql = new SqlCommands();
        int currentNode = superNode.getCurrentNode();
        INode iNode;
        Directory dir;

        boolean rootEncountered = false;
        while (!rootEncountered) {
            try {
                iNode = (INode) sql.retrieveObject(currentNode);
            } catch (Exception e) {
                Collections.reverse(ret);
                return ret;
            }
            dir = (Directory) iNode.getFileReference();
            ret.add(createDirLabel(dir.getName()));
//            s.insert(0, "/" + dir.getName());
            currentNode = dir.getContents().get(1).getInodeNumber();
            if (currentNode == 2) {
//                s.append("/");
                rootEncountered = true;
            }
        }
        Collections.reverse(ret);
        return ret;
    }

    private Label createDirLabel(String dirname) {
        //        "-fx-border-color: #000000; -fx-border-width: 0.3; -fx-background-color: #424648"
        Label label = new Label(dirname);
        label.setStyle("-fx-border-color: #000000; -fx-border-width: 0.3; -fx-background-color: #424648");
        label.setTextFill(Color.WHITE);

        label.setFont(Font.font("ubuntu", FontWeight.BOLD, 14));
        label.setPadding(new Insets(5, 10, 5, 10));
        label.alignmentProperty().set(Pos.CENTER);
        return label;
    }


}
