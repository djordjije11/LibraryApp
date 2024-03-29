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
public class GetCountOfCopiesOfBookInBuilding extends Operation<Long>{
    private final ICopyOfBookBroker copyOfBookBroker;
    private CopyOfBook copyOfBook;
    
    public GetCountOfCopiesOfBookInBuilding(){
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
    protected Long executeOperation(Connection connection) throws Exception {
        return copyOfBookBroker.getCountOfCopiesOfBookInBuilding(copyOfBook, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
