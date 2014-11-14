/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 * Action that sends a error in case of mistakes during parsing process 
 * @author Juan
 */
public class CommandError extends Command{
    /**
     * 
     * sends the result to the sender
     * 
  
     */
    public void execute(){
        
        sender.sendError("Unknown Error");
    }
    
}
