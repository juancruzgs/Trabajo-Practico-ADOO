/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Juan
 */
public class Broker {
    /**
     * Broker Creator
     */
    private static Broker instance;
    private Connection connection; 
    
    private Broker(String url, String user, String password)/*,String host)*/{
        try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url,user,password);
           // MessageAck mensaje = add("pelaterlareconchadetumadreeeeeeeeeeeeeeeeeeeeee","lalo",connection);
           // MessageAck mensaje = remove("aquiles",connection);
           // MessageAck mensaje = modify("lalo","asdf","juan123");
           // MessageAck mensaje = authenticate("adolfo hitler","juan123","192.0.0.0",connection);
           //System.out.println(mensaje.getStatus()+" "+mensaje.getDescripcion());
            
           /* ArrayList list = listAut("juan");
           
            Iterator<Autenticacion> iter2 = list.iterator();
            while(iter2.hasNext()){
                    Autenticacion aut1 = (Autenticacion)iter2.next();
                    System.out.println(aut1.getHost()+"|"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aut1.getTimestamp()));
            }*/
            
    
        }
        catch (Exception ex)
        { ex.printStackTrace(); }
    }
    
    public MessageAck add(String nombreUsuario,String contraseña){
        try{
        
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+nombreUsuario+"'");
        if (resultSet.next()){
            return new MessageAck("ERROR","User already exists");
        }
        else{
            int resultSet1 = statement.executeUpdate("INSERT INTO usuarios (username,password,timestamp) VALUES ('"+nombreUsuario+"','"
                    +contraseña+"',"+"NOW())");
            return new MessageAck("OK","");
            
            
        }
        }
        catch (SQLException e){ return new MessageAck ("ERROR",e.getMessage()); }
    }    
    
    public MessageAck remove(String nombreUsuario){
        try{
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate("DELETE FROM usuarios WHERE username='"+nombreUsuario+"'");
            if  (resultSet==0){
                 return new MessageAck("ERROR","User does not exists");
            }
            else{
                return new MessageAck("OK","");
            }
        }catch (SQLException e) { return new MessageAck ("ERROR",e.getMessage());}
    
    }
    
    public MessageAck modify(String nombreUsuario,String contraseña, String nuevaContraseña){
        try{
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+nombreUsuario+"'and password='"+contraseña+"'");
             if (resultSet.next()){
                  statement.executeUpdate("UPDATE  usuarios SET password='"+nuevaContraseña+"'WHERE username='"+nombreUsuario+"'");
                  return new MessageAck("OK","");
             }
             else{
                     return new MessageAck("ERROR","Invalid username or password");
             }
        }catch (SQLException e){ return new MessageAck("Error",e.getMessage());}
    }
    
    public MessageAck authenticate(String nombreUsuario,String contraseña,String host){
        try{
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+nombreUsuario+"'and password='"+contraseña+"'");
            if (resultSet.next()){
                statement.executeUpdate ("INSERT INTO autenticaciones (username,host,timestamp) VALUES ('"+nombreUsuario+"','"+host+"',"+"NOW())");    
                return new MessageAck("OK","");
            }
                else{
                    return new MessageAck("ERROR","Could not authenticate due to invalid username or password");
                }
            }catch (SQLException e) {return new MessageAck("Error",e.getMessage()); }
    }
    
    public ArrayList listUsers(){
        try {
            Statement statement = connection.createStatement();
            ArrayList<User> usuarios = new ArrayList<User>();
            ResultSet resultSet = statement.executeQuery("SELECT username,timestamp FROM usuarios");
            while (resultSet.next()){
                User usuario = new User(resultSet.getString("username"),resultSet.getTimestamp("timestamp"));
                usuarios.add(usuario);
              }
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList listAut(String nombreUsuario){
        try {
            Statement statement = connection.createStatement();
            ArrayList<Authentication> autenticaciones = new ArrayList<Authentication>();
            ResultSet resultSet = statement.executeQuery("SELECT host,timestamp FROM autenticaciones WHERE username='"+nombreUsuario+"'");
            while (resultSet.next()){
                Authentication autenticacion = new Authentication (resultSet.getString("host"),resultSet.getTimestamp("timestamp"));
                autenticaciones.add(autenticacion);
            }
            return autenticaciones;
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static Broker getInstance(String url,String user,String password)/*,String host)*/{   
        if (instance == null){
            instance = new Broker(url,user, password)/*,host)*/;
        }
    
        return instance;
    }
    
    
     
    




}

