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
public class CommandRemove extends Command {
    
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
        if (!parameters.get(0).equals("")){
            MessageAck response = broker.remove(this.parameters.get(0));
            sender.sendAck(response,out);
        }
        else
            sender.sendError("Parsing Error", out);

        
    }
    
}
