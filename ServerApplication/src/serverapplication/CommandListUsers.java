/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class CommandListUsers extends Command{
    
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
       
        ArrayList<User> response = broker.listUsers();
        sender.sendListUsers(response, out);
    }
}
