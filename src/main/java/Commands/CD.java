package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;

import java.util.ArrayList;
import java.util.Objects;

public class CD extends RecursiveSearch {
    public String execute(SuperNode superNode, String name){
        SqlCommands sql=new SqlCommands();
        int inodeNumber=tracePath(superNode,name);
        String[] dirs = name.split("/");
       if(dirs.length>0) name=dirs[dirs.length-1];
        if(inodeNumber==-1||inodeNumber==-2){
            return error;
        }
        INode iNode= (INode) sql.retrieveObject(inodeNumber);
        if(iNode.getFileType()==1){
           Directory dir= (Directory) iNode.getFileReference();
          ArrayList<DirContents>dirContents= dir.getContents();
          boolean hasChanged=false;
          for(DirContents dirContent:dirContents){
              if(Objects.equals(dirContent.getName(), name)){
                  superNode.setCurrentNode(dirContent.getInodeNumber());
                  superNode.saveSuperNode();
                  hasChanged=true;
              }
          }
          if(hasChanged)
          return "Directory changed successfully";
          return "Directory "+name+ " doesn't exist";
        }
        else{
            return "The Directory is a File";
        }
    }
}
