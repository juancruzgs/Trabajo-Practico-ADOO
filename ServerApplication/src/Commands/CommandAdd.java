/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.Broker;
import XMLServer.XMLSender;
import java.io.PrintWriter;
import MessageObjects.MessageAck;

/**
 * Action that add a user in the database 
 * @author Juan
 */
public class CommandAdd extends Command {
    /**
     * 
     * @param broker Intermediate between database and server application 
     * @param sender Create a XML and sent it to the client with the response 
     * @param out    Response to the client
  
     */
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
        if (!parameters.get(0).equals("") && !parameters.get(1).equals("")) {
        
            MessageAck response = broker.add(this.parameters.get(0),this.parameters.get(1));
            sender.sendAck(response,out);
        }
        else
            sender.sendError("Parsing Error", out);
    }
    
}