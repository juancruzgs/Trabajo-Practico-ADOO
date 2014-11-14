/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EmbeddedServer;

import Database.Broker;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import MessageObjects.Authentication;
import MessageObjects.MessageAck;
import MessageObjects.User;

/**
 *
 * @author Juan
 */
public class EmbeddedServer {
    /**
     * EmbeddedServer Constructor
     */
    public EmbeddedServer(){
        setProperties();
    }
    
    private Broker broker;
    private String passwordAdmin;
    
    /**
     * Sets properties from properties file
     */
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
    /**
     * Add user in database
     * @param username
     * @param password
     * @param adminPassword
     * @return Ack
     */
    public MessageAck add(String username,String password, String adminPassword){
        if (adminPassword.equals(this.passwordAdmin)){
            return broker.add(username, password);
        }
        else{
            return new MessageAck("ERROR","Invalid Admin Password");
        }
    }
    /**
     * Remove user from database
     * @param username
     * @param adminPassword
     * @return Ack
     */
    public MessageAck remove(String username,String adminPassword){
        if (adminPassword.equals(this.passwordAdmin)){
            return broker.remove(username);
        }
        else{
            return new MessageAck("ERROR","Invalid Admin Password");
        }       
    }
    
    /**
     * Modify a user password in database
     * @param username
     * @param password
     * @param newPassword
     * @return Ack
     */
    public MessageAck modify(String username,String password, String newPassword){
        return broker.modify(username, password, newPassword);        
    }
    
    /**
     * Authenticate user in database
     * @param username
     * @param password
     * @return Ack
     */
    public MessageAck authenticate(String username,String password){
        try {
            return broker.authenticate(username, password, InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(EmbeddedServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
    }
    /**
     * List of users storaged in database
     * @param adminPassword
     * @return Arraylist of users
     */
    public ArrayList<User> listUsers(String adminPassword){
        if (adminPassword.equals(this.passwordAdmin)){
            return broker.listUsers();
        }
        else{
            return null;
        }
    }
    /**
     * List of authentications from a specific user
     * @param username
     * @param adminPassword
     * @return Arraylist of authentications
     */
    public ArrayList<Authentication> listAut(String username, String adminPassword){
       if (adminPassword.equals(this.passwordAdmin)){
            return broker.listAut(username);
        }
        else{
            return null;
        }
    }
        
}    