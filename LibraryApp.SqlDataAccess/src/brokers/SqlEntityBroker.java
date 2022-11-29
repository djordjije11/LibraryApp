/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brokers;

import connectors.SqlConnector;
import java.io.IOException;
import models.IEntity;
import sqlmodels.SqlEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Djordjije
 */
public abstract class SqlEntityBroker implements IBroker {
    protected Connection connection;
    
    protected synchronized IEntity create(SqlEntity sqlEntity) throws SQLException, ParseException, IOException{
        IEntity entity = sqlEntity.getEntity();
        try{
            connection = SqlConnector.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            sqlEntity.setUpPreparedStatementInsert(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if(result.next()) 
                entity.setId(result.getLong(1));
            result.close();
            preparedStatement.close();
            connection.commit();
        } catch(SQLException ex){
            if(connection != null)
                connection.rollback();
            throw ex;
        }
        return entity;
    }
    protected synchronized IEntity read(SqlEntity sqlEntity) throws SQLException, ParseException, IOException{
        connection = SqlConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectQuery());
        IEntity instance = sqlEntity.getEntityFromResultSet(resultSet);
        statement.close();
        return instance;
    }
    protected synchronized List<IEntity> readAll(SqlEntity sqlEntity) throws SQLException, ParseException, IOException{
        connection = SqlConnector.getConnection();
        List<IEntity> entities = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectAllQuery());
        while(resultSet.next()){
            entities.add(sqlEntity.getEntityFromResultSet(resultSet));
        }
        statement.close();
        return entities;
    }
    protected synchronized IEntity update(SqlEntity sqlEntity) throws SQLException, ParseException, IOException{
        try{
            connection = SqlConnector.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementUpdateQuery());
            sqlEntity.setUpPreparedStatementUpdate(preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch(SQLException ex){
            if(connection != null)
                connection.rollback();
            throw ex;
        }
        return sqlEntity.getEntity();
    }
    protected synchronized IEntity delete(SqlEntity sqlEntity) throws SQLException, ParseException, IOException{
        try{
            connection = SqlConnector.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementDeleteQuery());
            sqlEntity.setUpPreparedStatementDelete(preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch(SQLException ex){
            if(connection != null)
                connection.rollback();
            throw ex;
        }
        return sqlEntity.getEntity();
    }

    @Override
    public String getBrokerName() {
        return "SQL";
    }

    @Override
    public void closeConnection() throws SQLException {
        SqlConnector.closeConnection();
    }
}
