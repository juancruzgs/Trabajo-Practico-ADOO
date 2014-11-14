/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

/**
 *
 * @author Maximiliano
 */
public class FactoryXML implements AbstractFactory {
    
    public  Parser createParser(){
        
       return new XMLParser();
    }
    public Sender createSender(){
        
        return new XMLSender();
    }
    
}
