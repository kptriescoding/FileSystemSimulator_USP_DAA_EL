package FileSystem;

import Database.SqlCommands;
import Models.Directory;

public class SuperNode {
    public SuperNode(){
        SqlCommands sql=new SqlCommands();
        SuperNode superNode=(SuperNode) sql.retrieveObject(1);
        if(superNode==null){
            this.currentNode=2;
            this.numberofNodes=1;
            Directory root= new Directory(this,1);
            this.numberofNodes++;
            sql.storeObject(this);
            sql.storeObject(root);
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
