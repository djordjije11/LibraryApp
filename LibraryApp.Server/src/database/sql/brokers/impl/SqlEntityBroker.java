package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IEntityBroker;
import database.sql.connectors.SqlConnector;
import models.IEntity;
import database.sql.sqlmodels.SqlEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
/**
 *
 * @author Djordjije
 */
public abstract class SqlEntityBroker implements IEntityBroker {
    
    protected synchronized IEntity create(SqlEntity sqlEntity) throws Exception{
        IEntity entity = sqlEntity.getEntity();
        Connection connection = SqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementInsertQuery(), Statement.RETURN_GENERATED_KEYS);
        sqlEntity.setUpPreparedStatementInsert(preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet result = preparedStatement.getGeneratedKeys();
        if(result.next())
            entity.setId(result.getLong(1));
        result.close();
        preparedStatement.close();
        return entity;
    }
    protected synchronized IEntity find(SqlEntity sqlEntity) throws Exception{
        Connection connection = SqlConnector.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectByIdQuery());
        resultSet.next();
        IEntity instance = sqlEntity.getEntityFromResultSet(resultSet);
        resultSet.close();
        statement.close();
        return instance;
    }
    protected synchronized List<IEntity> findEntities(SqlEntity sqlEntity) throws Exception{
        Connection connection = SqlConnector.getConnection();
        List<IEntity> entities = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectWithConditionQuery());
        while(resultSet.next()){
            entities.add(sqlEntity.getEntityFromResultSet(resultSet));
        }
        resultSet.close();
        statement.close();
        return entities;
    }
    protected synchronized List<IEntity> readAll(SqlEntity sqlEntity) throws Exception{
        Connection connection = SqlConnector.getConnection();
        List<IEntity> entities = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectAllQuery());
        while(resultSet.next()){
            entities.add(sqlEntity.getEntityFromResultSet(resultSet));
        }
        resultSet.close();
        statement.close();
        return entities;
    }
    protected synchronized IEntity update(SqlEntity sqlEntity) throws Exception{
        Connection connection = SqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementUpdateQuery());
        sqlEntity.setUpPreparedStatementUpdate(preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return sqlEntity.getEntity();
    }
    protected synchronized IEntity delete(SqlEntity sqlEntity) throws Exception{
        Connection connection = SqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementDeleteByIdQuery());
        sqlEntity.setUpPreparedStatementDeleteById(preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return sqlEntity.getEntity();
    }
    
    @Override
    public void openConnection() throws Exception {
        SqlConnector.openConnection();
    }

    @Override
    public void closeConnection() throws SQLException {
        SqlConnector.closeConnection();
    }
    
    @Override
    public void openTransaction() throws Exception {
        SqlConnector.getConnection().setAutoCommit(false);
    }

    @Override
    public void commitTransaction() throws Exception {
        SqlConnector.getConnection().commit();
    }

    @Override
    public void rollbackTransaction() throws Exception {
        Connection connection = SqlConnector.getConnection();
        if(connection != null) 
            connection.rollback();
    }
}
