package ch.schumm.security;


/**
 * Excpetion, wenn innerhalb der R-PDP etwas schief gelaufen ist. 
 * @author C709360
 *
 */
public class PolicyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PolicyException(Throwable e) {
        super(e); 
    }
    
    
    public PolicyException(String message, Throwable cause){
        super(message, cause); 
    }
    
    
    public PolicyException(String message){
        super(message); 
    }



}
