package affichage;

import java.util.*;
import sgbd.*;
import java.io.*;

public class Main{
  
    public static void main (String[] args){
        
        try{
                Table r = new Table();
                Request req = new Request("select * from table1 where age=18 and sexe=garcon","essai2");
                
                //DataRequest req= new DataRequest("Essai","select * from table1 where nom=Ranto" );
                // req.ExcecuteRequest();

                Show sh = new Show (req.ExcecuteRequest());
                Vector<String> Str = sh.StringVersion();
                for(int i=0; i<Str.size(); i++){
                    System.out.println(Str.get(i));
                }

                TableManager tm = new TableManager("Essai");
                 tm.Drop("vide");


    /*SCANNER */
        // String request = " ";
        // Scanner scan = new Scanner(System.in);
        // while(request!="quit"){
        //     System.out.printf("req>");
        //     request = scan.nextLine();
        //     req.ExcecuteRequest(request);
        // }
        // scan.close();

    /*ESSAI Create and INsert */
        TableManager tableM = new TableManager("essai");
        //System.out.println (tableM.splitData("/a1;x1/a2;x2/a3;x1/a1;x2/a2;x1"));
        // tableM.Creer("Relation", "nom;prenom;numero");
        // tableM.Insert("Relation", "Kiki;hehe;1");
        // tableM.Insert("Relation", "Meme;lolo;2");
        // tableM.Insert("Relation", "Mama;lilo;3");

        DatabaseManager dManager = new DatabaseManager();
        //System.out.println(dManager.DatabaseExist("Essai"));
    /*EXCECUTE TRYING */
        //    req.ExcecuteRequest("select age from personne where Nom=ranto");
        //    req.ExcecuteRequest("intersect personne with people");
        //    req.ExcecuteRequest("join personne with note on Nom");
        //    req.ExcecuteRequest("substract etudiant1 with etudiant2");
        //    req.ExcecuteRequest();
        //    req.ExcecuteRequest("unify personne with people");
        //    req.ExcecuteRequest("create essai:id,nom,prenom");
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }


}