/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class CommandAuthenticate extends Command{
    
    public void execute(Broker broker, Sender sender){
      
        MessageAck response = broker.authenticate(this.parameters.get(0),this.parameters.get(1),this.parameters.get(2));

    }
    
}
