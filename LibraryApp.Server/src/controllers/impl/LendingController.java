package controllers.impl;

import controllers.interfaces.IController;
import java.util.List;
import jdk.jshell.spi.ExecutionControl;
import logics.impl.LendingLogic;
import logics.interfaces.ILendingLogic;
import message.Request;
import message.Response;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public class LendingController implements IController {
    private final ILendingLogic lendingLogic;
    public LendingController(){
        lendingLogic = new LendingLogic();
    }
    
    @Override
    public Response handle(Request request) throws Exception {
        Response response = new Response();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case CREATELIST:    //SAVE ALL LENDINGS
            {
                List<Lending> lendings = (List<Lending>)request.getObject();
                dbObject = lendingLogic.createLendings(lendings);
                break;
            }
            case UPDATELIST:    //SAVE ALL RETURNINGS OF LENDINGS
            {
                List<Lending> lendings = (List<Lending>)request.getObject();
                dbObject = lendingLogic.returnLendings(lendings);
                break;
            }
            case FINDWHERE: //GET ALL UNRETURNED LENDINGS BY MEMBER
                Lending lending = (Lending)request.getObject();
                dbObject = lendingLogic.findUnreturnedLendingsByMember(lending);
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
