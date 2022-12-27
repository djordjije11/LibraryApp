package database.sql.brokers.impl;

import database.sql.brokers.interfaces.ILendingBroker;
import database.sql.sqlmodels.SqlLending;
import java.sql.Connection;
import java.util.List;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public class SqlLendingBroker extends SqlEntityBroker<Lending> implements ILendingBroker {

    @Override
    public List<Lending> createLendings(List<Lending> lendings, Connection connection) throws Exception {
        return createList(new SqlLending(lendings), connection);
    }

    @Override
    public List<Lending> updateLendings(List<Lending> lendings, Connection connection) throws Exception {
        return updateList(new SqlLending(lendings), connection);
    }
    
}
