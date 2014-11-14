/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.ArrayList;
import MessageObjects.User;

/**
 * Action that list all users storaged in the database
 * @author Juan
 */
public class CommandListUsers extends Command{
    /**
     * 
     *  calls broker with the correspondent parameters and sends the result to the sender
     * 
  
     */
    public void execute(){
       
        ArrayList<User> response = broker.listUsers();
        sender.sendListUsers(response);
    }
}
