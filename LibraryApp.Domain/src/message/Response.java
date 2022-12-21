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
    public void setConfirmed(boolean confirmed){
        this.confirmed = confirmed;
    }
    public Object getObject(){
        return object;
    }
    public void setObject(Object object){
        this.object = object;
    }
    public Exception getException(){
        return exception;
    }
    public void setException(Exception exception){
        this.exception = exception;
    }
}
