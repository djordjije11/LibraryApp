package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IBookBroker;
import database.sql.sqlmodels.SqlBook;
import helper.EntitiesConverter;
import java.sql.Connection;
import java.util.List;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class SqlBookBroker extends SqlEntityBroker implements IBookBroker {

    @Override
    public Book createBook(Book book, Connection connection) throws Exception {
        return (Book)create(new SqlBook(book), connection);
    }

    @Override
    public Book findBook(Book book, Connection connection) throws Exception {
        return (Book)find(new SqlBook(book), connection);
    }

    @Override
    public List<Book> findBooks(Book book, Connection connection) throws Exception {
        return EntitiesConverter.<Book>convertList(findEntities(new SqlBook(book), connection));
    }

    @Override
    public List<Book> readAllBooks(Book book, Connection connection) throws Exception {
        return EntitiesConverter.<Book>convertList(readAll(new SqlBook(book), connection));
    }

    @Override
    public Book updateBook(Book book, Connection connection) throws Exception {
        return (Book)update(new SqlBook(book), connection);
    }

    @Override
    public Book deleteBook(Book book, Connection connection) throws Exception {
        return (Book)delete(new SqlBook(book), connection);
    }
}
