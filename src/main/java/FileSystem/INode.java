package FileSystem;

import java.io.Serializable;
import java.util.Date;

public class INode implements Serializable {
    public int iNodeNumber,fileSize,mode,permision,linkNumber;
    public Date created,modified,accesssed;
    public int fileType;
    public Object fileReference;
    public String owner;
}
