/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

/**
 *
 * @author Juan
 */
public class CommandModify extends Command{
    
    public void execute(Broker broker, Sender sender){
        
        MessageAck response = broker.modify(this.parameters.get(0),this.parameters.get(1),this.parameters.get(2));
    }
    
}
