/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import java.util.Date;

/**
 *
 * @author Maximiliano
 */
public class Usuario {
    
    String username;
    Date timestamp;
    
    public Usuario(String username,Date timestamp){
        this.username=username;
        this.timestamp=timestamp;
    }
}