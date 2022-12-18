package database.sql.brokers.interfaces;

import java.util.List;
import models.Author;

/**
 *
 * @author Djordjije
 */
public interface IAuthorBroker extends IEntityBroker {
    Author findAuthor(Author author) throws Exception;
    List<Author> readAllAuthors(Author author) throws Exception;
}
