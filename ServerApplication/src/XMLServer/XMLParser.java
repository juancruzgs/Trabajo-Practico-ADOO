/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLServer;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import Commands.Command;
import Commands.CommandAdd;
import Commands.CommandAuthenticate;
import Commands.CommandError;
import Commands.CommandListAut;
import Commands.CommandListUsers;
import Commands.CommandModify;
import Commands.CommandRemove;

/**
 * Parse a XML documment
 * @author Juan
 */

public class XMLParser{
    
    private String passwordAdmin;
    private String remoteIP;

    public XMLParser(String passwordAdmin, String remoteIP) {
        this.passwordAdmin = passwordAdmin;
        this.remoteIP = remoteIP;
    }
    /**
     * Parse a XML file 
     * @param message action that client wants to be done
     * @param passwordAdmin p
     * @param remoteIP ip from the client
     * @return 
     */
    public Command parse(String message){
        try {
            InputSource is = new InputSource(new StringReader(message));
            org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            XPath xpath = XPathFactory.newInstance().newXPath();

            String tipo = (String)xpath.evaluate("@TYPE",xmlDoc.getDocumentElement(),XPathConstants.STRING);
            Command command;
            
            String admPass = (String)xpath.evaluate("/MESSAGE/ADM-PASS",xmlDoc.getDocumentElement(),XPathConstants.STRING);            
            
            if ((tipo.equals("ADD")) && (admPass.equals(passwordAdmin))){
                command = new CommandAdd();
                command.addParameter((String)xpath.evaluate("/MESSAGE/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/MESSAGE/PASSWORD",xmlDoc.getDocumentElement(),XPathConstants.STRING));

            }
            else
            if ((tipo.equals("REMOVE")) && (admPass.equals(passwordAdmin))){
                command = new CommandRemove();
                command.addParameter((String)xpath.evaluate("/MESSAGE/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));               
            }
            else
            if (tipo.equals("MODIFY")){
                command = new CommandModify();
                command.addParameter((String)xpath.evaluate("/MESSAGE/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/MESSAGE/PASSWORD",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/MESSAGE/NEW-PASS",xmlDoc.getDocumentElement(),XPathConstants.STRING));
            }
            else
            if (tipo.equals("AUTHENTICATE")){
                command = new CommandAuthenticate();
                command.addParameter((String)xpath.evaluate("/MESSAGE/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/MESSAGE/PASSWORD",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter(remoteIP);
            }           
            else
            if ((tipo.equals("LIST-USERS")) && (admPass.equals(passwordAdmin))){
                command = new CommandListUsers();
            }         
            else
            if ((tipo.equals("LIST-AUT"))  && (admPass.equals(passwordAdmin))){
                command = new CommandListAut();
                command.addParameter((String)xpath.evaluate("/MESSAGE/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));
            }     
            else
                command = new CommandError();
            
            return command;
            
            
        } catch (Exception ex) {
            return new CommandError();
            
        }
    }
    
}
