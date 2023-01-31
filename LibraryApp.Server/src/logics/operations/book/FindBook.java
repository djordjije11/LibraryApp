package logics.operations.book;

import database.sql.brokers.impl.SqlBookBroker;
import database.sql.brokers.interfaces.IBookBroker;
import java.sql.Connection;
import logics.operations.Operation;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class FindBook extends Operation<Book> {
    private Book book;
    private final IBookBroker bookBroker;
    
    public FindBook(){
        bookBroker = new SqlBookBroker();
    }
    public void setBook(Book book){
        this.book = book;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected Book executeOperation(Connection connection) throws Exception {
        return bookBroker.findBook(book, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
