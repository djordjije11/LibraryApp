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
public class ReadAllBooks extends Operation<List<Book>> {
    private final IBookBroker bookBroker;
    
    public ReadAllBooks(){
        bookBroker = new SqlBookBroker();
    }
    
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected List<Book> executeOperation(Connection connection) throws Exception {
        return bookBroker.readAllBooks(new Book(), connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
