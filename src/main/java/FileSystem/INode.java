package FileSystem;

import java.util.Date;

public class INode {
    int iNodeNumber,fileSize,mode,permision,links;
    Date created,modified,accesssed;
    int fileType;
    String owner;
}
