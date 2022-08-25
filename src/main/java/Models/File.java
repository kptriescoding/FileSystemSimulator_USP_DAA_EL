package Models;

import java.io.Serializable;

public class File implements Serializable {
    private int parentINodeNumber;
    private String contents;

    public int getParentINodeNumber() {
        return parentINodeNumber;
    }

    public void setParentINodeNumber(int parentINodeNumber) {
        this.parentINodeNumber = parentINodeNumber;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
