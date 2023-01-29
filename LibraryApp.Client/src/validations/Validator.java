package validations;

import java.util.ArrayList;
import java.util.List;
import validations.exceptions.ValidationException;

/**
 *
 * @author Djordjije
 */
public abstract class Validator<T> {
    private List<String> errors;
    public void isValid(T entity) throws ValidationException{
        validate(entity);
        if(errors != null && errors.isEmpty() == false)
            throw new ValidationException(getErrorsMessage());
    }
    protected abstract void validate(T entity);
    protected void addError(String message){
        if(errors == null)
            errors = new ArrayList<>();
        errors.add(message);
    }
    private String getErrorsMessage(){
        if(errors == null)
            return "No errors while validating";
        String message = "";
        for (String error : errors) {
            message += error + "\n";
        }
        errors = null;
        return message;
    }
}