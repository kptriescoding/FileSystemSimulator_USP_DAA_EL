package Models;

import Database.SqlCommands;
import FileSystem.SuperNode;

import java.io.Serializable;
import java.util.ArrayList;

public class Directory implements Serializable {
    private  ArrayList<DirContents>contents;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory(SuperNode superNode, int parentINodeNumber){
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
        DirContents contents2=new DirContents();
        contents2.setInodeNumber(2);
        contents2.setFileType(1);
        contents2.setName("/");
        this.contents.add(contents2);
    }


    public ArrayList<DirContents> getContents() {
        return contents;
    }

    public void addContents(DirContents content) {
        this.contents.add(content);
    }
    public void removeContent(DirContents content){
        this.contents.remove(content);
    }

}
