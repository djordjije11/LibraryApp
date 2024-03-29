package logics.operations.book;

import database.sql.brokers.impl.SqlBookBroker;
import database.sql.brokers.impl.SqlCopyOfBookBroker;
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
    private Book book;
    private final IBookBroker bookBroker;
    private final ICopyOfBookBroker copyOfBookBroker;
    
    public DeleteBook(){
        bookBroker = new SqlBookBroker();
        copyOfBookBroker = new SqlCopyOfBookBroker();
    }
    public void setBook(Book book){
        this.book = book;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        if(bookBroker.checkIfBookExists(book, connection) == false){
            throw new Exception("The book doesn't exist in the database.");
        }
        if(copyOfBookBroker.getCountOfAllCopiesOfBook(new CopyOfBook(book), connection) > 0){
            throw new Exception("A book cannot be deleted if it has already been copied.");
        }
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
