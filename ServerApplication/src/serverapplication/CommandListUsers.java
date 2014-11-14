/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class CommandListUsers extends Command{
    
    public void execute(Broker broker, Sender sender){
       
        ArrayList response = broker.listUsers();
        
    }
}
