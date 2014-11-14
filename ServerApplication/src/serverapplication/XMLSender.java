/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Juan
 */
public class XMLSender implements Sender{
    
    public void sendAck(MessageAck response,PrintWriter out){
        out.println("<ACK STATUS=\""+response.getStatus()+"\"><DESC>"+response.getDescripcion()+"</DESC></ACK>");
        
        
    }
    
    public void sendListUsers(ArrayList<User> lista,PrintWriter out ){
        String st = "<LIST-USERS>";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Iterator iterator = lista.iterator();
        while (iterator.hasNext()){
            User usuario = (User)iterator.next();
            st = st+ "<USER><USERNAME>"+usuario.getUsername()+"</USERNAME><TIMESTAMP>"
                    +formatter.format(usuario.getTimestamp())+"</TIMESTAMP></USER>";
        }
        st = st + "</LIST-USERS>";
        out.println(st);
    }
    public void sendListAut(ArrayList<Authentication>lista,PrintWriter out){
        String st = "<LIST-AUT>";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Iterator iterator = lista.iterator();
        while (iterator.hasNext()){
            Authentication autenticacion = (Authentication)iterator.next();
            st= st +"<AUT><HOST>"+autenticacion.getHost()+"</HOST><TIMESTAMP>"
                    +formatter.format(autenticacion.getTimestamp())+"</TIMESTAMP></USER>";
        }
        st= st + "</LIST-AUT>";
        out.println(st);
    
    }   
    
    public void sendError(String error,PrintWriter out){
       
        out.println("<ERROR>"+error+"<ERROR>");
       
    }

}

