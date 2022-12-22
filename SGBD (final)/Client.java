package socket;

import java.io.*;
import java.net.*;
import java.util.*;

import sgbd.Show;
import sgbd.Table;

public class Client extends Thread{

    public void sendRequest(DataOutputStream dos, String str)throws Exception{
        dos.writeUTF(str);
        dos.flush();
    }

    public void showResponse(ObjectInputStream ois)throws Exception{
        Object result = ois.readObject();
        if(result instanceof Table){
            Table t = (Table) result;
            Show sh = new Show(t);
            Vector<String> Str = sh.StringVersion();
            for(int i=0; i<Str.size(); i++){
                System.out.println(Str.get(i));
            }
            System.out.println("* "+t.getData().size()+" row(s) Selectioned");
            System.out.println(" ");
        }
        if(result instanceof Exception){
            Exception ex = (Exception) result;
            System.out.println("ERROR:: "+ex.getMessage());
            System.out.println(" ");
        }
        if(result instanceof String){
            System.out.println("Query OK: "+result);
            System.out.println(" ");
        }
    }

    public void run( ){
        final Socket socket;
        final DataOutputStream dos;
        final ObjectInputStream ois;
        final Scanner scan = new Scanner(System.in);

        try{
        
            socket = new Socket("localhost", 777 );
            dos = new DataOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            try{
                
                String str="";
                while(true){
                    System.out.printf(">");
                    str = scan.nextLine();
                    if(str.equalsIgnoreCase("exit")){
                        scan.close();
                        socket.close();
                        
                    }
                    sendRequest(dos, str);
                    showResponse(ois);
                }

            }catch(Exception e){
                System.out.println(e.getMessage());
                e.getStackTrace();
            }finally{
                try {
                    ois.close();
                    dos.close();
                    socket.close();
                } catch (Exception e) {
                    //TODO: handle exception
                }  
            }
        }catch(Exception e){ 
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
}