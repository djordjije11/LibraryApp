package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IBookBroker;
import database.sql.sqlmodels.SqlBook;
import java.sql.Connection;
import java.util.List;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class SqlBookBroker extends SqlEntityBroker<Book> implements IBookBroker {
    @Override
    public Book createBook(Book book, Connection connection) throws Exception {
        return create(new SqlBook(book), connection);
    }
    @Override
    public Book findBook(Book book, Connection connection) throws Exception {
        return find(new SqlBook(book), connection);
    }
    @Override
    public List<Book> findBooks(Book book, Connection connection) throws Exception {
        return findEntities(new SqlBook(book), connection);
    }
    @Override
    public List<Book> readAllBooks(Book book, Connection connection) throws Exception {
        return readAll(new SqlBook(book), connection);
    }
    @Override
    public Book updateBook(Book book, Connection connection) throws Exception {
        return update(new SqlBook(book), connection);
    }
    @Override
    public Book deleteBook(Book book, Connection connection) throws Exception {
        return delete(new SqlBook(book), connection);
    }
    @Override
    public boolean checkIfBookExists(Book book, Connection connection) throws Exception {
        return checkIfExists(new SqlBook(book), connection);
    }
}
