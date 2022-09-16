package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;

import java.util.Objects;

public class LS {
    public String execute(SuperNode superNode,int parentInodeNumber){
        SqlCommands sql=new SqlCommands();
        StringBuilder s= new StringBuilder();
        INode inode= (INode) sql.retrieveObject(parentInodeNumber);
        Directory d=(Directory)inode.getFileReference();
        for(DirContents dirContent:d.getContents()){
            if(Objects.equals(dirContent.getName(), "/"))continue;
            s.append(dirContent.getName()).append(" ");
        }
        s.append("\n");
        return String.valueOf(s);
    }
}
