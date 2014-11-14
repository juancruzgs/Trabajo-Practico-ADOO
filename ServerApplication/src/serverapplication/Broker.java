/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.sql.*;
import java.util.ArrayList;
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
    
    private Broker(String url, String userDb, String passwordDb){
        try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url,userDb,passwordDb);
    
        }
        catch (Exception ex)
        { ex.printStackTrace(); }
    }
    
    public MessageAck add(String username,String password){
        try{
        
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+username+"'");
        if (resultSet.next()){
            return new MessageAck("ERROR","User already exists");
        }
        else{
            int resultSet1 = statement.executeUpdate("INSERT INTO usuarios (username,password,timestamp) VALUES ('"+username+"','"
                    +password+"',"+"NOW())");
            return new MessageAck("OK","");
            
            
        }
        }
        catch (SQLException e){ return new MessageAck ("ERROR",e.getMessage()); }
    }    
    
    public MessageAck remove(String username){
        try{
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate("DELETE FROM usuarios WHERE username='"+username+"'");
            if  (resultSet==0){
                 return new MessageAck("ERROR","User does not exists");
            }
            else{
                return new MessageAck("OK","");
            }
        }catch (SQLException e) { return new MessageAck ("ERROR",e.getMessage());}
    
    }
    
    public MessageAck modify(String username,String password, String newPassword){
        try{
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+username+"'and password='"+password+"'");
             if (resultSet.next()){
                  statement.executeUpdate("UPDATE  usuarios SET password='"+newPassword+"'WHERE username='"+username+"'");
                  return new MessageAck("OK","");
             }
             else{
                     return new MessageAck("ERROR","Invalid username or password");
             }
        }catch (SQLException e){ return new MessageAck("Error",e.getMessage());}
    }
    
    public MessageAck authenticate(String username,String password,String hostIP){
        try{
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+username+"'and password='"+password+"'");
            if (resultSet.next()){
                statement.executeUpdate ("INSERT INTO autenticaciones (username,host,timestamp) VALUES ('"+username+"','"+hostIP+"',"+"NOW())");    
                return new MessageAck("OK","");
            }
                else{
                    return new MessageAck("ERROR","Could not authenticate due to invalid username or password");
                }
            }catch (SQLException e) {return new MessageAck("Error",e.getMessage()); }
    }
    
    public ArrayList<User> listUsers(){
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
    
    public ArrayList<Authentication> listAut(String username){
        try {
            Statement statement = connection.createStatement();
            ArrayList<Authentication> autenticaciones = new ArrayList<Authentication>();
            ResultSet resultSet = statement.executeQuery("SELECT host,timestamp FROM autenticaciones WHERE username='"+username+"'");
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
    public static Broker getInstance(String url,String user,String password){   
        if (instance == null){
            instance = new Broker(url,user, password);
        }
    
        return instance;
    }
}