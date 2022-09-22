package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;
import javafx.scene.Parent;

import java.sql.SQLOutput;
import java.util.Objects;

public class RecursiveSearch {
    public String error;
    public int tracePath(SuperNode superNode, String path) {
        SqlCommands sql = new SqlCommands();
        int currentNode = superNode.getCurrentNode();
        String[] dirs = path.split("/");
        int i=0;
        if (path.charAt(0) == '/')
        {
            System.out.println("Here");
            currentNode = 2;
            i=1;
        }
        INode inode = (INode) sql.retrieveObject(currentNode);
        Directory d = (Directory) inode.getFileReference();
        for (; i < dirs.length-1; i++) {
            assert d != null;
            boolean hasChanged=false;
            for (DirContents dirContent : d.getContents()) {
                System.out.println("Current Dir:"+d.getName()+":"+dirContent.getName());
                if (Objects.equals(dirs[i], dirContent.getName())) {
                    if (dirContent.getFileType() == 0) {
                        error =  dirContent.getName() + " is a file";
                        return -1;
                    }
                    inode = (INode) sql.retrieveObject(dirContent.getInodeNumber());
                    hasChanged = true;
                    d = (Directory) inode.getFileReference();
                    break;
                }
            }
                if(!hasChanged){
                    System.out.println(dirs[i]);
                    error="The directory "+ dirs[i]+" doesn't exist";
                    return -1;
                }
    }
        return inode.getiNodeNumber();
    }
    public String reverseTracePath(SuperNode superNode){
        SqlCommands sql=new SqlCommands();
        int currentNode=superNode.getCurrentNode();
        INode iNode;
        Directory dir;
        String s= "";
        boolean rootEncountered=false;
        while(!rootEncountered){
            iNode= (INode) sql.retrieveObject(currentNode);
            dir=(Directory) iNode.getFileReference();
            s="/"+dir.getName()+s;
            currentNode=dir.getContents().get(1).getInodeNumber();
            if(currentNode==2){
                rootEncountered=true;
            }
        }
        return s;
    }
    public static void main(String[] args){
        RecursiveSearch recursiveSearch=new RecursiveSearch();
//        System.out.println(recursiveSearch.tracePath(new SuperNode(),"dir1/dir2/dir22/dir23"));
        System.out.println(recursiveSearch.reverseTracePath(new SuperNode()));
//        System.out.println(recursiveSearch.error);
    }
}
