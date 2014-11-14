/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLServer;

import java.util.Properties;
import java.io.*;
import java.net.*;
import Database.Broker;

/**
 *
 * @author Juan 
 */
public class XMLServer {
    
    private Broker broker;
    private int port;
    private String passwordAdmin;
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        // TODO code application logic here
            
        XMLServer server = new XMLServer();
        
    }
    /** 
     * Server Constructor
     */
    public XMLServer(){
         
      setProperties();
      
      ServerSocket server = null;
      Socket socket = null;
      
      try{
          server = new ServerSocket(this.port);
  
          while (true){
              socket = server.accept();  
                      
              new Thread (new Receiver(socket,passwordAdmin,broker)).start();
          }
    
         }
      catch (Exception e) { e.printStackTrace(); }      
    }
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
            this.port=Integer.parseInt(propiedades.getProperty("port"));
   

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

 }