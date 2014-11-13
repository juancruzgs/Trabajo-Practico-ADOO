/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;

/**
 *
 * @author Juan
 */
public class XMLParser implements Parser{
    
    public Command parse(String message){
        try {
            InputSource is = new InputSource(new StringReader(message));
            org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            XPath xpath = XPathFactory.newInstance().newXPath();

            String tipo = (String)xpath.evaluate("@TYPE",xmlDoc.getDocumentElement(),XPathConstants.STRING);
            Command command = new Command(tipo);
            
            if (tipo.equals("ADD")){
                command.addParameter((String)xpath.evaluate("/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/PASSWORD",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/ADM-PASS",xmlDoc.getDocumentElement(),XPathConstants.STRING));
            }
            else
            if (tipo.equals("REMOVE")){
                command.addParameter((String)xpath.evaluate("/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));               
                command.addParameter((String)xpath.evaluate("/ADM-PASS",xmlDoc.getDocumentElement(),XPathConstants.STRING));
            }
            else
            if (tipo.equals("MODIFY")){
                command.addParameter((String)xpath.evaluate("/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/PASSWORD",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/NEW-PASS",xmlDoc.getDocumentElement(),XPathConstants.STRING));
            }
            else
            if (tipo.equals("AUTHENTICATE")){
                command.addParameter((String)xpath.evaluate("/USERNAME",xmlDoc.getDocumentElement(),XPathConstants.STRING));
                command.addParameter((String)xpath.evaluate("/PASSWORD",xmlDoc.getDocumentElement(),XPathConstants.STRING));
            }                
            
            
            
            return command;
            
            
        } catch (Exception ex) {
            return new Command("Unknown Error");
            
        }
    }
    
}
