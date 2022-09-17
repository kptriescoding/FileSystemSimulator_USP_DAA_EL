package FileSystem;

import Database.SqlCommands;

import java.io.Serializable;
import java.util.Date;

public class INode implements Serializable {
    public INode(SuperNode  superNode,int fileType,Object fileReference){
        superNode.updateSuperNode();
        this.iNodeNumber=superNode.getNumberofNodes();
        this.fileType=fileType;
        this.fileReference=fileReference;
        this.linkNumber=1;
        SqlCommands sql =new SqlCommands();
        sql.storeObject(this,iNodeNumber);
    }
    private int iNodeNumber,fileSize,mode,permision,linkNumber;
    private Date created,modified,accesssed;
    private int fileType;
    private Object fileReference;
    private String owner;

    public int getiNodeNumber() {
        return iNodeNumber;
    }

    public void setiNodeNumber(int iNodeNumber) {
        this.iNodeNumber = iNodeNumber;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getPermision() {
        return permision;
    }

    public void setPermision(int permision) {
        this.permision = permision;
    }

    public int getLinkNumber() {
        return linkNumber;
    }

    public void setLinkNumber(int linkNumber) {
        this.linkNumber = linkNumber;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getAccesssed() {
        return accesssed;
    }

    public void setAccesssed(Date accesssed) {
        this.accesssed = accesssed;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public Object getFileReference() {
        return fileReference;
    }

    public void setFileReference(Object fileReference) {
        this.fileReference = fileReference;
        SqlCommands sql = new SqlCommands();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
