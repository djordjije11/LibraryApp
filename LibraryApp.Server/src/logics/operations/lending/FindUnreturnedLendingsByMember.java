package logics.operations.lending;

import database.sql.brokers.impl.SqlLendingBroker;
import database.sql.brokers.interfaces.ILendingBroker;
import java.sql.Connection;
import java.util.List;
import logics.operations.Operation;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public class FindUnreturnedLendingsByMember extends Operation<List<Lending>> {
    private final ILendingBroker lendingBroker;
    private Lending lending;
    
    public FindUnreturnedLendingsByMember(){
        lendingBroker = new SqlLendingBroker();
    }
    public void setLending(Lending lending){
        this.lending = lending;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }
    @Override
    protected List<Lending> executeOperation(Connection connection) throws Exception {
        return lendingBroker.findUnreturnedLendingsByMember(lending, connection);
    }
    @Override
    protected boolean isTransaction() {
        return false;
    }
}
