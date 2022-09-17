package Commands;

import FileSystem.SuperNode;

public class Commands {
    private CD cd;
    private LS ls;
    private MKDIR mkdir;
    private String command;
    private TOUCH touch;
    private RMDIR rmdir;
    private RM rm;
    private SaveTextFile save;
    private char[] cmds;
    private CAT cat;
    private LNK lnk;
    private MV mv;
    private String fileDirectoryPath;
    public Commands(){
        cd=new CD();
        ls=new LS();
        mkdir=new MKDIR();
        touch=new TOUCH();
        rmdir=new RMDIR();
        rm=new RM();
        save=new SaveTextFile();
        cat=new CAT();
        mv=new MV();
        lnk=new LNK();
    }
    public String run(String cmd, SuperNode superNode){
        cmd=cmd.trim();
        String[] split = cmd.split(" ");
        command = split[0];
        fileDirectoryPath = split[split.length-1];
        if(split.length>2) {
            char[] cmds = (split[1].substring(1)).toCharArray();
        }
        switch (command) {
            case "mkdir" -> {
                return mkdir.execute(superNode, fileDirectoryPath);
            }
            case "cd" -> {
                return cd.execute(superNode, fileDirectoryPath);
            }
            case "ls" -> {
                return ls.execute(superNode, superNode.getCurrentNode());
            }
            case "touch" -> {
                return touch.execute(superNode, fileDirectoryPath);
            }
            case "rmdir"->{
                return rmdir.execute(superNode, fileDirectoryPath);
            }
            case "rm"->{
                return rm.execute(superNode, fileDirectoryPath);
            }
            case "cat"->{
                return cat.execute(superNode, fileDirectoryPath);
            }
            case "lnk"->{
                String[] s =cmd.split(" ");
                if(s.length<3)return "Insufficient Parameters";
                return lnk.execute(superNode,s[1],s[2]);
            }
            case "mv"->{
                String[] s =cmd.split(" ");
                if(s.length<3)return "Insufficient Parameters";
                return mv.execute(superNode,s[1],s[2]);
            }
            default -> {
                return "Error command";
            }
        }

    }
    public void saveTextFile(int inodeNumber,String content){
        save.execute(inodeNumber,content);
    }
    public String getPrevCommand(){
        return this.command;
    }


}


