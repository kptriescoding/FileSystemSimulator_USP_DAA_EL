package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import Models.File;

public class SaveTextFile {
    public void execute(int inodeNumber,String content){
        SqlCommands sql=new SqlCommands();
        INode inode= (INode) sql.retrieveObject(inodeNumber);
        File f=(File) inode.getFileReference();
        f.setContents(content);
        inode.setFileReference(f);
        sql.UpdateObject(inode,inodeNumber);
    }
}
