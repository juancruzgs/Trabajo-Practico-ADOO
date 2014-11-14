/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLServer;

import XMLSender.XMLSender;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.Broker;
import Commands.Command;

/**
 * Thread 
 * @author Juan
 */
public class Receiver implements Runnable{

    public Receiver(Socket socket, String passwordAdmin, Broker broker) {
        this.socket = socket;
        this.passwordAdmin = passwordAdmin;
        this.broker = broker;
    }
    
    private Socket socket;
    private String passwordAdmin;
    private Broker broker;
    /**
     * Starts thread
     */
    public void run(){
        PrintWriter out = null;
        XMLSender sender = null;
        
        String remoteIP = socket.getRemoteSocketAddress().toString();
        remoteIP = remoteIP.substring(remoteIP.indexOf("/")+1,remoteIP.indexOf(":"));
        
        XMLParser parser = new XMLParser(passwordAdmin,remoteIP);
       
        try {
            
            out = new PrintWriter(socket.getOutputStream(),true);
            sender = new XMLSender(out);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            Command command = parser.parse(in.readLine());
            
            command.setBroker(broker);
            command.setSender(sender);
            command.execute();
            
            out.close();
            in.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
        
    }
     
}