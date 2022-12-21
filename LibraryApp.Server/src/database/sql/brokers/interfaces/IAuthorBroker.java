package database.sql.brokers.interfaces;

import java.sql.Connection;
import java.util.List;
import models.Author;

/**
 *
 * @author Djordjije
 */
public interface IAuthorBroker {
    Author findAuthor(Author author, Connection connection) throws Exception;
    List<Author> readAllAuthors(Author author, Connection connection) throws Exception;
}
