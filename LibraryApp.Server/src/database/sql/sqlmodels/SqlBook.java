package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Author;
import models.Book;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public class SqlBook extends SqlEntity {
    private Book book;
    
    public SqlBook(Book book) {
        super(book);
        this.book = book;
    }
    
    @Override
    String getTableName() {
        return "book";
    }

    @Override
    public String getPreparedStatementInsertQuery() {
        return "INSERT INTO " + getTableName() + "(title, description, authorID) VALUES (?,?,?)";
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getDescription());
        preparedStatement.setLong(3, book.getAuthor().getId());
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET title = ?, description = ?, authorID = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getDescription());
        preparedStatement.setLong(3, book.getAuthor().getId());
        preparedStatement.setLong(4, book.getId());
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT b.*, a.firstname, a.lastname FROM " + getTableName() + " as b INNER JOIN " + new SqlAuthor().getTableName() + " as a ON (b.authorID = a.ID)";
    }
    
    @Override
    public String getStatementSelectByIdQuery() {
        return "SELECT b.*, a.firstname, a.lastname FROM " + getTableName() + " as b INNER JOIN " + new SqlAuthor().getTableName() + " as a ON (b.authorID = a.ID) WHERE b.ID = " + book.getId();
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        List<String> conditions = new ArrayList<>();
        if(book.getTitle() != null && book.getTitle().isBlank() == false){
            conditions.add("title LIKE '" + book.getTitle() + "%'");
        }
        return constructSelectWithConditionsQuery(conditions);
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author(resultSet.getLong("authorID"), resultSet.getString("firstname"), resultSet.getString("lastname"));
        return new Book(resultSet.getLong("ID"), resultSet.getString("title"), resultSet.getString("description"), author);
    }
}
