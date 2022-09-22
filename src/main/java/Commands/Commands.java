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
    private GREP grep;
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
        grep=new GREP();
    }
    public String run(String cmd, SuperNode superNode){
        cmd=cmd.trim();
       String[] split= cmd.split("\\|");
       String prevResponse="";
       for(int i=0;i<split.length;i++){
           split[i]=split[i].trim();
           split[i]+=" "+prevResponse;
           prevResponse=runCommand(split[i],superNode);
           System.out.println(prevResponse);
       }
       return prevResponse;
    }
    public String runCommand(String cmd, SuperNode superNode){
        cmd=cmd.trim();
        String[] split = cmd.split(" ");
        command = split[0];
        fileDirectoryPath = split[split.length-1];
        if(split.length>2) {
            char[] cmds = (split[1].substring(1)).toCharArray();
        }
        switch (command) {
            case "mkdir" -> {
                if(split.length<2)return "Insufficient Parameters";
                return mkdir.execute(superNode, fileDirectoryPath);
            }
            case "cd" -> {
                if(split.length<2)return "Insufficient Parameters";
                return cd.execute(superNode, fileDirectoryPath);
            }
            case "ls" ->{
                if(split.length<2)fileDirectoryPath=".";
                return ls.execute(superNode,fileDirectoryPath);
            }
            case "touch" -> {
                if(split.length<2)return "Insufficient Parameters";
                return touch.execute(superNode, fileDirectoryPath);
            }
            case "rmdir"->{
                if(split.length<2)return "Insufficient Parameters";
                return rmdir.execute(superNode, fileDirectoryPath);
            }
            case "rm"->{
                if(split.length<2)return "Insufficient Parameters";
                return rm.execute(superNode, fileDirectoryPath);
            }
            case "cat"->{
                if(split.length<2)return "Insufficient Parameters";
                return cat.execute(superNode, fileDirectoryPath);
            }
            case "lnk"->{
                if(split.length<3)return "Insufficient Parameters";
                return lnk.execute(superNode,split[1],split[2]);
            }
            case "mv"->{
                if(split.length<3)return "Insufficient Parameters";
                return mv.execute(superNode,split[1],split[2]);
            }
            case "grep"->{
                int i=0,j=0;
                while(i<2){
                    if(cmd.charAt(j)==' ') i++;
                    j++;
                }
                split[2]=cmd.substring(j);
                if(split.length<3)return "Insufficient Parameters";
                return grep.execute(split[1],split[2]);
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


