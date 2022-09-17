package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;
import Models.File;

import java.util.Objects;

public class CAT extends RecursiveSearch{
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
                if(dirContents.getFileType()==1)return name+ " is a directory";
                INode iNode=(INode)sql.retrieveObject(dirContents.getInodeNumber());
                File f=(File)iNode.getFileReference();
                return f.getContents();
            }
        return "File doesn't exist";
    }
}
