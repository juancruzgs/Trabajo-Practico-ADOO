/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class EmbeddedServer {
    
    public EmbeddedServer(){
        setProperties();
    }
    
    private Broker broker;
    private String passwordAdmin;

    private void setProperties() {

        Properties propiedades = new Properties();
        InputStream entrada = null;

        try {
            entrada = new FileInputStream("serverConfig.properties");
            propiedades.load(entrada);

            this.broker = Broker.getInstance(propiedades.getProperty("urlconnection"),propiedades.getProperty("userdb"),
                                propiedades.getProperty("passworddb"));


            this.passwordAdmin= propiedades.getProperty("passwordadmin");


        } catch (IOException ex) {
            ex.printStackTrace();
          } finally {
             if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    e.printStackTrace();
                  }
             }
           }

    }
    
    public MessageAck add(String username,String password, String adminPassword){
        if (adminPassword.equals(this.passwordAdmin)){
            return broker.add(username, password);
        }
        else{
            return new MessageAck("ERROR","Invalid Admin Password");
        }
    }
    
    public MessageAck remove(String username,String adminPassword){
        if (adminPassword.equals(this.passwordAdmin)){
            return broker.remove(username);
        }
        else{
            return new MessageAck("ERROR","Invalid Admin Password");
        }       
    }
    
    public MessageAck modify(String username,String password, String newPassword){
        return broker.modify(username, password, newPassword);        
    }
    
    public MessageAck authenticate(String username,String password){
        try {
            return broker.authenticate(username, password, InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(EmbeddedServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
    }
    
    public ArrayList<User> listUsers(String adminPassword){
        if (adminPassword.equals(this.passwordAdmin)){
            return broker.listUsers();
        }
        else{
            return null;
        }
    }
    
    public ArrayList<Authentication> listAut(String username, String adminPassword){
       if (adminPassword.equals(this.passwordAdmin)){
            return broker.listAut(username);
        }
        else{
            return null;
        }
    }
        
}    