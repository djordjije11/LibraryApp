package controllers.impl;

import controllers.interfaces.IController;
import jdk.jshell.spi.ExecutionControl;
import logics.interfaces.ILendingLogic;
import message.Request;
import message.Response;

/**
 *
 * @author Djordjije
 */
public class LendingController implements IController {

    private ILendingLogic lendingLogic;
    
    public LendingController(){
        lendingLogic = null;
    }
    @Override
    public Response handle(Request request) throws Exception {
        Response response = new Response();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case CREATE:
                break;
            case UPDATE:
                break;
            default:
                throw new ExecutionControl.NotImplementedException("The request method is not able for a Book object.");
            }
            response.setObject(dbObject);
            response.setConfirmed(true);
            return response;
        } catch(Exception ex){
            response.setConfirmed(false);
            response.setException(ex);
            return response;
        }
    }
    
}
