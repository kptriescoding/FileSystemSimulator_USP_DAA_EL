package FileSystemSimulator;

import Database.SqlCommands;
import FileSystem.SuperNode;
import FileSystem.INode;

import JavaFxCustomClasses.DirIcon;
import JavaFxCustomClasses.FileIcon;
import JavaFxCustomClasses.GUIRightClickOptions;
import Models.DirContents;
import Models.Directory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.*;

public class GuiController {
    public Button back;
    public HBox current_directory_path;
    public VBox vBox;
    @FXML
    private TilePane tilePane;


    public GUIRightClickOptions guiRightClickOptions;
    public static boolean isContextMenuOpen;
    public void startScheduler(ContextMenuEvent e) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(!isContextMenuOpen)guiRightClickOptions.show(back,e.getScreenX(),e.getScreenY());
                    isContextMenuOpen=false;
                });
            }
        }, 200);
    }


    public void updateGui() {
        guiRightClickOptions=new GUIRightClickOptions();

        vBox.setOnContextMenuRequested(this::startScheduler);
        if (tilePane.getChildren().size() > 0) {
            tilePane.getChildren().subList(0, tilePane.getChildren().size()).clear();
        }
        SqlCommands sql = new SqlCommands();
        INode iNode = (INode) sql.retrieveObject(FileSystem.superNode.getCurrentNode());
        Directory curDir = (Directory) iNode.getFileReference();
        boolean pathset = false;
        for (DirContents dirContents : curDir.getContents()) {
            if (Objects.equals(dirContents.getName(), ".") || Objects.equals(dirContents.getName(), "..") || Objects.equals(dirContents.getName(), "/")){
                setCurrent_directory_path();
                continue;}
            if (dirContents.getFileType() == 1) {
                setCurrent_directory_path();
                tilePane.getChildren().add(new DirIcon(iNode, dirContents.getName(), dirContents.getInodeNumber(), pathset));
                pathset = true;
            } else {
                tilePane.getChildren().add(new FileIcon(iNode, dirContents.getName(), dirContents.getInodeNumber()));
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
        TerminalController.guiCommands("cd ..");
        setCurrent_directory_path();

    }

    private void setCurrent_directory_path() {
        current_directory_path.getChildren().remove(2, current_directory_path.getChildren().size());
//        "-fx-border-color: #000000; -fx-border-width: 0.3; -fx-background-color: #424648"
        ArrayList<Label> labels = reverseTracePath(FileSystem.superNode);
        for (Label l : labels) current_directory_path.getChildren().add(l);
        current_directory_path.getChildren().forEach(label->{
            label.setOnMouseClicked(event -> {
                if(current_directory_path.getChildren().indexOf(label)>0){
                    TerminalController.guiCommands(backSlashCount(current_directory_path.getChildren().size()-current_directory_path.getChildren().indexOf(label)-1));
                    setCurrent_directory_path();
                }
            });
        });

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

    private String backSlashCount(int no){
        StringBuilder sb = new StringBuilder();
        sb.append("cd ");
        for(int i = 0;i<no;i++) sb.append("../");
        return sb.substring(0,sb.toString().length()-1);

    }


}
