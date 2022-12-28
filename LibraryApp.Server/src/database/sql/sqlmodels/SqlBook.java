package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Author;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class SqlBook extends SqlEntity<Book> {
    public SqlBook(){}
    public SqlBook(Book book) {
        super(book);
    }
    
    @Override
    public String getTableName() {
        return "book";
    }

    @Override
    public String getPreparedStatementInsertQuery() {
        return "INSERT INTO " + getTableName() + "(title, description, authorID) VALUES (?,?,?)";
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getTitle());
        preparedStatement.setString(2, entity.getDescription());
        if(entity.getAuthor() == null){
            preparedStatement.setObject(3, null);
        } else{
            preparedStatement.setLong(3, entity.getAuthor().getId());
        }
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET title = ?, description = ?, authorID = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getTitle());
        preparedStatement.setString(2, entity.getDescription());
        if(entity.getAuthor() == null){
            preparedStatement.setObject(3, null);
        } else{
            preparedStatement.setLong(3, entity.getAuthor().getId());
        }
        preparedStatement.setLong(4, entity.getId());
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT b.*, a.firstname, a.lastname FROM " + getTableName() + " as b LEFT OUTER JOIN " + new SqlAuthor().getTableName() + " as a ON (b.authorID = a.ID)";
    }
    
    @Override
    public String getStatementSelectByIdQuery() {
        return "SELECT b.*, a.firstname, a.lastname FROM " + getTableName() + " as b LEFT OUTER JOIN " + new SqlAuthor().getTableName() + " as a ON (b.authorID = a.ID) WHERE b.ID = " + entity.getId();
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        List<String> conditions = new ArrayList<>();
        if(entity.getTitle() != null && entity.getTitle().isBlank() == false){
            conditions.add("title LIKE '" + entity.getTitle() + "%'");
        }
        return constructSelectWithConditionsQuery(conditions);
    }

    @Override
    public Book getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author(resultSet.getLong("authorID"), resultSet.getString("firstname"), resultSet.getString("lastname"));
        return new Book(resultSet.getLong("ID"), resultSet.getString("title"), resultSet.getString("description"), author);
    }
}
