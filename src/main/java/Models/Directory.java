package Models;

import Database.SqlCommands;
import FileSystem.SuperNode;

import java.io.Serializable;
import java.util.ArrayList;

public class Directory implements Serializable {
    private  ArrayList<DirContents>contents;
    public Directory(SuperNode superNode,int parentINodeNumber){
        superNode.updateSuperNode();
        this.contents=new ArrayList<>();
        DirContents content=new DirContents();
        content.setName(".");
        int inodeNumber=superNode.getNumberofNodes();
        DirContents contents1=new DirContents();
        content.setInodeNumber(inodeNumber);
        content.setFileType(1);
        this.contents.add(content);
        contents1.setName("..");
        contents1.setInodeNumber(parentINodeNumber);
        contents1.setFileType(1);
        this.contents.add(contents1);
    }


    public ArrayList<DirContents> getContents() {
        return contents;
    }

    public void addContents(DirContents content) {
        this.contents.add(content);
    }

}
