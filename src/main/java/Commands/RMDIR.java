package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;
import java.util.Objects;

public class RMDIR extends RecursiveSearch {


    public String execute(SuperNode superNode, String name) {
        RM rm=new RM();
        RMDIR rmdir=new RMDIR();
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
                INode diriNode=(INode)sql.retrieveObject(dirContents.getInodeNumber());
                diriNode.setLinkNumber(iNode.getLinkNumber()-1);
                sql.UpdateObject(iNode,inodeNumber);
                if(iNode.getiNodeNumber()==0) sql.removeObject(dirContents.getInodeNumber());
                d.removeContent(dirContents);
                inode.setFileReference(d);
                sql.UpdateObject(inode,inodeNumber);
                return "Directory deleted successfully";
            }
        return "Directory doesn't exist";
    }
}
