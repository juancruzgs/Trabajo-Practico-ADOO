/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class Command {

    private String description;

    public Command(String description) {
        this.description = description;
    }
    private ArrayList<String> parameters = new ArrayList<String>();

    public void addParameter(String param){
        parameters.add(param);
    }    
}
