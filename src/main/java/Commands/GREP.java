package Commands;

import FileSystem.SuperNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GREP {
    public String execute(String pattern, String text){
        System.out.println(pattern+"\n"+text);
        StringBuilder returnString= new StringBuilder();
        String[] texts=text.split("\n");
      Pattern p=Pattern.compile(pattern);
        for (String s : texts) if (p.matcher(s).find()) returnString.append(s).append("\n");
        return returnString.toString();
    }
}
