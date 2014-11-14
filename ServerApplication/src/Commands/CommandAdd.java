/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import MessageObjects.MessageAck;

/**
 * Action that add a user in the database 
 * @author Juan
 */
public class CommandAdd extends Command {
    /**
     * 
     * 
     *  calls broker with the correspondent parameters and sends the result to the sender
  
     */
    public void execute(){
        if (!parameters.get(0).equals("") && !parameters.get(1).equals("")) {
        
            MessageAck response = broker.add(this.parameters.get(0),this.parameters.get(1));
            sender.sendAck(response);
        }
        else
            sender.sendError("Parsing Error");
    }
    
}