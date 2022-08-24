package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Directory implements Serializable {
    public int parentINodeNumber;
   public  ArrayList<DirContents>contents;
}
