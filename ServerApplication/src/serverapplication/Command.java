/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public abstract class Command {

    public ArrayList<String> getParameters() {
        return parameters;
    }

    protected ArrayList<String> parameters = new ArrayList<String>();

    public void addParameter(String param){
        parameters.add(param);
    }    
    
    public abstract void execute(Broker broker, Sender sender, PrintWriter out);
}
