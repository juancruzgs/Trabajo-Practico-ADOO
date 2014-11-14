/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.Broker;
import XMLServer.XMLSender;
import java.io.PrintWriter;
import java.util.ArrayList;
import MessageObjects.Authentication;

/**
 * Action that list all the authentications from a specific user 
 * @author Juan
 */
public class CommandListAut extends Command {
    /**
     * 
     * @param broker Intermediate between database and server application 
     * @param sender Create a XML and sent it to the client with the response 
     * @param out    Response to the client
  
     */
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
            if (!parameters.get(0).equals("")){
                ArrayList<Authentication> response = broker.listAut(this.parameters.get(0));
                sender.sendListAut(response,out);
            }
            else
                sender.sendError("Parsing Error", out);
        
    }
    
}
