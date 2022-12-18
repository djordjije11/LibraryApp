package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IBookBroker;
import database.sql.sqlmodels.SqlBook;
import helper.EntitiesConverter;
import java.util.List;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class SqlBookBroker extends SqlEntityBroker implements IBookBroker {

    @Override
    public Book createBook(Book book) throws Exception {
        return (Book) create(new SqlBook(book));
    }

    @Override
    public Book findBook(Book book) throws Exception {
        return (Book)find(new SqlBook(book));
    }

    @Override
    public List<Book> findBooks(Book book) throws Exception {
        return EntitiesConverter.<Book>convertList(findEntities(new SqlBook(book)));
    }

    @Override
    public List<Book> readAllBooks(Book book) throws Exception {
        return EntitiesConverter.<Book>convertList(readAll(new SqlBook(book)));
    }

    @Override
    public Book updateBook(Book book) throws Exception {
        return (Book)update(new SqlBook(book));
    }

    @Override
    public Book deleteBook(Book book) throws Exception {
        return (Book)delete(new SqlBook(book));
    }
}
