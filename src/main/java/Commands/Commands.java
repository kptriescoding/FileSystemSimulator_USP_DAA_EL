package Commands;

import FileSystem.SuperNode;

public class Commands {
    private CD cd;
    private LS ls;
    private MKDIR mkdir;
    private String command;
    private char[] cmds;
    private String fileDirectoryName;
    public Commands(){
        cd=new CD();
        ls=new LS();
        mkdir=new MKDIR();
    }
    public void run(String cmd, SuperNode superNode){
        cmd=cmd.trim();
        String[] split = cmd.split(" ");
        command = split[0];
        fileDirectoryName = split[split.length-1];
        if(split.length>2) {
            char[] cmds = (split[1].substring(1)).toCharArray();
        }
        switch (command) {
            case "mkdir" -> mkdir.execute(superNode, superNode.getCurrentNode(), fileDirectoryName);
            case "cd" -> cd.execute(superNode, fileDirectoryName);
            case "ls" -> ls.execute(superNode, superNode.getCurrentNode());
            default -> System.out.println("Error command");
        }
    }



}


