package sgbd;

import java.util.*;
import java.io.*;


public class DatabaseManager {
    public boolean DatabaseExist (String database){
        String directory = "databases";  // the actual directory
        File f = new File(directory);
        String[] listFile = f.list();
        Vector<String> list = new Vector<>();
        //System.out.println()
        for(int i=0; i<listFile.length; i++){
            System.out.println(listFile[i]);
            list.add(listFile[i]);
        }
        return list.contains(database);
    }

    public void CreerDataBase (String dbbName)throws Exception{
        String directory = "databases/"+dbbName;
        File f = new File(directory);
        f.mkdir();
    }

}
