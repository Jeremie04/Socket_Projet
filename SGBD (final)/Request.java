package sgbd;

import java.util.*;
import java.io.*;

public class Request{
/// Attributs
    Vector<Table> relation; 
    String request;
    String database;

/// Setters & Getters
    public void setRelation (Vector<Table> r)throws Exception{
        if(r!=null){
            this.relation=r;
        }else{
            throw new Exception("Relation invalid");
        }
    }

    public Vector<Table> getRelation()throws Exception{
        if(this.relation==null){
            TableManager tm = new TableManager(this.getDatabase());
            this.setRelation(tm.TakeRelation());
        }
        return this.relation;
    }

    public Table getRelation(String nom)throws Exception{
        for(int i=0; i<this.getRelation().size(); i++){
            if(this.getRelation().get(i).getNom().equalsIgnoreCase(nom)){
                return this.getRelation().get(i);
            }
        }
        throw new Exception(nom+" not found");
    }

    public String getDatabase(){
        return this.database;
    }
    public void setDatabase(String value){
        this.database=value;
    }


/// Constructor
    public Request(String dtb){this.database=dtb;}
    public Request(String req, String dtb){
        this.database=dtb;
        this.request = req;
    }


/// Fonctions
    public Vector<Table> getRelationfromRequest( String action)throws Exception{
        Vector<Table> rel = new Vector<>();
        String[] element = request.split(action+" ");
        String[] tableName = element[1].split(" with ");
        rel.add (this.getRelation(tableName[0]));
        rel.add (this.getRelation(tableName[1]));
        return rel;
    }

    public Vector<String> split (String text, String splitter){
        Vector<String> result = new Vector<>();
        String[] splitted = text.split(splitter); 
        for(int i=0; i<splitted.length; i++){
            result.add(splitted[i]);
        }
        return result;
    }

    public Table selectWithCondition ()throws Exception{
            /* SELECT col1,col2,... FROM table */
        String[] req = request.split("select "); 
        String[] element = req[1].split(" from ");
        String columns = element[0];
        String t = element[1];
        
        /* table where col1=val1 and col2=val2*/
        String tab = t.split(" where ")[0];
        Table table = getRelation(tab);
        String colonne = t.split(" where ")[1];
        Vector<String> cond = split(colonne, " and ");
        Vector<String> col = new Vector<>();
        Vector<String> valeur = new Vector<>();
        for(int i=0; i<cond.size(); i++){
            Vector<String> el = split(cond.get(i), "=");
            col.add(el.get(0));
            valeur.add(el.get(1)); 
        }
        
        if(columns.equals("*")){
            return table.Selection(null, col, valeur);
        }else{
            Vector<String> cols = split(columns, ",");
            
            return table.Selection(cols, col, valeur); 
        }

    }

    public Table selectWithoutCondition ()throws Exception{
        /* SELECT col1,col2,... FROM table */
        String[] req = request.split("select "); 
        String[] element = req[1].split(" from ");
        String columns = element[0];
        String t = element[1];

        Table table = getRelation(t);
        if(columns.equals("*")){
            return table;
        }else{
            Vector<String> cols = split(columns, ",");
            
            return table.Projection(cols, true); 
        }
    }



    public Table ExcecuteRequest ()throws Exception{
        String FirstElement = request.split(" ")[0];
        switch (FirstElement) {
            case "select":
                if(request.contains("where")){
                    return this.selectWithCondition();
                }else{
                    return this.selectWithoutCondition();
                }
        
            case "substract":
                /* SUBSTRACT table1 with table2 */
                Vector<Table> rel = getRelationfromRequest("substract");
                return rel.get(0).Difference(rel.get(1));
            

            case "divide":
                /*Divide tab1 with tab2 */
                Vector<Table> tab = getRelationfromRequest("divide");
                return tab.get(0).Division(tab.get(1));
            

            case "unify":
                /*Unify table with table */
                Vector<Table> table = getRelationfromRequest("unify");
                return table.get(0).Union(table.get(1));        
            

            case "join":
                /*Join table1 with table2 on colonne */
                String[] element = request.split("join ");
                String[] table_colonne = element[1].split(" on ");
                String[] tables = table_colonne[0].split(" with ");
                Table r1 = this.getRelation(tables[0]);
                Table r2 = this.getRelation(tables[1]);
                return r1.Join(r2, table_colonne[1]);
            

            case "intersect":
                /*intersect table1 with table2*/
                Vector<Table> relation = getRelationfromRequest("intersect");
                return relation.get(0).Intersection(relation.get(1));
            
        }
        throw new Exception("Syntax Error !");
    }

}