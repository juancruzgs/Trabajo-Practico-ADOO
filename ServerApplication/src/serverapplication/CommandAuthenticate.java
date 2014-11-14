/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.PrintWriter;

/**
 *
 * @author Juan
 */
public class CommandAuthenticate extends Command{
    
    public void execute(Broker broker, Sender sender, PrintWriter out){
      
        MessageAck response = broker.authenticate(this.parameters.get(0),this.parameters.get(1),this.parameters.get(2));

    }
    
}
