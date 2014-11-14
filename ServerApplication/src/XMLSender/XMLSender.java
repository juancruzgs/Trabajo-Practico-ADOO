/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLSender;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import MessageObjects.Authentication;
import MessageObjects.MessageAck;
import MessageObjects.User;

/**
 * Response to a XML message
 * @author Juan
 */

public class XMLSender{
     
    private PrintWriter out;

    public XMLSender(PrintWriter out) {
        this.out = out;
    }
    /**
     * Sends an acknowledgment message.It could be ok message or error message
     * @param response Response to add,modify,remove or authenticate messages
     * @param out Response to the client
     */

    public void sendAck(MessageAck response){
        out.println("<ACK STATUS=\""+response.getStatus()+"\"><DESC>"+response.getDescription()+"</DESC></ACK>");
        
        
    }
    /**
     * Response to a list users message
     * @param list Users storaged in DataBase
     * @param out Response to the client
     */
    public void sendListUsers(ArrayList<User> list){
        String st = "<LIST-USERS>";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            User usuario = (User)iterator.next();
            st = st+ "<USER><USERNAME>"+usuario.getUsername()+"</USERNAME><TIMESTAMP>"
                    +formatter.format(usuario.getTimestamp())+"</TIMESTAMP></USER>";
        }
        st = st + "</LIST-USERS>";
        out.println(st);
    }
    /**
     * Response to a List authentication message
     * @param list Authentications from a specific user
     * @param out Response to the client 
     */
    public void sendListAut(ArrayList<Authentication> list){
        String st = "<LIST-AUT>";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Authentication autenticacion = (Authentication)iterator.next();
            st= st +"<AUT><HOST>"+autenticacion.getHost()+"</HOST><TIMESTAMP>"
                    +formatter.format(autenticacion.getTimestamp())+"</TIMESTAMP></USER>";
        }
        st= st + "</LIST-AUT>";
        out.println(st);
    
    }   
    /**
     * Sends a message error 
     * @param error Error message
     * @param out Response to the client
     */
    public void sendError(String error){
       
        out.println("<ERROR>"+error+"<ERROR>");
       
    }

}

