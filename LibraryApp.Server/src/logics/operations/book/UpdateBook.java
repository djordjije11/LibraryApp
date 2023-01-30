package logics.operations.book;

import database.sql.brokers.interfaces.IAuthorBroker;
import database.sql.brokers.interfaces.IBookBroker;
import java.sql.Connection;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class UpdateBook extends CreateBook {
    public UpdateBook(IBookBroker bookBroker, IAuthorBroker authorBroker){
        super(bookBroker, authorBroker);
    }
    @Override
    protected Book executeOperation(Connection connection) throws Exception {
        return bookBroker.updateBook(book, connection);
    }
}
