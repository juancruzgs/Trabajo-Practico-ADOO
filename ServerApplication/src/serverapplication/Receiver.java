/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class Receiver implements Runnable{

    public Receiver(Socket socket, String passwordAdmin, Parser parser, Sender sender, Broker broker) {
        this.socket = socket;
        this.passwordAdmin = passwordAdmin;
        this.parser = parser;
        this.sender = sender;
        this.broker = broker;
    }
    
    private Socket socket;
    private String passwordAdmin;
    private Parser parser;
    private Sender sender;
    private Broker broker;
    
    public void run(){
        PrintWriter out = null;
        
        String remoteIP = socket.getRemoteSocketAddress().toString();
        remoteIP = remoteIP.substring(remoteIP.indexOf("/")+1,remoteIP.indexOf(":"));
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Command command = parser.parse(in.readLine(),passwordAdmin,remoteIP);
            command.execute(broker,sender,out);
            
            out.close();
            in.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
        
    }
    
    
}
