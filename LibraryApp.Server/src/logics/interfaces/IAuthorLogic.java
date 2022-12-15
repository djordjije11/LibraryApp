package logics.interfaces;

import java.util.List;
import models.Author;

/**
 *
 * @author Djordjije
 */
public interface IAuthorLogic {
    Author readAuthor(Author author) throws Exception;
    List<Author> readAllAuthors() throws Exception;
}