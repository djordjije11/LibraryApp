package logics.impl;

import database.sql.brokers.interfaces.IAuthorBroker;
import java.util.List;
import logics.interfaces.IAuthorLogic;
import models.Author;

/**
 *
 * @author Djordjije
 */
public class AuthorLogic implements IAuthorLogic {
    private final IAuthorBroker authorBroker;
    public AuthorLogic(IAuthorBroker authorBroker){
        this.authorBroker = authorBroker;
    }
    @Override
    public Author readAuthor(Author author) throws Exception {
        try{
            authorBroker.openConnection();
            return authorBroker.readAuthor(author);
        } finally{
            authorBroker.closeConnection();
        }
    }
    @Override
    public List<Author> readAllAuthors() throws Exception {
        try{
            authorBroker.openConnection();
            return authorBroker.readAllAuthors(new Author());
        } finally{
            authorBroker.closeConnection();
        }
    }
}
