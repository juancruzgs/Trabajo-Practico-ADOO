/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */        
package testbd;

import java.io.*;
import java.sql.*;

/**
 *
 * @author Juan
 */
public class TestBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autenticacion","root","admin");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");
            
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        { ex.printStackTrace(); }
        
    }
}
