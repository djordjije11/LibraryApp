package controllers.impl;

import controllers.interfaces.IController;
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
        bookLogic = new BookLogic();
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
            case CREATE:    //SAVE NEW BOOK
                if(addingAmount > 0){   //PLUS ADD NEW COPIES OF BOOK
                    Book dbBook = bookLogic.createBook(book, addingAmount, buildingID);
                    Long newCurrentAmount = bookLogic.getCountOfCopiesOfBookInBuilding(new CopyOfBook(dbBook, buildingID));
                    dbObject = new BookDto(dbBook, buildingID, newCurrentAmount);
                } else {    //WITHOUT ADDING COPIES OF BOOK
                    dbObject = new BookDto(bookLogic.createBook(book));
                }
                break;
            case READALL:   //READ ALL BOOKS
                dbObject = EntitiesConverter.convertBookListToBookDtoList(bookLogic.readAllBooks());
                break;
            case FINDWHERE: //FIND ALL BOOKS THAT HAVE A SIMILAR TITLE TO THE SEARCHED TEXT
                dbObject = EntitiesConverter.convertBookListToBookDtoList(bookLogic.findBooks(book));
                break;
            case GET: { //GET THE BOOK BY ID WITH THE NUMBER OF COPIES OF BOOK IN THE BUILDING
                Book dbBook = bookLogic.findBook(book);
                Long newCurrentAmount = bookLogic.getCountOfCopiesOfBookInBuilding(new CopyOfBook(dbBook, buildingID));
                dbObject = new BookDto(dbBook, buildingID, newCurrentAmount);
                break;
            }
            case UPDATE:    //UPDATE THE BOOK
                if(bookDto.getIsUpdated() == false && addingAmount > 0){    //BY JUST ADDING NEW COPIES OF BOOK
                    List<CopyOfBook> copiesOfBook = bookLogic.addCopiesOfBookInBuilding(new CopyOfBook(book, buildingID), addingAmount);
                    Long newCurrentAmount = (long)copiesOfBook.size() + currentAmount;
                    dbObject = new BookDto(book, buildingID, newCurrentAmount);
                } else if(bookDto.getIsUpdated() == true && addingAmount == 0){ //BY JUST CHANGING INFORMATIONS ABOUT BOOK
                    dbObject = new BookDto(bookLogic.updateBook(book), buildingID, currentAmount);
                } else if(bookDto.getIsUpdated() == true && addingAmount > 0) { //BY BOTH CHANGING INFORMATIONS ABOUT BOOK AND ADDING NEW COPIES OF BOOK
                    Book dbBook = bookLogic.updateBook(book, addingAmount, buildingID);
                    Long newCurrentAmount = bookLogic.getCountOfCopiesOfBookInBuilding(new CopyOfBook(dbBook, buildingID));
                    dbObject = new BookDto(dbBook, buildingID, newCurrentAmount);
                } else{ //BAD REQUEST
                    response.setConfirmed(false);
                    return response;
                }
                break;
            case DELETE:    //DELETE THE BOOK BY ID
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
