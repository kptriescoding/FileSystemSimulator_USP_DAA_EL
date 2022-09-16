package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Objects;

public class RMDIR extends RecursiveSearch {


    public String execute(SuperNode superNode, String name) {
        SqlCommands sql = new SqlCommands();
        int inodeNumber = tracePath(superNode, name);
        String[] dirs = name.split("/");
        if (dirs.length > 0) name = dirs[dirs.length - 1];
        if (inodeNumber == -1 || inodeNumber == -2) {
            return error;
        }
        INode inode = (INode) sql.retrieveObject(inodeNumber);
        Directory d = (Directory) inode.getFileReference();
        for (DirContents dirContents : d.getContents())
            if (Objects.equals(dirContents.getName(), name)) {
                if(dirContents.getFileType()==0)return name+ " is a file";
                INode iNode= (INode) sql.retrieveObject(dirContents.getInodeNumber());
                    Directory dir= (Directory) iNode.getFileReference();
                    if(dir.getContents().size()>3)return "Directory "+name+" is not empty";
                sql.removeObject(dirContents.getInodeNumber());
                d.removeContent(dirContents);
                inode.setFileReference(d);
                sql.UpdateObject(inode,inodeNumber);
                return "Directory deleted successfully";
            }
        return "Directory doesn't exist";
    }
}