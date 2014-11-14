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
public class CommandListAut extends Command {
    
    public void execute(Broker broker, Sender sender, PrintWriter out){
       
            ArrayList<Autenticacion> response = broker.listAut(this.parameters.get(0));
            sender.sendListAut(response,out);
        
    }
    
}
