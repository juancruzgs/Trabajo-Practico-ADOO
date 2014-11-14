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
public class CommandModify extends Command{
    
    public void execute(Broker broker, Sender sender, PrintWriter out){
        
        MessageAck response = broker.modify(this.parameters.get(0),this.parameters.get(1),this.parameters.get(2));
        sender.sendAck(response,out);

    }
    
}
