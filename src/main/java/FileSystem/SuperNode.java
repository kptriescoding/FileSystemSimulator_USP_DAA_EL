package FileSystem;

import Database.SqlCommands;

public class SuperNode {
    public SuperNode(){
        SqlCommands sql=new SqlCommands();
        SuperNode superNode=(SuperNode) sql.retrieveObject(1);

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
