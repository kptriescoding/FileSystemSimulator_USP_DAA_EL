package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;
import Models.File;

import java.util.ArrayList;
import java.util.Objects;

public class MV extends RecursiveSearch{
            private boolean validate(ArrayList<DirContents> dirContents, String dirname){
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
                DirContents dirC = null;
                INode srciNode= (INode) sql.retrieveObject(srcInodeNumber);
                Directory srcDir;
                if(srciNode.getFileType()==1){
                    srcDir= (Directory) srciNode.getFileReference();
                    ArrayList<DirContents> dirContents= srcDir.getContents();
                    boolean hasChanged=false;
                    for(DirContents dirContent:dirContents){
                        if(Objects.equals(dirContent.getName(), src)){
                            inodeNumber= dirContent.getInodeNumber();
                            fileType=dirContent.getFileType();
                            dirC=dirContent;
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
                Directory destDir=(Directory)destInode.getFileReference();
                if(validate(destDir.getContents(),dest)) {
                    DirContents content = new DirContents();
                    content.setName(dest);
                    updateContent(inodeNumber,dest,sql);
                    content.setInodeNumber(inodeNumber);
                    content.setFileType(fileType);
                    destDir.addContents(content);
                    destInode.setFileReference(destDir);
                    sql.UpdateObject(destInode,destInodeNumber);
                    if(srcInodeNumber==destInodeNumber){
                        srcDir=destDir;
                        for(DirContents dirCs: srcDir.getContents()){
                            if(Objects.equals(dirCs.getName(),src)){
                                dirC=dirCs;
                                break;
                            }
                        }
                    }
                    srcDir.removeContent(dirC);
                        srciNode.setFileReference(srcDir);
                        sql.UpdateObject(srciNode, srcInodeNumber);
                    return "Move Successfull";
                }
                else{
                    return "Directory/File Already exists";
                }
            }

    private void updateContent(int inodeNumber, String dest,SqlCommands sql) {
                INode inode= (INode) sql.retrieveObject(inodeNumber);
                if(inode.getFileType()==1){
                    Directory dir=(Directory) inode.getFileReference();
                    dir.setName(dest);
                    inode.setFileReference(dir);
                }
                else {
                    File f=(File) inode.getFileReference();
                    f.setName(dest);
                    inode.setFileReference(f);
                }
                sql.UpdateObject(inode,inodeNumber);
    }
}
