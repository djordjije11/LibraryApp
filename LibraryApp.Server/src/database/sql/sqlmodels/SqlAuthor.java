package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Author;

/**
 *
 * @author Djordjije
 */
public class SqlAuthor extends SqlEntity<Author> {
    
    public SqlAuthor(Author author){
        super(author);
    }
    public SqlAuthor(){}
    
    @Override
    protected String getTableName() {
        return "author";
    }

    @Override
    public String getPreparedStatementInsertQuery() {
        return "INSERT INTO " + getTableName() + "(firstname, lastname) VALUES (?,?)";
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getFirstname());
        preparedStatement.setString(2, entity.getLastname());
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET firstname = ?, lastname = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getFirstname());
        preparedStatement.setString(2, entity.getLastname());
        preparedStatement.setLong(3, entity.getId());
    }
    
    @Override
    public String getStatementSelectWithConditionQuery(){
        List<String> conditions = new ArrayList<>();
        if(entity.getFirstname() != null && entity.getFirstname().isBlank() == false){
            conditions.add("firstname LIKE '" + entity.getFirstname() + "%'");
        }
        if(entity.getLastname() != null && entity.getLastname().isBlank() == false){
            conditions.add("lastname LIKE '" + entity.getLastname() + "%'");
        }
        return constructSelectWithConditionsQuery(conditions);
    }

    @Override
    public Author getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getLong("ID"), resultSet.getString("firstname"), resultSet.getString("lastname"));
    }
}
