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
 * Action that list all users storaged in the database
 * @author Juan
 */
public class CommandListUsers extends Command{
    /**
     * 
     * @param broker Intermediate between database and server application 
     * @param sender Create a XML and sent it to the client with the response 
     * @param out    Response to the client
  
     */
    public void execute(Broker broker, XMLSender sender, PrintWriter out){
       
        ArrayList<User> response = broker.listUsers();
        sender.sendListUsers(response, out);
    }
}
