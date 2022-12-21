package controllers.impl;

import controllers.interfaces.IController;
import database.sql.brokers.impl.SqlBookBroker;
import database.sql.brokers.impl.SqlCopyOfBookBroker;
import helper.EntitiesConverter;
import java.util.List;
import jdk.jshell.spi.ExecutionControl;
import logics.impl.BookLogic;
import logics.interfaces.IBookLogic;
import message.Request;
import message.Response;
import models.Book;
import models.CopyOfBook;
import models.dto.BookDto;

/**
 *
 * @author Djordjije
 */
public class BookController implements IController {
    private IBookLogic bookLogic;
    
    public BookController(){
        bookLogic = new BookLogic(new SqlBookBroker(), new SqlCopyOfBookBroker());
    }
    
    @Override
    public Response handle(Request request)  {
        Response response = new Response();
        BookDto bookDto = (BookDto)request.getObject();
        Book book = bookDto.getBookFromBookDto();
        Long currentAmount = bookDto.getCurrentAmount();
        Long addingAmount = bookDto.getAddingAmount();
        Long buildingID = bookDto.getBuildingId();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case CREATE:
                if(addingAmount > 0){
                    Book dbBook = bookLogic.createBook(book, addingAmount, buildingID);
                    Long newCurrentAmount = bookLogic.getNumberOfCopiesOfBookInBuilding(new CopyOfBook(dbBook.getId(), buildingID));
                    dbObject = new BookDto(dbBook, buildingID, newCurrentAmount);
                } else {
                    dbObject = new BookDto(bookLogic.createBook(book));
                }
                break;
            case READALL:
                dbObject = EntitiesConverter.convertBookListToBookDtoList(bookLogic.readAllBooks());
                break;
            case FINDWHERE:
                dbObject = EntitiesConverter.convertBookListToBookDtoList(bookLogic.findBooks(book));
                break;
            case GET:{
                Book dbBook = bookLogic.findBook(book);
                Long newCurrentAmount = bookLogic.getNumberOfCopiesOfBookInBuilding(new CopyOfBook(dbBook.getId(), buildingID));
                dbObject = new BookDto(dbBook, buildingID, newCurrentAmount);
                break;
            }
            case UPDATE:
                if(bookDto.getIsUpdated() == false && addingAmount > 0){
                    List<CopyOfBook> copiesOfBook = bookLogic.addCopiesOfBookInBuilding(new CopyOfBook(book.getId(), buildingID), addingAmount);
                    Long newCurrentAmount = (long)copiesOfBook.size() + currentAmount;
                    dbObject = new BookDto(book, buildingID, newCurrentAmount);
                } else if(bookDto.getIsUpdated() == true && addingAmount == 0){
                    dbObject = bookLogic.updateBook(book);
                } else if(bookDto.getIsUpdated() == true && addingAmount > 0) {
                    Book dbBook = bookLogic.updateBook(book, addingAmount, buildingID);
                    Long newCurrentAmount = bookLogic.getNumberOfCopiesOfBookInBuilding(new CopyOfBook(dbBook.getId(), buildingID));
                    dbObject = new BookDto(dbBook, buildingID, newCurrentAmount);
                } else{
                    response.setConfirmed(false);
                    return response;
                }
                break;
            case DELETE:
                dbObject = new BookDto(bookLogic.deleteBook(book));
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
