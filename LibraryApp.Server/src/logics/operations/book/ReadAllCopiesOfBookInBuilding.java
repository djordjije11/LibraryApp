package logics.operations.book;

import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import java.sql.Connection;
import java.util.List;
import logics.operations.Operation;
import models.Book;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class ReadAllCopiesOfBookInBuilding extends Operation<List<CopyOfBook>> {
    protected CopyOfBook copyOfBook;
    private final IBookBroker bookBroker;
    private final ICopyOfBookBroker copyOfBookBroker;
    public ReadAllCopiesOfBookInBuilding(IBookBroker bookBroker, ICopyOfBookBroker copyOfBookBroker){
        this.bookBroker = bookBroker;
        this.copyOfBookBroker = copyOfBookBroker;
    }
    public void setCopyOfBook(CopyOfBook copyOfBook){
        this.copyOfBook = copyOfBook;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        Book book = copyOfBook.getBook();
        if(book == null || bookBroker.findBook(book, connection) == null)
            throw new Exception("The book is not in the database.");
    }
    @Override
    protected List<CopyOfBook> executeOperation(Connection connection) throws Exception {
        return copyOfBookBroker.readAllCopiesOfBookInBuilding(copyOfBook, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
