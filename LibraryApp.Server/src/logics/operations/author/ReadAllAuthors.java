package logics.operations.author;

import database.sql.brokers.impl.SqlAuthorBroker;
import database.sql.brokers.interfaces.IAuthorBroker;
import java.sql.Connection;
import java.util.List;
import logics.operations.Operation;
import models.Author;

/**
 *
 * @author Djordjije
 */
public class ReadAllAuthors extends Operation<List<Author>> {
    protected final IAuthorBroker authorBroker;
    
    public ReadAllAuthors(){
        authorBroker = new SqlAuthorBroker();
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected List<Author> executeOperation(Connection connection) throws Exception {
        return authorBroker.readAllAuthors(new Author(), connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
