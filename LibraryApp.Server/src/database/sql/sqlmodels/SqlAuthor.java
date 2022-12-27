package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Author;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public class SqlAuthor extends SqlEntity {
    private Author author;
    
    public SqlAuthor(Author author){
        super(author);
        this.author = author;
    }
    public SqlAuthor(){}
    
    @Override
    public IEntity getEntity(){
        return author;
    }
    
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
        preparedStatement.setString(1, author.getFirstname());
        preparedStatement.setString(2, author.getLastname());
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET firstname = ?, lastname = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, author.getFirstname());
        preparedStatement.setString(2, author.getLastname());
        preparedStatement.setLong(3, author.getId());
    }
    
    @Override
    public String getStatementSelectWithConditionQuery(){
        List<String> conditions = new ArrayList<>();
        if(author.getFirstname() != null && author.getFirstname().isBlank() == false){
            conditions.add("firstname LIKE '" + author.getFirstname() + "%'");
        }
        if(author.getLastname() != null && author.getLastname().isBlank() == false){
            conditions.add("lastname LIKE '" + author.getLastname() + "%'");
        }
        return constructSelectWithConditionsQuery(conditions);
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getLong("ID"), resultSet.getString("firstname"), resultSet.getString("lastname"));
    }

    @Override
    public List<IEntity> getListOfEntities() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
