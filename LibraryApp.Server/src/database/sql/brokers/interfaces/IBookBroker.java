package database.sql.brokers.interfaces;

import java.sql.Connection;
import java.util.List;
import models.Book;

/**
 *
 * @author Djordjije
 */
public interface IBookBroker {
    Book createBook(Book book, Connection connection) throws Exception;
    Book findBook(Book book, Connection connection) throws Exception;
    List<Book> findBooks(Book book, Connection connection) throws Exception;
    List<Book> readAllBooks(Book book, Connection connection) throws Exception;
    Book updateBook(Book book, Connection connection) throws Exception;
    Book deleteBook(Book book, Connection connection) throws Exception;
}
