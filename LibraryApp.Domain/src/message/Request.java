package message;

import java.io.Serializable;

/**
 *
 * @author Djordjije
 */
public class Request implements Serializable {
    private Object object;
    private ModelElement modelElement;
    private Method method;
    
    public Request(Object object, ModelElement modelElement, Method method){
        this.object = object;
        this.modelElement = modelElement;
        this.method = method;
    }
    public Object getObject(){
        return object;
    }
    public ModelElement getModelElement(){
        return modelElement;
    }
    public Method getMethod(){
        return method;
    }

    @Override
    public String toString() {
        return "ModelElement: " + modelElement + "\nMethod: " + method + "\nObjekat: " + object;
    }
    
    
}
