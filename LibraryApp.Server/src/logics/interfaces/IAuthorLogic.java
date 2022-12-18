package logics.interfaces;

import java.util.List;
import models.Author;

/**
 *
 * @author Djordjije
 */
public interface IAuthorLogic {
    Author findAuthor(Author author) throws Exception;
    List<Author> readAllAuthors() throws Exception;
}