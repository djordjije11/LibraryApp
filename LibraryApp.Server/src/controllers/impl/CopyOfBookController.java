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
import models.dto.CopiesOfBookDto;

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
        CopiesOfBookDto copyOfBookDto = (CopiesOfBookDto)request.getObject();
        Book book = copyOfBookDto.getBookFromCopiesOfBookDto();
        Long buildingID = copyOfBookDto.getBuildingId();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case CREATE:
                break;
            case READALL:
                break;
            case FINDWHERE:
                List<CopiesOfBookDto> copiesOfBookDto = new ArrayList<>();
                List<Book> books = bookLogic.findBooks(book);
                for (Book dbBook : books) {
                    List<CopyOfBook> dbCopiesOfBook = bookLogic.findCopiesOfBookInBuilding(new CopyOfBook(dbBook, buildingID));
                    if(dbCopiesOfBook == null || dbCopiesOfBook.isEmpty()){
                        continue;
                    }
                    copiesOfBookDto.add(new CopiesOfBookDto(dbBook, buildingID, dbCopiesOfBook));
                }
                dbObject = copiesOfBookDto;
                break;
            case GET:
                Long copyOfBookID = copyOfBookDto.getCopyOfBookId();
                CopyOfBook copyOfBook = bookLogic.findCopyOfBookInBuilding(new CopyOfBook(copyOfBookID, buildingID));
                CopiesOfBookDto dbCopiesOfBookDto = new CopiesOfBookDto(copyOfBook.getBook(), buildingID);
                dbCopiesOfBookDto.setCopyOfBookId(copyOfBook.getId());
                dbObject = dbCopiesOfBookDto;
                break;
            case UPDATE:
                break;
            case DELETE:
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
