package database.sql.brokers.impl;

import database.sql.brokers.interfaces.ILendingBroker;
import java.sql.Connection;
import java.util.List;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public class SqlLendingBroker extends SqlEntityBroker implements ILendingBroker {

    @Override
    public List<Lending> createLendings(List<Lending> lendings, Connection connection) throws Exception {
        return null;
    }

    @Override
    public List<Lending> updateLendings(List<Lending> lendings, Connection connection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
