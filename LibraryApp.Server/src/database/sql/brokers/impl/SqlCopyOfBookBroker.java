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
public class SqlCopyOfBookBroker implements ICopyOfBookBroker {

    @Override
    public List<CopyOfBook> createCopiesOfBook(CopyOfBook copyOfBook, Long amount, Connection connection) throws Exception {
        List<CopyOfBook> copiesOfBook = new ArrayList<>();
        SqlCopyOfBook sqlCopyOfBook = new SqlCopyOfBook(copyOfBook);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlCopyOfBook.getPreparedStatementInsertQuery(), Statement.RETURN_GENERATED_KEYS);
        sqlCopyOfBook.setUpPreparedStatementInsert(preparedStatement);
        ResultSet result = null;
        for (int i = 0; i < amount; i++) {
            preparedStatement.executeUpdate();
            result = preparedStatement.getGeneratedKeys();
            if(result.next()){
                copiesOfBook.add(new CopyOfBook(result.getLong(1), copyOfBook.getBookId(), copyOfBook.getBuildingId()));
            }
        }
        if(result != null) result.close();
        preparedStatement.close();
        return copiesOfBook;
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
    public Long getNumberOfCopiesOfBookInBuilding(CopyOfBook copyOfBook, Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS amount FROM copyofbook WHERE bookID = " + copyOfBook.getBookId() + " AND buildingID = " + copyOfBook.getBuildingId());
        resultSet.next();
        Long amount = resultSet.getLong("amount");
        resultSet.close();
        statement.close();
        return amount;
    }

    @Override
    public Long getNumberOfAllCopiesOfBook(CopyOfBook copyOfBook, Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS amount FROM copyofbook WHERE bookID = " + copyOfBook.getBookId());
        resultSet.next();
        Long amount = resultSet.getLong("amount");
        resultSet.close();
        statement.close();
        return amount;
    }
}
