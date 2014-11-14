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
    
    public void execute(Broker broker, Sender sender, PrintWriter out){
        
        MessageAck response = broker.remove(this.parameters.get(0));
        
    }
    
}
