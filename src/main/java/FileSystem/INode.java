package FileSystem;

import java.io.Serializable;
import java.util.Date;

public class INode implements Serializable {
    private int iNodeNumber,fileSize,mode,permision,linkNumber;
    private Date created,modified,accesssed;
    private int fileType;
    private Object fileReference;
    private String owner;
}
