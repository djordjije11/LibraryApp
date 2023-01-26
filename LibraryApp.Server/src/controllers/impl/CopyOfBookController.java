package controllers.impl;

import controllers.interfaces.IController;
import database.sql.brokers.impl.SqlBookBroker;
import database.sql.brokers.impl.SqlCopyOfBookBroker;
import java.util.ArrayList;
import java.util.List;
import jdk.jshell.spi.ExecutionControl;
import logics.impl.BookLogic;
import logics.interfaces.IBookLogic;
import message.Request;
import message.Response;
import models.Book;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class CopyOfBookController implements IController {
    private IBookLogic bookLogic;
    
    public CopyOfBookController(){
        bookLogic = new BookLogic(new SqlBookBroker(), new SqlCopyOfBookBroker());
    }
    
    @Override
    public Response handle(Request request) throws Exception {
        Response response = new Response();
        CopyOfBook copyOfBook = (CopyOfBook)request.getObject();
        Book book = copyOfBook.getBook();
        Long buildingID = copyOfBook.getBuildingId();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case FINDWHERE: //FIND ALL COPIES OF BOOK IN THE BUILDING
                List<CopyOfBook> dbCopiesOfBooks = new ArrayList<>();
                List<Book> dbBooks = bookLogic.findBooks(book);
                for (Book dbBook : dbBooks) {
                    List<CopyOfBook> dbCopiesOfBook = bookLogic.readAllCopiesOfBookInBuilding(new CopyOfBook(dbBook, buildingID));
                    if(dbCopiesOfBook == null || dbCopiesOfBook.isEmpty()){
                        continue;
                    }
                    dbCopiesOfBooks.addAll(dbCopiesOfBook);
                }
                dbObject = dbCopiesOfBooks;
                break;
            case GET: //GET THE COPYOFBOOK BY ID IN THE BUILDING
                dbObject = bookLogic.findCopyOfBookInBuilding(copyOfBook);
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
