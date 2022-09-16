package Commands;

import Database.SqlCommands;
import FileSystem.INode;
import Models.DirContents;
import Models.Directory;

import java.util.*;

public class TreeListing {
    public ArrayList<Directory> execute(){
        SqlCommands sql=new SqlCommands();
        StringBuilder s= new StringBuilder();
        Stack<Directory> stack=new Stack<>();
        ArrayList<Directory> order=new ArrayList<>();
        INode inode= (INode) sql.retrieveObject(2);
        Directory d=(Directory)inode.getFileReference();
        stack.add(d);
        while(!stack.isEmpty()) {
            d=stack.pop();
            order.add(d);
            assert d != null;
            for (DirContents dirContent : d.getContents()) {
                if(Objects.equals(dirContent.getName(), ".") || Objects.equals(dirContent.getName(), "..")||Objects.equals(dirContent.getName(),"/"))continue;
                if(dirContent.getFileType()==0)continue;
                s.append(dirContent.getName()).append(" ");
                inode=(INode) sql.retrieveObject(dirContent.getInodeNumber());
                stack.add((Directory) inode.getFileReference());
            }
            s.append("\n");
        }
        System.out.println(s);
        return order;
    }
    public static void main(String[] args) {
        TreeListing treeListing=new TreeListing();
        System.out.println(treeListing.execute());
    }
}
