package logics.operations.book;

import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import java.sql.Connection;
import logics.operations.Operation;
import models.Book;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class DeleteBook extends Operation<Book> {
    protected Book book;
    private final IBookBroker bookBroker;
    private final ICopyOfBookBroker copyOfBookBroker;
    
    public DeleteBook(IBookBroker bookBroker, ICopyOfBookBroker copyOfBookBroker){
        this.bookBroker = bookBroker;
        this.copyOfBookBroker = copyOfBookBroker;
    }
    public void setBook(Book book){
        this.book = book;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        Book dbBook = bookBroker.findBook(book, connection);
        if(dbBook == null)
            throw new Exception("The book doesn't exist in the database.");
        if(copyOfBookBroker.getCountOfAllCopiesOfBook(new CopyOfBook(dbBook), connection) > 0)
                throw new Exception("A book cannot be deleted if it has already been copied.");
    }
    @Override
    protected Book executeOperation(Connection connection) throws Exception {
        return bookBroker.deleteBook(book, connection);
    }
    @Override
    protected boolean isTransaction() {
        return true;
    }
}
