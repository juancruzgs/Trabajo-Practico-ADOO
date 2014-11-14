/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.ArrayList;
import MessageObjects.Authentication;

/**
 * Action that list all the authentications from a specific user 
 * @author Juan
 */
public class CommandListAut extends Command {
    /**
     * 
     *  calls broker with the correspondent parameters and sends the result to the sender
     * 
  
     */
    public void execute(){
            if (!parameters.get(0).equals("")){
                ArrayList<Authentication> response = broker.listAut(this.parameters.get(0));
                sender.sendListAut(response);
            }
            else
                sender.sendError("Parsing Error");
        
    }
    
}
