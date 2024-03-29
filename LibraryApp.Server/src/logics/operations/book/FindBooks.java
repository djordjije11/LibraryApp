package logics.operations.book;

import database.sql.brokers.impl.SqlBookBroker;
import database.sql.brokers.interfaces.IBookBroker;
import java.sql.Connection;
import java.util.List;
import logics.operations.Operation;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class FindBooks extends Operation<List<Book>> {
    private final IBookBroker bookBroker;
    private Book book;
    
    public FindBooks(){
        this.bookBroker = new SqlBookBroker();
    }
    
    public void setBook(Book book){
        this.book = book;
    }
    @Override
    protected List<Book> executeOperation(Connection connection) throws Exception {
        return bookBroker.findBooks(book, connection);
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
