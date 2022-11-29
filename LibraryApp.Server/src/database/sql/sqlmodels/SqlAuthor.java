/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    String getTableName() {
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
    public String getPreparedStatementDeleteQuery() {
        return "DELETE FROM " + getTableName() + " WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementDelete(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, author.getId());
    }

    @Override
    public String getStatementSelectQuery() {
        return "SELECT * FROM " + getTableName() + " WHERE ID = " + author.getId();
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT * FROM " + getTableName();
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getLong("ID"), resultSet.getString("firstname"), resultSet.getString("lastname"));
    }
}
