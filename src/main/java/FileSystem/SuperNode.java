package FileSystem;

import Database.SqlCommands;
import Models.Directory;

import java.io.Serializable;

public class SuperNode implements Serializable {
    public SuperNode(){
        SqlCommands sql=new SqlCommands();
        SuperNode superNode=(SuperNode) sql.retrieveObject(1);
        if(superNode==null){
            this.currentNode=2;
            this.numberofNodes=2;
            Directory root= new Directory(this,2);
            INode iNode=new INode(this,1,root);
            sql.storeObject(this,1);
            sql.storeObject(iNode,2);
        }
        else{
            this.currentNode=superNode.getCurrentNode();
            this.numberofNodes= superNode.getNumberofNodes();
        }
    }
    public void updateSuperNode(){
        SqlCommands sql=new SqlCommands();
        numberofNodes=numberofNodes+1;
        sql.UpdateObject(this,1);
    }
    private int currentNode;
    private int numberofNodes;

    public int getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(int currentNode) {
        this.currentNode = currentNode;
    }

    public int getNumberofNodes() {
        return numberofNodes;
    }

    public void setNumberofNodes(int numberofNodes) {
        this.numberofNodes = numberofNodes;
    }
}
