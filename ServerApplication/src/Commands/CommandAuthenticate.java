/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import MessageObjects.MessageAck;

/**
 *
 * @author Juan
 */
public class CommandAuthenticate extends Command{
    
    public void execute(){
      if (!parameters.get(0).equals("") && !parameters.get(1).equals("") && !parameters.get(2).equals("")) {
        MessageAck response = broker.authenticate(this.parameters.get(0),this.parameters.get(1),this.parameters.get(2));
        sender.sendAck(response);
      }
      else
        sender.sendError("Parsing Error");
    }
    
}
