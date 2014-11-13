/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Juan
 */
public class Broker {
    /**
     * Broker Creator
     */
    private static Broker instance;
    
    private Broker(String url, String user, String password)/*,String host)*/{
        try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(url,user,password);
           // MessageAck mensaje = add("pelaterlareconchadetumadreeeeeeeeeeeeeeeeeeeeee","lalo",connection);
           // MessageAck mensaje = remove("aquiles",connection);
           // MessageAck mensaje = modify("juan","juan123","pelater2014dasdasdafafdsgsgssfsfsfs",connection);
           // MessageAck mensaje = authenticate("adolfo hitler","juan123","192.0.0.0",connection);
           //System.out.println(mensaje.getStatus()+" "+mensaje.getDescripcion());
            listUsers(connection);
          /*  Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");
            
            while (resultSet.next()){
                 System.out.println(resultSet.getString(1));
            } */
        }
        catch (Exception ex)
        { ex.printStackTrace(); }
    }
    
    public MessageAck add(String nombreUsuario,String contraseña, Connection connection) throws SQLException{
        try{
        
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+nombreUsuario+"'");
        if (resultSet.next()){
            return new MessageAck("Error","User already exists");
        }
        else{
            int resultSet1 = statement.executeUpdate("INSERT INTO usuarios (username,password,timestamp) VALUES ('"+nombreUsuario+"','"
                    +contraseña+"',"+"NOW())");
            return new MessageAck("OK","");
            
            
        }
        }
        catch (SQLException e){ return new MessageAck ("Error",e.getMessage()); }
    }    
    
    public MessageAck remove(String nombreUsuario,Connection connection){
        try{
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate("DELETE FROM usuarios WHERE username='"+nombreUsuario+"'");
            if  (resultSet==0){
                 return new MessageAck("Error","User does not exists");
            }
            else{
                return new MessageAck("OK","");
            }
        }catch (SQLException e) { return new MessageAck ("Error",e.getMessage());}
    
    }
    
    public MessageAck modify(String nombreUsuario,String contraseña, String nuevaContraseña,Connection connection){
        try{
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+nombreUsuario+"'");
             if (resultSet.next()){
                 int resultSet2 = statement.executeUpdate("UPDATE  usuarios SET password='"+nuevaContraseña+"'WHERE username='"+nombreUsuario+"' and password='"+contraseña+"'");
                 if (resultSet2!=0){
                     return new MessageAck("OK","");
                 }
                 else{
                     return new MessageAck("Error","Invalid password");
                 }
             }else{
                 return new MessageAck("Error","User does not exists");
             }
             
        }catch (SQLException e){ return new MessageAck("Error",e.getMessage());}
    }
    
    public MessageAck authenticate(String nombreUsuario,String contraseña,String host,Connection connection) throws SQLException{
        try{
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username FROM usuarios WHERE username='"+nombreUsuario+"'and password='"+contraseña+"'");
            if (resultSet.next()){
                statement.executeUpdate ("INSERT INTO autenticaciones (username,host,timestamp) VALUES ('"+nombreUsuario+"','"+host+"',"+"NOW())");    
                return new MessageAck("OK","");
            }
                else{
                    return new MessageAck("Error","Could not authenticate due to invalid username or password");
                }
            }catch (SQLException e) {return new MessageAck("Error",e.getMessage()); }
    }
    
    public ArrayList listUsers(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
          ResultSet resultSet = statement.executeQuery("SELECT username,timestamp FROM usuarios");
          while (resultSet.next()){
            Usuario usuario = new Usuario(resultSet.getString("username"),resultSet.getDate("timestamp"));
            usuarios.add(usuario);
          }
        return usuarios;
    }
    
    public ArrayList listAut(String nombreUsuario,Connection connection){
        
    }
    public static Broker getInstance(String url,String user,String password)/*,String host)*/{   
        if (instance == null){
            instance = new Broker(url,user, password)/*,host)*/;
        }
    
        return instance;
    }
    
    
     
    




}

