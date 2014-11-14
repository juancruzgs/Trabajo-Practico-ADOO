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
 * Action that browse user and delete it from database 
 * @author Juan
 */
public class CommandRemove extends Command {
    /**
     * 
     * @param broker Intermediate between database and server application 
     * @param sender Create a XML and sent it to the client with the response 
     * @param out Response to the client
     */
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
        if (!parameters.get(0).equals("")){
            MessageAck response = broker.remove(this.parameters.get(0));
            sender.sendAck(response,out);
        }
        else
            sender.sendError("Parsing Error", out);

        
    }
    
}
