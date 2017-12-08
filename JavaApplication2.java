/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.io.*;
import java.net.*;
/**
 *
 * @author MC
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
   



    //Main Method:- called when running the class file.
    public static void main(String[] args){ 
        
        //Portnumber:- number of the port we wish to connect on.
        int portNumber = 15882;
        //ServerIP:- IP address of the server.
        String serverIP = "192.168.17.158";
        
        try{
            //Create a new socket for communication
            Socket soc = new Socket(serverIP,portNumber);
            
            // create new instance of the client writer thread, intialise it and start it running
            ClientWriter clientWrite = new ClientWriter(soc);
            Thread clientWriteThread = new Thread(clientWrite);
            clientWriteThread.start();
            
        }
        catch (Exception except){
            //Exception thrown (except) when something went wrong, pushing message to the console
            System.out.println("Error --> " + except.getMessage());
        }
    }
}

//This thread is responcible for writing messages
class ClientWriter implements Runnable
{
    Socket cwSocket = null;
    
    public ClientWriter (Socket outputSoc){
        cwSocket = outputSoc;
    }
    
    public void run(){
        try{
            //Create the outputstream to send data through
            DataOutputStream dataOut = new DataOutputStream(cwSocket.getOutputStream());
            
            System.out.println("Client writer running");
            
            //Write message to output stream and send through socket
            dataOut.writeUTF("I LIKE TOAST!");
            dataOut.flush();
            
            //close the stream once we are done with it
            dataOut.close();
        }
        catch (Exception except){
            //Exception thrown (except) when something went wrong, pushing message to the console
            System.out.println("Error in Writer--> " + except.getMessage());
        }
    }
}



    

