/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.io.*;
import java.net.*;

/**
 *
 * @author MC
 */
public class JavaApplication1 {

        public static void main(String[] args){ 
        
        //Portnumber:- number of the port we wish to connect on.
        int portNumber = 15882;
        try{
            //Setup the socket for communication 
            ServerSocket serverSoc = new ServerSocket(portNumber);
            
            while (true){
                
                //accept incoming communication
                System.out.println("Waiting for client");
                Socket soc = serverSoc.accept();
                
                //create a new thread for the connection and start it.
                ServerConnetionHandler sch = new ServerConnetionHandler(soc);
                Thread schThread = new Thread(sch);
                schThread.start();
            }
            
        }
        catch (Exception except){
            //Exception thrown (except) when something went wrong, pushing message to the console
            System.out.println("Error --> " + except.getMessage());
        }
    }   
}
    
class ServerConnetionHandler implements Runnable
{
    Socket clientSocket = null;
    
    public ServerConnetionHandler (Socket inSoc){
        clientSocket = inSoc;
    }
    
    public void run(){
        try{
            //Catch the incoming data in a data stream, read a line and output it to the console
            DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
            
            System.out.println("Client Connected");
            
            //Print out message
            System.out.println("--> " + dataIn.readUTF());
            
            //close the stream once we are done with it
            dataIn.close();
        }
        catch (Exception except){
            //Exception thrown (except) when something went wrong, pushing message to the console
            System.out.println("Error in ServerHandler--> " + except.getMessage());
        }
    }
}



    

