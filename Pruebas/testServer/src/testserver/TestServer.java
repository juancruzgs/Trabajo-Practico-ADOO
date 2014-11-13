/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testserver;

import java.io.*;
import java.net.*;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
/**
 *
 * @author Juan
 */
public class TestServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerSocket servidor = null;
        Socket socket = null;
        PrintWriter out;
        BufferedReader in;
        
        try{
            servidor = new ServerSocket(3307);
            socket = servidor.accept();
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //out.println("servidor");
            //while (true){
            //System.out.println(in.readLine());
            //}
            
            String message = in.readLine();
            InputSource is = new InputSource(new StringReader(message));
            org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            String tipo = (String)xpath.evaluate("@TYPE",xmlDoc.getDocumentElement(),XPathConstants.STRING);
            
            System.out.println(tipo);
            
        }
        catch (Exception e){ e.printStackTrace(); }
    }
}
