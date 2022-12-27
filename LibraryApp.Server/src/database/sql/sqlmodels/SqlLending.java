package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import models.Book;
import models.CopyOfBook;
import models.IEntity;
import models.Lending;

/**
 *
 * @author Djordjije
 */
public class SqlLending extends SqlEntity {

    private Lending lending;
    private List<Lending> listOfLendings;
    
    public SqlLending(Lending lending){
        super(lending);
        this.lending = lending;
    }
    public SqlLending(){}
    public SqlLending(List<Lending> listOfLendings){
        super(listOfLendings.get(0));
        this.listOfLendings = listOfLendings;
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
        CopyOfBook copyOfBook = lending.getCopyOfBook();
        Book book = copyOfBook.getBook();
        preparedStatement.setLong(1, book.getId());
        preparedStatement.setLong(2, copyOfBook.getId());
        preparedStatement.setLong(3, lending.getMember().getId());
        preparedStatement.setDate(4, Date.valueOf(lending.getLendingDate()));
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<IEntity> getListOfEntities() {
        return null;
    }
    
}
