package logics.impl;

import database.sql.brokers.interfaces.ICopyOfBookBroker;
import database.sql.brokers.interfaces.ILendingBroker;
import database.sql.connection.SqlConnectionFactory;
import java.util.List;
import logics.interfaces.ILendingLogic;
import models.Lending;
import java.sql.Connection;
import java.util.ArrayList;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class LendingLogic implements ILendingLogic {
    private ILendingBroker lendingBroker;
    private ICopyOfBookBroker copyOfBookBroker;
    public LendingLogic(ILendingBroker lendingBroker, ICopyOfBookBroker copyOfBookBroker){
        this.lendingBroker = lendingBroker;
        this.copyOfBookBroker = copyOfBookBroker;
    }
    @Override
    public List<Lending> createLendings(List<Lending> lendings) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            List<Lending> dbLendings = lendingBroker.createLendings(lendings, connection);
            List<CopyOfBook> copiesOfBook = new ArrayList<>();
            for (Lending dbLending : dbLendings) {
                CopyOfBook copyOfBook = dbLending.getCopyOfBook();
                copyOfBook.setBuildingId(null);
                copiesOfBook.add(copyOfBook);
            }
            List<CopyOfBook> dbCopiesOfBook = copyOfBookBroker.updateCopiesOfBook(copiesOfBook, connection);
            connection.commit();
            return dbLendings;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public List<Lending> returnLendings(List<Lending> lendings) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            List<Lending> dbLendings = lendingBroker.updateLendings(lendings, connection);
            List<CopyOfBook> copiesOfBook = new ArrayList<>();
            for (Lending dbLending : dbLendings) {
                copiesOfBook.add(dbLending.getCopyOfBook());
            }
            List<CopyOfBook> dbCopiesOfBook = copyOfBookBroker.updateCopiesOfBook(copiesOfBook, connection);
            connection.commit();
            return dbLendings;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
}
