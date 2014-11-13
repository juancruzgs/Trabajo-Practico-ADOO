/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.io.*;
import java.sql.*;
/**
 *
 * @author Juan
 */
public class Broker {
    /**
     * Broker Creator
     */
    private static Broker instance;
    
    private Broker(String url, String user, String password){
        try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(url,user,password);
            MessageAck mensaje = add("pelaterlareconchadetumadreeeeeeeeeeeeeeeeeeeeee","lalo",connection);
            System.out.println(mensaje.getStatus()+" "+mensaje.getDescripcion());
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
    
    public static Broker getInstance(String url,String user,String password){   
        if (instance == null){
            instance = new Broker(url,user, password);
        }
    
        return instance;
    }
            
     
    




}

