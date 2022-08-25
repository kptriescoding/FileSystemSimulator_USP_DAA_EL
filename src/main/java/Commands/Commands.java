package Commands;

import FileSystem.SuperNode;

public class Commands {
    private CD cd;
    private LS ls;
    private MKDIR mkdir;
    private String command;
    private TOUCH touch;
    private char[] cmds;
    private String fileDirectoryName;
    public Commands(){
        cd=new CD();
        ls=new LS();
        mkdir=new MKDIR();
        touch=new TOUCH();

    }
    public String run(String cmd, SuperNode superNode){
        cmd=cmd.trim();
        String[] split = cmd.split(" ");
        command = split[0];
        fileDirectoryName = split[split.length-1];
        if(split.length>2) {
            char[] cmds = (split[1].substring(1)).toCharArray();
        }
        switch (command) {
            case "mkdir" -> {
                return mkdir.execute(superNode, superNode.getCurrentNode(), fileDirectoryName);
            }
            case "cd" -> {
                return cd.execute(superNode, fileDirectoryName);
            }
            case "ls" -> {
                return ls.execute(superNode, superNode.getCurrentNode());
            }
            case "touch" -> {
                return touch.execute(superNode,superNode.getCurrentNode(),fileDirectoryName);
            }
            default -> {
                return "Error command";
            }
        }

    }
    public String getPrevCommand(){
        return this.command;
    }


}


