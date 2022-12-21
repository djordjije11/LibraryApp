package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IAuthorBroker;
import helper.EntitiesConverter;
import java.sql.SQLException;
import java.util.List;
import models.Author;
import database.sql.sqlmodels.SqlAuthor;
import java.sql.Connection;

/**
 *
 * @author Djordjije
 */
public class SqlAuthorBroker extends SqlEntityBroker implements IAuthorBroker  {

    public SqlAuthorBroker() {
        super();
    }
    @Override
    public synchronized Author findAuthor(Author author, Connection connection) throws Exception {
        return (Author)find(new SqlAuthor(author), connection);
    }
    @Override
    public synchronized List<Author> readAllAuthors(Author author, Connection connection) throws Exception {
        return EntitiesConverter.<Author>convertList(readAll(new SqlAuthor(author), connection));
    }
}
