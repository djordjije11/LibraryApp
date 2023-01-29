package validations.exceptions;

/**
 *
 * @author Djordjije
 */
public class ValidationException extends Exception {
    public ValidationException(String message){
        super("Nevalidni podaci:\n" + message);
    }
}
