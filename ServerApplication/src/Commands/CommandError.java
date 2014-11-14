/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.Broker;
import XMLServer.XMLSender;
import java.io.PrintWriter;

/**
 * Action that sends a error in case of mistakes during parsing process 
 * @author Juan
 */
public class CommandError extends Command{
    /**
     * 
     * @param broker Intermediate between database and server application 
     * @param sender Create a XML and sent it to the client with the response 
     * @param out    Response to the client
  
     */
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
        
        sender.sendError("Unknown Error",out);
    }
    
}
