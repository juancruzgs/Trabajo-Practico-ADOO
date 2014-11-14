/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testclient;

import java.net.*;
import java.io.*;

/**
 *
 * @author Juan
 */
public class TestClient {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 
        PrintWriter out;
        BufferedReader in;
        
        try{
            Socket socket = new Socket("localhost",3307);
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //while (true){
            out.println("<MESSAGE TYPE=\"REMOVE\"><USERNAME>juan</USERNAME><ADM-PASS>adminadoo</ADM-PASS></MESSAGE>");
            //out.println("<MESSAGE TYPE=\"ADD\"><USERNAME>juan</USERNAME><PASSWORD>juan123</PASSWORD><ADM-PASS>adminadoo</ADM-PASS></MESSAGE>");
            //out.println("<MESSAGE TYPE=\"MODIFY\"><USERNAME>juan</USERNAME><PASSWORD>juan123</PASSWORD><NEW-PASS>pelater2014</NEW-PASS></MESSAGE>");
            //out.println("<MESSAGE TYPE=\"AUTHENTICATE\"><USERNAME>juan</USERNAME><PASSWORD>juan123</PASSWORD></MESSAGE>");
            //out.println("<MESSAGE TYPE=\"LIST-AUT\"><USERNAME>juan</USERNAME><ADM-PASS>adminadoo</ADM-PASS></MESSAGE>");
            //out.println("<MESSAGE TYPE=\"LIST-USERS\"><ADM-PASS>adminadoo</ADM-PASS></MESSAGE>");
            //}
            
            System.out.println(in.readLine());
        }
        catch (Exception e){ e.printStackTrace(); }
    }
}
