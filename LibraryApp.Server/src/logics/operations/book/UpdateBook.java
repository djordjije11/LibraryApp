package logics.operations.book;

import java.sql.Connection;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class UpdateBook extends CreateBook {
    @Override
    protected Book executeOperation(Connection connection) throws Exception {
        return bookBroker.updateBook(book, connection);
    }
}
