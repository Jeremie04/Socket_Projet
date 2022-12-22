package sgbd;

import java.util.*;
import java.io.*;
import java.lang.reflect.Method;

public class DataRequest{
    String database;
    String request;

    public String getDatabase(){
        return this.database;
    }
    public void setDatabase(String value){
        this.database=value;
    }

    public String getRequest(){
        return this.request;
    }
    public void setRequest(String value){
        this.request=value;
    }

    public DataRequest(String dtb, String request){
        setDatabase(dtb);
        setRequest(request);
    }

/// Procedure method

    public Object ExcecuteDataRequest()throws Exception{
        String FirstElement = request.split(" ")[0];
        String[] req;
        String[] element;
        TableManager tm = new TableManager(this.getDatabase());
        DatabaseManager dataM = new DatabaseManager();

        switch (FirstElement) {
            case "tableCreate":    /* Create table table:col1,col2,... */
                if(this.getRequest().contains(":")){
                    req = this.getRequest().split("tableCreate ");
                    element = req[1].split(":"); // 0 for table name; 1 for columns
                    tm.Creer(element[0], element[1]);
                    return "Table created";   
                } break;   
                
            case "tableInsert":     /* Insert into table:d1,d2,d3... */
                if(this.getRequest().contains(":")){
                    req = this.getRequest().split("tableInsert into ");
                    element = req[1].split(":"); // 0 for table name; 1 for columns
                    tm.Insert(element[0], element[1]);
                    return "Insertion done";
                } break;

            case "tableDelete":     /* Delete table */

                req = this.getRequest().split("tableDelete ");
                tm.Delete(req[1]);
                return "Table Deleted";

            case "dataCreate":      /* Create database dbbName */
                
                req = this.getRequest().split("dataCreate ");
                dataM.CreerDataBase(req[1]);
                return "Database Created"; 

        }
        throw new Exception("Data Syntax Error");
    }
}