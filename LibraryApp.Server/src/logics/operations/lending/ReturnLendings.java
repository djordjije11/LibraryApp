package logics.operations.lending;

import database.sql.brokers.impl.SqlCopyOfBookBroker;
import database.sql.brokers.impl.SqlLendingBroker;
import database.sql.brokers.impl.SqlMemberBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import database.sql.brokers.interfaces.ILendingBroker;
import database.sql.brokers.interfaces.IMemberBroker;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import logics.operations.Operation;
import models.CopyOfBook;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public class ReturnLendings extends Operation<List<Lending>> {
    private final ILendingBroker lendingBroker;
    private final IMemberBroker memberBroker;
    private final ICopyOfBookBroker copyOfBookBroker;
    private List<Lending> lendings;
    
    public ReturnLendings(){
        lendingBroker = new SqlLendingBroker();
        memberBroker = new SqlMemberBroker();
        copyOfBookBroker = new SqlCopyOfBookBroker();
    }
    public void setLendings(List<Lending> lendings){
        this.lendings = lendings;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        if(memberBroker.checkIfMemberExists(lendings.get(0).getMember(), connection) == false){
            throw new Exception("Lendings cannot be created because member is not in the database.");
        }
    }
    @Override
    protected List<Lending> executeOperation(Connection connection) throws Exception {
        List<Lending> dbLendings = lendingBroker.updateLendings(lendings, connection);
        List<CopyOfBook> copiesOfBook = new ArrayList<>();
        for (Lending dbLending : dbLendings) {
            copiesOfBook.add(dbLending.getCopyOfBook());
        }
        copyOfBookBroker.updateCopiesOfBook(copiesOfBook, connection);
        return dbLendings;
    }
    @Override
    protected boolean isTransaction() {
        return true;
    }
}
