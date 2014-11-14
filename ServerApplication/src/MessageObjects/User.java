/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageObjects;


import java.sql.Timestamp;

/**
 * Returns a specific user storaged in the database
 * @author Maximiliano
 */
public class User {
    
    private String username;
    /**
     * 
     * @return Datetime generated when user was added 
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }
    private Timestamp timestamp;
    /**
     * User constructor
     * @param username
     * @param timestamp 
     */
    public User(String username,Timestamp timestamp){
        this.username=username;
        this.timestamp=timestamp;
    }
}