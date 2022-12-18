package database.sql.brokers.interfaces;

import java.util.List;
import models.Book;

/**
 *
 * @author Djordjije
 */
public interface IBookBroker extends IEntityBroker {
    Book createBook(Book book) throws Exception;
    Book findBook(Book book) throws Exception;
    List<Book> findBooks(Book book) throws Exception;
    List<Book> readAllBooks(Book book) throws Exception;
    Book updateBook(Book book) throws Exception;
    Book deleteBook(Book book) throws Exception;
}
