/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import MessageObjects.MessageAck;

/**
 * Action that browse user and delete it from database 
 * @author Juan
 */
public class CommandRemove extends Command {
    /**
     * 
     * 
     * 
     */
    public void execute(){
        if (!parameters.get(0).equals("")){
            MessageAck response = broker.remove(this.parameters.get(0));
            sender.sendAck(response);
        }
        else
            sender.sendError("Parsing Error");

        
    }
    
}
