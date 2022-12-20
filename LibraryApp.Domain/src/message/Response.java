package message;

import java.io.Serializable;

/**
 *
 * @author Djordjije
 */
public class Response implements Serializable {
    private Object object;
    private boolean confirmed;
    private Exception exception;
    
    public boolean isConfirmed(){
        return confirmed;
    }
    public Object getObject(){
        return object;
    }
    public Exception getException(){
        return exception;
    }
}
