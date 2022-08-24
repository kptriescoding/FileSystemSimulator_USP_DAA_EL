package Commands;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Command {
    String command;
    char[] cmds;
    String fileDirectoryName;
    public Command(String cmd){
        cmd.trim();
        String[] split = cmd.split(" ");
        command = split[0];
        fileDirectoryName = split[split.length-1];
        if(split.length>2) {
            char[] cmds = (split[1].substring(1)).toCharArray();
        }
    }



}


