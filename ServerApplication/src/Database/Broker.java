/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import MessageObjects.Authentication;
import MessageObjects.MessageAck;
import MessageObjects.User;
/**
 * Interactor between xml server,embedded server and database, manage all add,modify,remove,authenticate,list users and list aut actions
 * @author Juan
 */
public class Broker {
    
    private static Broker instance;
    private Connection connection; 
    
    /**
     * Broker Creator
     */
    
    private Broker(String url, String userDb, String passwordDb){
        try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url,userDb,passwordDb);
    
        }
        catch (Exception ex)
        { ex.printStackTrace(); }
    }
    /**
     * Add user to database
     * 
     * @param username 
     * @param password
     * @return Ack
     */
    
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
    /**
     * Remove user from database
     * @param username
     * @return Ack
     */
    public MessageAck remove(String username){
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+username+"'");
            if (resultSet.next()){
               statement.executeUpdate("DELETE FROM autenticaciones WHERE username='"+username+"'"); 
               statement.executeUpdate("DELETE FROM usuarios WHERE username='"+username+"'");
               return new MessageAck("OK","");
            }
            else{
                 return new MessageAck("ERROR","User does not exists");
            }
        }catch (SQLException e) { return new MessageAck ("ERROR",e.getMessage());}
    
    }
    /**
     * Modify user password in database
     * @param username
     * @param password
     * @param newPassword
     * @return ack
     */
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
    /**
     * Authenticate a user 
     * @param username
     * @param password
     * @param hostIP
     * @return Ack
     */
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
    /**
     * List of users stored in database
     * @return Arraylist  with Users storaged in database 
     */
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
    /**
     *  List of authentication from a specific user 
     * @param username
     * @return Arraylist with Authentications from a specific user 
     */
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
    /**
     *  Singleton 
     * @param url 
     * @param user 
     * @param password
     * @return 
     */
    public static Broker getInstance(String url,String user,String password){   
        if (instance == null){
            instance = new Broker(url,user, password);
        }
    
        return instance;
    }
}