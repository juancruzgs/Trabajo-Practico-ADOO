/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.Broker;
import XMLSender.XMLSender;
import java.util.ArrayList;

/**
 * An specific action that client wants to execute
 * @author Juan
 */
public abstract class Command {
    
    protected Broker broker;

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public void setSender(XMLSender sender) {
        this.sender = sender;
    }
    protected XMLSender sender;

    public ArrayList<String> getParameters() {
        return parameters;
    }
    /**
        *  An arraylist with correspondant parameters of a correspondant action 
     */
    protected ArrayList<String> parameters = new ArrayList<String>();
    
    /**
     * Adds parameters to the arraylist
     * @param param 
     */
    public void addParameter(String param){
        parameters.add(param);
    }    
    /**
     * calls broker with the correspondent parameters and sends the result to the sender
     */
    public abstract void execute();
}
