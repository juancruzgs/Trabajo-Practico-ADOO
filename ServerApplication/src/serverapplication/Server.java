/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.util.Properties;
import java.io.*;
import java.net.*;

/**
 *
 * @author Juan 
 */
public class Server {
    
    private Broker broker;
    private int port;
    private String passwordAdmin;
    private AbstractFactory factory;
    private Sender sender;
    private Parser parser;
    
    /** 
     * Server Constructor
     */
    public Server(){
         
      setProperties();
      
      ServerSocket servidor = null;
      Socket socket = null;
      
      try{
          servidor = new ServerSocket(this.port);
  
          while (true){
              socket = servidor.accept();  
                      
              new Thread (new Receiver(socket,passwordAdmin,parser,sender,broker)).start();
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
            // cargamos el archivo de propiedades
            propiedades.load(entrada);
            // obtenemos las propiedades y las almacenamos

            this.broker = Broker.getInstance(propiedades.getProperty("urlconnection"),propiedades.getProperty("userbd"),
                                propiedades.getProperty("passwordbd"));

                                
            this.passwordAdmin= propiedades.getProperty("passwordadmin");
            this.port=Integer.parseInt(propiedades.getProperty("port"));
   
            //Crea el sender y el parser
            createFactory(propiedades.getProperty("mode"));
            this.sender = factory.createSender();
            this.parser = factory.createParser();

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
     * Creates the suitable sender and parser
     * @param mode XML or Embedded
     */
    private void createFactory(String mode){
        if (mode.toLowerCase().equals("xml")) {
             this.factory = new FactoryXML();
             
        }
        else
        if (mode.toLowerCase().equals("embedded")) {
            this.factory = new FactoryEmbedded();
        }
    }

 }

