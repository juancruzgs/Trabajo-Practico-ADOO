/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.PrintWriter;

/**
 *
 * @author Juan
 */
public class CommandAdd extends Command {
    
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
        if (!parameters.get(0).equals("") && !parameters.get(1).equals("")) {
        
            MessageAck response = broker.add(this.parameters.get(0),this.parameters.get(1));
            sender.sendAck(response,out);
        }
        else
            sender.sendError("Parsing Error", out);
    }
    
}