/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.PrintWriter;
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
    
    public void sendListUsers(ArrayList<Usuario> lista,PrintWriter out ){
        String st = "<LIST-USERS>";
        Iterator iterator = lista.iterator();
        while (iterator.hasNext()){
            Usuario usuario = (Usuario)iterator.next();
            st = st+ "<USER><USERNAME>"+usuario.getUsername()+"</USERNAME><TIMESTAMP>"+"formato"+"</TIMESTAMP></USER>";
        }
        st = st + "</LIST-USERS>";
        out.println(st);
    }
    public void sendListAut(ArrayList<Autenticacion>lista,PrintWriter out){}   
}
