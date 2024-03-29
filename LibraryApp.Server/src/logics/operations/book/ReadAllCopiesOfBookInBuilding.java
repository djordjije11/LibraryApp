package logics.operations.book;

import database.sql.brokers.impl.SqlBookBroker;
import database.sql.brokers.impl.SqlCopyOfBookBroker;
import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import java.sql.Connection;
import java.util.List;
import logics.operations.Operation;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class ReadAllCopiesOfBookInBuilding extends Operation<List<CopyOfBook>> {
    protected CopyOfBook copyOfBook;
    protected final IBookBroker bookBroker;
    protected final ICopyOfBookBroker copyOfBookBroker;
    
    public ReadAllCopiesOfBookInBuilding(){
        bookBroker = new SqlBookBroker();
        copyOfBookBroker = new SqlCopyOfBookBroker();
    }
    public void setCopyOfBook(CopyOfBook copyOfBook){
        this.copyOfBook = copyOfBook;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        if(copyOfBook.getBook() == null)
            throw new Exception("The book is null.");
    }
    @Override
    protected List<CopyOfBook> executeOperation(Connection connection) throws Exception {
        return copyOfBookBroker.readAllCopiesOfBookInBuilding(copyOfBook, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
