/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import MessageObjects.MessageAck;

/**
 * Action that browse a specific user and update his password 
 * @author Juan
 */
public class CommandModify extends Command{
    /**
     * 
     *  calls broker with the correspondent parameters and sends the result to the sender
     * 
     */
    public void execute(){
        if (!parameters.get(0).equals("") && !parameters.get(1).equals("") && !parameters.get(2).equals("")) {
            MessageAck response = broker.modify(this.parameters.get(0),this.parameters.get(1),this.parameters.get(2));
            sender.sendAck(response);
        }
        else
            sender.sendError("Parsing Error");

    }
    
}
