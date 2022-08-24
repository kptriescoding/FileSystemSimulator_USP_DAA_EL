package Models;

import java.util.ArrayList;

public class Directory {
    int parentINodeNumber;
    ArrayList<Contents>contents;
}
class Contents {
    int inodeNumber;
    String name;
}
