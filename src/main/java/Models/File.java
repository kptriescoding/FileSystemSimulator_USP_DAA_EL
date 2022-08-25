package Models;
import FileSystem.SuperNode;

import java.io.Serializable;

public class File implements Serializable {
    private String content;
    public File(SuperNode superNode){
        superNode.updateSuperNode();
        this.content="";
    }

    public String getContents() {
        return content;
    }

    public void setContents(String contents) {
        this.content = contents;
    }
}
