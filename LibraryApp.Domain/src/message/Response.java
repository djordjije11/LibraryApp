package message;

import java.io.Serializable;

/**
 *
 * @author Djordjije
 */
public class Response implements Serializable {
    private Object object;
    private boolean confirmed;
    
    public boolean isConfirmed(){
        return confirmed;
    }
    public Object getObject(){
        return object;
    }
}
