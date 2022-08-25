package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;

public class LS {
    public void execute(SuperNode superNode,int parentInodeNumber){
        SqlCommands sql=new SqlCommands();
        INode inode= (INode) sql.retrieveObject(parentInodeNumber);
        Directory d=(Directory)inode.getFileReference();
        for(DirContents dirContent:d.getContents()){
            System.out.print(dirContent.getName()+" ");
        }
        System.out.println();
    }
}
