package database.sql.brokers.impl;

import models.IEntity;
import database.sql.sqlmodels.SqlEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
/**
 *
 * @author Djordjije
 */
public abstract class SqlEntityBroker {
    
    protected synchronized IEntity create(SqlEntity sqlEntity, Connection connection) throws Exception{
        IEntity entity = sqlEntity.getEntity();
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
    protected synchronized IEntity find(SqlEntity sqlEntity, Connection connection) throws Exception{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectByIdQuery());
        resultSet.next();
        IEntity instance = sqlEntity.getEntityFromResultSet(resultSet);
        resultSet.close();
        statement.close();
        return instance;
    }
    protected synchronized List<IEntity> findEntities(SqlEntity sqlEntity, Connection connection) throws Exception{
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
    protected synchronized List<IEntity> readAll(SqlEntity sqlEntity, Connection connection) throws Exception{
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
    protected synchronized IEntity update(SqlEntity sqlEntity, Connection connection) throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementUpdateQuery());
        sqlEntity.setUpPreparedStatementUpdate(preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return sqlEntity.getEntity();
    }
    protected synchronized IEntity delete(SqlEntity sqlEntity, Connection connection) throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementDeleteByIdQuery());
        sqlEntity.setUpPreparedStatementDeleteById(preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return sqlEntity.getEntity();
    }
}
