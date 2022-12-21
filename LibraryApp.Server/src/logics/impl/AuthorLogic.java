package logics.impl;

import database.sql.brokers.interfaces.IAuthorBroker;
import database.sql.connection.SqlConnectionFactory;
import java.util.List;
import logics.interfaces.IAuthorLogic;
import models.Author;
import java.sql.Connection;

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
    public Author findAuthor(Author author) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return authorBroker.findAuthor(author, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public List<Author> readAllAuthors() throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return authorBroker.readAllAuthors(new Author(), connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
}
