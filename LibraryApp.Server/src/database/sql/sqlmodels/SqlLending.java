package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import models.Book;
import models.CopyOfBook;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public class SqlLending extends SqlEntity<Lending> {
    public SqlLending(Lending lending){
        super(lending);
    }
    public SqlLending(){}
    public SqlLending(List<Lending> listOfLendings){
        super(listOfLendings);
    }
    
    @Override
    protected String getTableName() {
        return "lending";
    }
    @Override
    public String getPreparedStatementInsertQuery() {
        return "INSERT INTO " + getTableName() + "(bookID, copyofbookID, memberID, lending_date) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        CopyOfBook copyOfBook = entity.getCopyOfBook();
        Book book = copyOfBook.getBook();
        preparedStatement.setLong(1, book.getId());
        preparedStatement.setLong(2, copyOfBook.getId());
        preparedStatement.setLong(3, entity.getMember().getId());
        preparedStatement.setDate(4, Date.valueOf(entity.getLendingDate()));
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET return_date = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(entity.getReturnDate()));
        preparedStatement.setLong(2, entity.getId());
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lending getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
