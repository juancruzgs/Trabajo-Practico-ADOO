/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageObjects;

import java.sql.Timestamp;

/**
 * Return an authentication from a specific user 
 * @author Maximiliano
 */
public class Authentication {

   
    private String host;
    private Timestamp timestamp;
    
    /**
     * User constructor
     * @param host
     * @param timestamp 
     */
    public Authentication(String host, Timestamp timestamp) {
        this.host = host;
        this.timestamp = timestamp;
    }
    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}