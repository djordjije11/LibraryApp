package logics.impl;

import java.util.List;
import logics.interfaces.IAuthorLogic;
import models.Author;
import logics.operations.author.FindAuthor;
import logics.operations.author.ReadAllAuthors;

/**
 *
 * @author Djordjije
 */
public class AuthorLogic implements IAuthorLogic {
    @Override
    public Author findAuthor(Author author) throws Exception {
        FindAuthor operation = new FindAuthor();
        operation.setAuthor(author);
        return operation.execute();
    }
    @Override
    public List<Author> readAllAuthors() throws Exception {
        return new ReadAllAuthors().execute();
    }
}
