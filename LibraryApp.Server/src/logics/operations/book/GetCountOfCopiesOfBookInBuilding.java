package logics.operations.book;

import database.sql.brokers.impl.SqlBookBroker;
import database.sql.brokers.impl.SqlCopyOfBookBroker;
import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import java.sql.Connection;
import logics.operations.Operation;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class GetCountOfCopiesOfBookInBuilding extends Operation<Long>{
    protected final ICopyOfBookBroker copyOfBookBroker;
    protected final IBookBroker bookBroker;
    protected CopyOfBook copyOfBook;
    
    public GetCountOfCopiesOfBookInBuilding(){
        copyOfBookBroker = new SqlCopyOfBookBroker();
        bookBroker = new SqlBookBroker();
    }
    public void setCopyOfBook(CopyOfBook copyOfBook){
        this.copyOfBook = copyOfBook;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        if(copyOfBook.getBook() == null)
            throw new NullPointerException("The book is null.");
    }
    @Override
    protected Long executeOperation(Connection connection) throws Exception {
        return copyOfBookBroker.getCountOfCopiesOfBookInBuilding(copyOfBook, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
