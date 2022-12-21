package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.CopyOfBook;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public class SqlCopyOfBook extends SqlEntity {
    private CopyOfBook copyOfBook;
    
    public SqlCopyOfBook(CopyOfBook copyOfBook){
        super(copyOfBook);
        this.copyOfBook = copyOfBook;
    }
    public SqlCopyOfBook(){}
    
    @Override
    String getTableName() {
        return "copyofbook";
    }

    public void setCopyOfBook(CopyOfBook copyOfBook){
        this.copyOfBook = copyOfBook;
    }
    
    @Override
    public String getPreparedStatementInsertQuery() {
        return "INSERT INTO " + getTableName() + "(bookID, buildingID) VALUES (?, ?)";
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, copyOfBook.getBookId());
        preparedStatement.setLong(2, copyOfBook.getBuildingId());
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET buildingID = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, copyOfBook.getBuildingId());
        preparedStatement.setLong(1, copyOfBook.getId());
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        List<String> conditions = new ArrayList<>();
        conditions.add("bookID = " + copyOfBook.getBookId());
        conditions.add("buildingID = " + copyOfBook.getBuildingId());
        return constructSelectWithConditionsQuery(conditions);
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new CopyOfBook(resultSet.getLong("ID"), resultSet.getLong("bookID"), resultSet.getLong("buildingID"));
    }
}
