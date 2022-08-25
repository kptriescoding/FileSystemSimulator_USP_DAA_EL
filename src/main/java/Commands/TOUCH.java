package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;
import Models.File;

import java.util.ArrayList;
import java.util.Objects;

public class TOUCH {
    private boolean validate(ArrayList<DirContents> dirContents, String dirname){
        for(DirContents dirContent:dirContents) {
            if(Objects.equals(dirContent.getName(), dirname))
                return false;
        }
        return true;
    }
    public String execute(SuperNode superNode, int parentInodeNumber, String name){
        SqlCommands sql=new SqlCommands();
        INode inode= (INode) sql.retrieveObject(parentInodeNumber);
        Directory d=(Directory)inode.getFileReference();
        if(validate(d.getContents(),name)) {
            File f= new File(superNode);
            INode iNode = new INode(superNode, 0,f);
            DirContents content = new DirContents();
            content.setName(name);
            content.setInodeNumber(iNode.getiNodeNumber());
            content.setFileType(0);
            d.addContents(content);
            inode.setFileReference(d);
            sql.UpdateObject(inode,parentInodeNumber);
            return "File Added Successfully";
        }
        else{
            return "Directory/File Already exists";
        }

    }
}
