package controllers.interfaces;

import message.Request;
import message.Response;

/**
 *
 * @author Djordjije
 */
public interface IController {
    public Response handle(Request request) throws Exception;
}
