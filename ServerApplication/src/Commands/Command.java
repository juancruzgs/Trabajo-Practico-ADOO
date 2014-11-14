/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.Broker;
import XMLSender.XMLSender;
import java.util.ArrayList;

/**
 *
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

    protected ArrayList<String> parameters = new ArrayList<String>();

    public void addParameter(String param){
        parameters.add(param);
    }    
    
    public abstract void execute();
}
