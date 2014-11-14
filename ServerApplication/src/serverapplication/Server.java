/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan 
 */
public class Server {
    
    Broker broker;
    int port;
    String passwordAdmin;
    String remoteIP;
    AbstractFactory factory;
    Sender sender;
    Parser parser;
    
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
          this.remoteIP = socket.getRemoteSocketAddress().toString();
          this.remoteIP = this.remoteIP.substring(remoteIP.indexOf("/")+1,remoteIP.indexOf(":"));
          
          out = new PrintWriter(socket.getOutputStream(),true);
          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          
          Command command = parser.parse(in.readLine(),passwordAdmin);
          command.execute(broker,sender,out);
                  
          //System.out.println(in.readLine());
      }
      catch (Exception e) { e.printStackTrace(); }        
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

