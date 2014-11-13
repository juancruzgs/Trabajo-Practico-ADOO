/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Juan 
 */
public class Server {
    
    Broker broker;
    int port;
    String contraseñaAdmin;
    
    /** 
     * Server Constructor
     */
    public Server(){
        
      setProperties();
 
    }
    
    private void setProperties() {
        
        Properties propiedades = new Properties();
        InputStream entrada = null;
        
        try {
            entrada = new FileInputStream("serverConfig.properties");
            // cargamos el archivo de propiedades
            propiedades.load(entrada);
            // obtenemos las propiedades y las imprimimos
            broker = new Broker(propiedades.getProperty("urlconexion"),propiedades.getProperty("usuariobd"),
                                propiedades.getProperty("contraseñabd"));
            
            this.contraseñaAdmin= propiedades.getProperty("contraseña");
            this.port=Integer.parseInt(propiedades.getProperty("puerto"));

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

