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
public class CommandError extends Command{
    
    public void execute(Broker broker, Sender sender, PrintWriter out){
        
        sender.sendError("Unknown Error",out);
    }
    
}
