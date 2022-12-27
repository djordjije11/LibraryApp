package logics.impl;

import database.sql.connection.SqlConnectionFactory;
import java.util.List;
import logics.interfaces.ILendingLogic;
import models.Lending;
import java.sql.Connection;

/**
 *
 * @author Djordjije
 */
public class LendingLogic implements ILendingLogic {

    @Override
    public List<Lending> createLendings(List<Lending> lendings) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            
            connection.commit();
            return null;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }

    @Override
    public List<Lending> returnLendings(List<Lending> lendings) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
