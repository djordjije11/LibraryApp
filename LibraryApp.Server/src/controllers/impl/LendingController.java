package controllers.impl;

import controllers.interfaces.IController;
import database.sql.brokers.impl.SqlCopyOfBookBroker;
import database.sql.brokers.impl.SqlLendingBroker;
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
    private ILendingLogic lendingLogic;
    public LendingController(){
        lendingLogic = new LendingLogic(new SqlLendingBroker(), new SqlCopyOfBookBroker());
    }
    
    @Override
    public Response handle(Request request) throws Exception {
        Response response = new Response();
        List<Lending> lendings = (List<Lending>)request.getObject();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case CREATELIST:
                dbObject = lendingLogic.createLendings(lendings);
                break;
            case UPDATELIST:
                dbObject = lendingLogic.returnLendings(lendings);
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
