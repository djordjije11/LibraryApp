package database.sql.brokers.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import database.sql.sqlmodels.SqlCopyOfBook;
import java.util.ArrayList;
import java.util.List;
import models.CopyOfBook;


/**
 *
 * @author Djordjije
 */
public class SqlCopyOfBookBroker extends SqlEntityBroker<CopyOfBook> implements ICopyOfBookBroker {
    
    @Override
    public List<CopyOfBook> createCopiesOfBook(List<CopyOfBook> copiesOfBook, Connection connection) throws Exception {
        return createList(new SqlCopyOfBook(copiesOfBook), connection);
    }
    @Override
    public List<CopyOfBook> readAllCopiesOfBookInBuilding(CopyOfBook copyOfBook, Connection connection) throws Exception {
        List<CopyOfBook> copiesOfBook = new ArrayList<>();
        SqlCopyOfBook sqlCopyOfBook = new SqlCopyOfBook(copyOfBook);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCopyOfBook.getStatementSelectWithConditionQuery());
        while(resultSet.next()){
            copiesOfBook.add((CopyOfBook)sqlCopyOfBook.getEntityFromResultSet(resultSet));
        }
        resultSet.close();
        statement.close();
        return copiesOfBook;
    }
    @Override
    public Long getCountOfCopiesOfBookInBuilding(CopyOfBook copyOfBook, Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS amount FROM " + new SqlCopyOfBook().getTableName() + " WHERE bookID = " + copyOfBook.getBook().getId() + " AND buildingID = " + copyOfBook.getBuildingId());
        resultSet.next();
        Long amount = resultSet.getLong("amount");
        resultSet.close();
        statement.close();
        return amount;
    }
    @Override
    public Long getCountOfAllCopiesOfBook(CopyOfBook copyOfBook, Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS amount FROM copyofbook WHERE bookID = " + copyOfBook.getBook().getId());
        resultSet.next();
        Long amount = resultSet.getLong("amount");
        resultSet.close();
        statement.close();
        return amount;
    }
    @Override
    public CopyOfBook findCopyOfBookInBuilding(CopyOfBook copyOfBook, Connection connection) throws Exception {
        return find(new SqlCopyOfBook(copyOfBook), connection);
    }
    @Override
    public List<CopyOfBook> updateCopiesOfBook(List<CopyOfBook> copiesOfBook, Connection connection) throws Exception {
        return updateList(new SqlCopyOfBook(copiesOfBook), connection);
    }
}
