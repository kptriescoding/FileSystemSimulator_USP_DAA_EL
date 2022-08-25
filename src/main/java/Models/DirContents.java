package Models;

import java.io.Serializable;

public class DirContents implements Serializable {
    private int inodeNumber;
    private String name;

    public int getInodeNumber() {
        return inodeNumber;
    }

    public void setInodeNumber(int inodeNumber) {
        this.inodeNumber = inodeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
