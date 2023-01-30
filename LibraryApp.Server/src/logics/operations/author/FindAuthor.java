package logics.operations.author;

import database.sql.brokers.impl.SqlAuthorBroker;
import database.sql.brokers.interfaces.IAuthorBroker;
import java.sql.Connection;
import logics.operations.Operation;
import models.Author;

/**
 *
 * @author Djordjije
 */
public class FindAuthor extends Operation<Author> {
    protected final IAuthorBroker authorBroker;
    protected Author author;
    
    public FindAuthor(){
        authorBroker = new SqlAuthorBroker();
    }
    public void setAuthor(Author author){
        this.author = author;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected Author executeOperation(Connection connection) throws Exception {
        return authorBroker.findAuthor(author, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
