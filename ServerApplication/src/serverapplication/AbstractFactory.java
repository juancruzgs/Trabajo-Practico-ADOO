/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

/**
 *
 * @author Juan
 */
public interface AbstractFactory {
    
    public Parser createParser();
    public Sender createSender();
    
}
