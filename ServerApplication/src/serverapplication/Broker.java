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
    public Broker(String url, String user, String password){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(url,user,password);
          /*  Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");
            
            while (resultSet.next()){
                 System.out.println(resultSet.getString(1));
            } */
        }
        catch (Exception ex)
        { ex.printStackTrace(); }
    }
}
