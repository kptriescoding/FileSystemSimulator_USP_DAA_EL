package Models;

import FileSystem.SuperNode;

import java.io.Serializable;
import java.util.ArrayList;

public class Directory implements Serializable {
    private int parentINodeNumber;
    private  ArrayList<DirContents>contents;
    public Directory(SuperNode superNode,int parentINodeNumber){
        this.parentINodeNumber=parentINodeNumber;
        this.contents=new ArrayList<>();
        DirContents content=new DirContents();
        content.setName(".");
        content.setInodeNumber(superNode.getNumberofNodes()+1);
        this.contents.add(content);
        content.setName("..");
        content.setInodeNumber(parentINodeNumber);
        this.contents.add(content);
    }

    public int getParentINodeNumber() {
        return parentINodeNumber;
    }

    public void setParentINodeNumber(int parentINodeNumber) {
        this.parentINodeNumber = parentINodeNumber;
    }

    public ArrayList<DirContents> getContents() {
        return contents;
    }

    public void addContents(DirContents content) {
        this.contents.add(content);
    }

}
