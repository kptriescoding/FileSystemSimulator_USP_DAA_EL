package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;

import java.util.ArrayList;
import java.util.Objects;

public class LS extends RecursiveSearch{
    public String execute(SuperNode superNode,String name){
        SqlCommands sql=new SqlCommands();
        StringBuilder s= new StringBuilder();
        int inodeNumber;
        Directory d=null;
        if(name.equals(".")){
            inodeNumber=superNode.getCurrentNode();
            INode inode = (INode) sql.retrieveObject(inodeNumber);
            d= (Directory) inode.getFileReference();
        }
        else {
            inodeNumber = tracePath(superNode, name);
            String[] dirs = name.split("/");
            if (dirs.length > 0) name = dirs[dirs.length - 1];
            if (inodeNumber == -1 || inodeNumber == -2) {
                return error;
            }
            INode iNode = (INode) sql.retrieveObject(inodeNumber);
            INode inode;
            if (iNode.getFileType() == 1) {
                Directory dir = (Directory) iNode.getFileReference();
                ArrayList<DirContents> dirContents = dir.getContents();
                boolean hasChanged = false;
                for (DirContents dirContent : dirContents) {
                    if (Objects.equals(dirContent.getName(), name)) {
                        inode = (INode) sql.retrieveObject(dirContent.getInodeNumber());
                        if (inode.getFileType() == 0) return name + " is a path";
                        d = (Directory) inode.getFileReference();
                        hasChanged = true;
                        break;
                    }
                }
                if (!hasChanged)
                    return "Directory " + name + " doesn't exist";
            } else {
                return "The Directory" + name + "is a File";
            }
        }
        for(DirContents dirContent:d.getContents()){
            if(Objects.equals(dirContent.getName(), "/"))continue;
            s.append(dirContent.getName()).append(" ");
        }
        s.append("\n");
        return String.valueOf(s);
    }
}
