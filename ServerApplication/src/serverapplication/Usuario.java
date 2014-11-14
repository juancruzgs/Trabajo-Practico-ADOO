/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;


import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Maximiliano
 */
public class Usuario {
    
    private String username;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }
    private Timestamp timestamp;
    
    public Usuario(String username,Timestamp timestamp){
        this.username=username;
        this.timestamp=timestamp;
    }
}
