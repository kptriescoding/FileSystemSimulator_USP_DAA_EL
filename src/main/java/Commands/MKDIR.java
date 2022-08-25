package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;

import java.util.ArrayList;
import java.util.Objects;

public class MKDIR {
    

    private boolean validate(ArrayList<DirContents>dirContents,String dirname){
        for(DirContents dirContent:dirContents) {
            if(Objects.equals(dirContent.getName(), dirname))
                return false;
        }
        return true;
    }
    public void execute(SuperNode superNode,int parentInodeNumber,String name){
        SqlCommands sql=new SqlCommands();
        INode inode= (INode) sql.retrieveObject(parentInodeNumber);
        Directory d=(Directory)inode.getFileReference();
        if(validate(d.getContents(),name)) {
            Directory dir = new Directory(superNode, parentInodeNumber);
            INode iNode = new INode(superNode, 1, dir);
            DirContents content = new DirContents();
            content.setName(name);
            content.setInodeNumber(iNode.getiNodeNumber());
            d.addContents(content);
            inode.setFileReference(d);
            sql.UpdateObject(inode,parentInodeNumber);
            System.out.println("File Stored Successfully");
        }
        else{
            System.out.println("File Already exists");
        }

    }
    
}
