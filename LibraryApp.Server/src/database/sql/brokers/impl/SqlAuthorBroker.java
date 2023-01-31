package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IAuthorBroker;
import java.util.List;
import models.Author;
import database.sql.sqlmodels.SqlAuthor;
import java.sql.Connection;

/**
 *
 * @author Djordjije
 */
public class SqlAuthorBroker extends SqlEntityBroker<Author> implements IAuthorBroker  {

    public SqlAuthorBroker() {
        super();
    }
    @Override
    public synchronized Author findAuthor(Author author, Connection connection) throws Exception {
        return find(new SqlAuthor(author), connection);
    }
    @Override
    public synchronized List<Author> readAllAuthors(Author author, Connection connection) throws Exception {
        return readAll(new SqlAuthor(author), connection);
    }

    @Override
    public boolean checkIfAuthorExists(Author author, Connection connection) throws Exception {
        return checkIfExists(new SqlAuthor(author), connection);
    }
}
