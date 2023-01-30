package logics.operations.book;

import database.sql.brokers.interfaces.IAuthorBroker;
import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import models.Book;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class CreateBookAndAddCopies extends CreateBook {
    private final ICopyOfBookBroker copyOfBookBroker;
    private Long amount;
    private Long buildingID;
    public void setAmount(Long amount){
        this.amount = amount;
    }
    public void setBuildingId(Long buildingID){
        this.buildingID = buildingID;
    }
    public CreateBookAndAddCopies(IBookBroker bookBroker, IAuthorBroker authorBroker, ICopyOfBookBroker copyOfBookBroker){
        super(bookBroker, authorBroker);
        this.copyOfBookBroker = copyOfBookBroker;
    }
    @Override
    protected Book executeOperation(Connection connection) throws Exception {
        Book createdBook = bookBroker.createBook(book, connection);
        CopyOfBook copyOfBook = new CopyOfBook(createdBook, buildingID);
        List<CopyOfBook> copiesOfBook = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            copiesOfBook.add(copyOfBook);
        }
        copyOfBookBroker.createCopiesOfBook(copiesOfBook, connection);
        return createdBook;
    }
}