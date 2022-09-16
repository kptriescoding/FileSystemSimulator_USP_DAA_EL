package Models;
import FileSystem.SuperNode;

import java.io.Serializable;

public class File implements Serializable {
    private String content;
    private String name;

    public String toString() {
        return name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File(SuperNode superNode){
        this.content="";
    }

    public String getContents() {
        return content;
    }

    public void setContents(String contents) {
        this.content = contents;
    }
}
