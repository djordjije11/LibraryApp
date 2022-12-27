package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Author;
import models.Book;
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
        Book book = copyOfBook.getBook();
        if(book != null){
            preparedStatement.setLong(1, copyOfBook.getId());
        }
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
        Book book = copyOfBook.getBook();
        if(book != null){
            conditions.add("bookID = " + book.getId());
        }
        conditions.add("buildingID = " + copyOfBook.getBuildingId());
        return constructSelectWithConditionsQuery(conditions);
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new CopyOfBook(resultSet.getLong("ID"), new Book(resultSet.getLong("bookID"), resultSet.getString("title"), resultSet.getString("description"), 
                new Author(resultSet.getLong("authorID"), resultSet.getString("firstname"), resultSet.getString("lastname"))), resultSet.getLong("buildingID"));
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT cob.*, b.title, b.description, b.authorID, a.firstname, a.lastname FROM " + getTableName() +  " AS cob JOIN " + new SqlBook().getTableName() + " AS b ON(cob.bookID = b.ID) JOIN " + new SqlAuthor().getTableName() + " AS a ON(b.authorID = a.ID)";
    }
    
    @Override
    public String getStatementSelectByIdQuery() {
        return getStatementSelectAllQuery() + " WHERE cob.ID = " + entity.getId() + " AND buildingID = " + copyOfBook.getBuildingId();
    }
}
