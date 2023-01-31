package logics.operations.book;

import database.sql.brokers.impl.SqlCopyOfBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import java.sql.Connection;
import logics.operations.Operation;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class FindCopyOfBookInBuilding extends Operation<CopyOfBook> {
    private final ICopyOfBookBroker copyOfBookBroker;
    private CopyOfBook copyOfBook;
    
    public FindCopyOfBookInBuilding(){
        copyOfBookBroker = new SqlCopyOfBookBroker();
    }
    public void setCopyOfBook(CopyOfBook copyOfBook){
        this.copyOfBook = copyOfBook;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected CopyOfBook executeOperation(Connection connection) throws Exception {
        return copyOfBookBroker.findCopyOfBookInBuilding(copyOfBook, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
