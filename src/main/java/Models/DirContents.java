package Models;

import java.io.Serializable;

public class DirContents implements Serializable {
    private int inodeNumber;
    private int fileType;
    private String name;

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

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
