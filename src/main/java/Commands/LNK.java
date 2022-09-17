package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;

import java.util.ArrayList;
import java.util.Objects;

public class LNK extends RecursiveSearch{
    private boolean validate(ArrayList<DirContents>dirContents,String dirname){
        for(DirContents dirContent:dirContents) {
            if(Objects.equals(dirContent.getName(), dirname))
                return false;
        }
        return true;
    }
    public String execute(SuperNode superNode,String src,String dest){
        SqlCommands sql=new SqlCommands();
        int srcInodeNumber=tracePath(superNode,src);
        int destInodeNumber=tracePath(superNode,dest);
        String[] s1=src.split("/");
        String [] s2=dest.split("/");
        if(s1.length>0)src=s1[s1.length-1];
        if(s2.length>0)dest=s2[s2.length-1];
        if(srcInodeNumber==-1||srcInodeNumber==-2||destInodeNumber==-1||destInodeNumber==-2){
            return error;
        }
        int inodeNumber=-1;
        int fileType=-1;
        INode srciNode= (INode) sql.retrieveObject(srcInodeNumber);
        if(srciNode.getFileType()==1){
            Directory dir= (Directory) srciNode.getFileReference();
            ArrayList<DirContents> dirContents= dir.getContents();
            boolean hasChanged=false;
            for(DirContents dirContent:dirContents){
                if(Objects.equals(dirContent.getName(), src)){
                    inodeNumber= dirContent.getInodeNumber();
                    fileType=dirContent.getFileType();
                    hasChanged=true;
                }
            }
            if(!hasChanged)
                return "Directory "+src+ " doesn't exist";
        }
        else {
            return "The Directory is a File";
        }
        INode destInode= (INode) sql.retrieveObject(destInodeNumber);
        Directory d=(Directory)destInode.getFileReference();
        if(validate(d.getContents(),dest)) {
            DirContents content = new DirContents();
            content.setName(dest);
            content.setInodeNumber(inodeNumber);
           INode iNode=(INode)sql.retrieveObject(inodeNumber);
           iNode.setLinkNumber(iNode.getLinkNumber()+1);
           sql.UpdateObject(iNode,inodeNumber);
            content.setFileType(fileType);
            d.addContents(content);
            System.out.println();
            destInode.setFileReference(d);
            sql.UpdateObject(destInode,destInodeNumber);
            return "Hard link created Successfully";
        }
        else{
            return "Directory/File Already exists";
        }
    }
}
