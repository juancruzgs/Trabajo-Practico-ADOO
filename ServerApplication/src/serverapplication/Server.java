/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.*;
import java.net.*;

/**
 *
 * @author Juan 
 */
public class Server {
    
    Broker broker;
    int port;
    String passwordAdmin;
    String mode; //SERA XML O EMBEDDED, CREAR LA FACTORY CORRESPONDIENTE
    
    /** 
     * Server Constructor
     */
    public Server(){
         
      setProperties();
      waitMessage();
 
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
            // obtenemos las propiedades y las imprimimos

            this.broker = Broker.getInstance(propiedades.getProperty("urlconnection"),propiedades.getProperty("userbd"),
                                propiedades.getProperty("passwordbd"));

            
            this.passwordAdmin= propiedades.getProperty("passwordadmin");
            this.port=Integer.parseInt(propiedades.getProperty("port"));
            this.mode=propiedades.getProperty("mode"); //CREAR FACTORY

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
     * Waits for a Client message
     */
    private void waitMessage(){
        
      ServerSocket servidor = null;
      Socket socket = null;
      PrintWriter out;
      BufferedReader in;    
      
      try{
          servidor = new ServerSocket(this.port);
          socket = servidor.accept();
          out = new PrintWriter(socket.getOutputStream(),true);
          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          
          System.out.println(in.readLine());
      }
      catch (Exception e) { e.printStackTrace(); }        
    }

 }

