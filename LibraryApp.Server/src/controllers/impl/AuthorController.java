package controllers.impl;

import controllers.interfaces.IController;
import database.sql.brokers.impl.SqlAuthorBroker;
import jdk.jshell.spi.ExecutionControl;
import logics.impl.AuthorLogic;
import logics.interfaces.IAuthorLogic;
import message.Request;
import message.Response;
import models.Author;

/**
 *
 * @author Djordjije
 */
public class AuthorController implements IController {
    private IAuthorLogic authorLogic;
        
    public AuthorController() {
        authorLogic = new AuthorLogic();
    }
    
    @Override
    public Response handle(Request request)  {
        Response response = new Response();
        Author author = (Author)request.getObject();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case READALL:   //READ ALL AUTHORS
                dbObject = authorLogic.readAllAuthors();
                break;
            case GET:   //GET THE AUTHOR BY ID
                dbObject = authorLogic.findAuthor(author);
                break;
            default:
                throw new ExecutionControl.NotImplementedException("The request method is not able for an Author object.");
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
