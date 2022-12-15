package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IAuthorBroker;
import helper.EntitiesConverter;
import java.sql.SQLException;
import java.util.List;
import models.Author;
import database.sql.sqlmodels.SqlAuthor;

/**
 *
 * @author Djordjije
 */
public class SqlAuthorBroker extends SqlEntityBroker implements IAuthorBroker  {

    public SqlAuthorBroker() throws SQLException{
        super();
    }
    @Override
    public synchronized Author readAuthor(Author author) throws Exception {
        return (Author)read(new SqlAuthor(author));
    }
    @Override
    public synchronized List<Author> readAllAuthors(Author author) throws Exception {
        return EntitiesConverter.<Author>convertList(readAll(new SqlAuthor(author)));
    }
}
