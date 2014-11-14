/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

/**
 *
 * @author Juan
 */
public class CommandRemove extends Command {
    
    public void execute(Broker broker, Sender sender){
        
        MessageAck response = broker.remove(this.parameters.get(0));
        
    }
    
}
