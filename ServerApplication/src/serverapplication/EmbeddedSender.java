/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class EmbeddedSender implements Sender{
     
    public void sendAck(MessageAck response,PrintWriter out){}
    public void sendListUsers(ArrayList< User> lista,PrintWriter out ){}
    public void sendListAut(ArrayList<Authentication>lista,PrintWriter out){}
    public void sendError(String error,PrintWriter out){}
    
}
