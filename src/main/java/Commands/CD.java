package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import FileSystem.SuperNode;
import Models.DirContents;
import Models.Directory;

import java.util.ArrayList;
import java.util.Objects;

public class CD {
    public String execute(SuperNode superNode, String name){
        SqlCommands sql=new SqlCommands();
        INode iNode= (INode) sql.retrieveObject(superNode.getCurrentNode());
        if(iNode.getFileType()==1){
           Directory dir= (Directory) iNode.getFileReference();
          ArrayList<DirContents>dirContents= dir.getContents();
          for(DirContents dirContent:dirContents){
              if(Objects.equals(dirContent.getName(), name)){
                  superNode.setCurrentNode(dirContent.getInodeNumber());
              }
          }
          return "Directory changed successfully";
        }
        else{
            return "The Directory is a File";
        }
    }
}
