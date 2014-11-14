/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.PrintWriter;

/**
 * Action that browse a specific user and update his password 
 * @author Juan
 */
public class CommandModify extends Command{
    /**
     * 
     * @param broker Intermediate between database and server application 
     * @param sender Create a XML and sent it to the client with the response 
     * @param out    Response to the client
     */
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
        if (!parameters.get(0).equals("") && !parameters.get(1).equals("") && !parameters.get(2).equals("")) {
            MessageAck response = broker.modify(this.parameters.get(0),this.parameters.get(1),this.parameters.get(2));
            sender.sendAck(response,out);
        }
        else
            sender.sendError("Parsing Error", out);

    }
    
}
