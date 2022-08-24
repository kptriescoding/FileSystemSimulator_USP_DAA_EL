package Commands;

import java.util.ArrayList;

public class MkDir {
    String dirname;
    ArrayList<String> names ;
    

    public MkDir(String dirname,int id,ArrayList<String> namesTillNow){
        this.dirname = dirname;
        this.names = namesTillNow;
        names.add("shah");
        names.add("dil");
    }

    public boolean validate(){
        if(names.contains(dirname))
        return false;
        if(dirname.contains("."))
            return false;
        return true;
    }

    public ArrayList<String> getLatest(){
        return this.names;
    }
    
}
