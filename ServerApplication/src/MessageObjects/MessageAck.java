/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageObjects;

/**
 * Acknowledgement message generated from  add,remove,modify or authenticate action 
 * @author Maximiliano
 */
public class MessageAck {
    
    private String status;
    private String description;
    
    /**
     * MessageAck constructor
     * @param status
     * @param description 
     */
    public MessageAck(String status,String description){
        this.status= status;
        this.description=description;
    
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the descripcion
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
     
     
}
