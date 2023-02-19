package database.sql.brokers.impl;

import models.IEntity;
import database.sql.sqlmodels.SqlEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Djordjije
 */
public abstract class SqlEntityBroker<T extends IEntity> {
    protected synchronized T create(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        T entity = sqlEntity.getEntity();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementInsertQuery(), Statement.RETURN_GENERATED_KEYS);
        sqlEntity.setUpPreparedStatementInsert(preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            entity.setId(resultSet.getLong(1));
        }
        resultSet.close();
        preparedStatement.close();
        return entity;
    }
    protected synchronized List<T> createList(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        List<T> listOfEntities = sqlEntity.getListOfEntities();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementInsertQuery(), Statement.RETURN_GENERATED_KEYS);
        for (T entity : listOfEntities) {
            sqlEntity.setEntity(entity);
            sqlEntity.setUpPreparedStatementInsert(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                entity.setId(resultSet.getLong(1));
            }
            resultSet.close();
        }
        preparedStatement.close();
        return listOfEntities;
    }
    protected synchronized T find(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectByIdQuery());
        T instance = null;
        if(resultSet.next()){
            instance = sqlEntity.getEntityFromResultSet(resultSet);
        }
        resultSet.close();
        statement.close();
        return instance;
    }
    protected synchronized List<T> findEntities(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        List<T> entities = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectWithConditionQuery());
        while(resultSet.next()){
            entities.add(sqlEntity.getEntityFromResultSet(resultSet));
        }
        resultSet.close();
        statement.close();
        return entities;
    }
    protected synchronized List<T> findEntitiesWithCondition(SqlEntity<T> sqlEntity, Connection connection, List<String> conditions) throws Exception{
        List<T> entities = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.constructSelectWithConditionsQuery(conditions));
        while(resultSet.next()){
            entities.add(sqlEntity.getEntityFromResultSet(resultSet));
        }
        resultSet.close();
        statement.close();
        return entities;
    }
    protected synchronized List<T> readAll(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        List<T> entities = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectAllQuery());
        while(resultSet.next()){
            entities.add(sqlEntity.getEntityFromResultSet(resultSet));
        }
        resultSet.close();
        statement.close();
        return entities;
    }
    protected synchronized T update(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementUpdateQuery());
        sqlEntity.setUpPreparedStatementUpdate(preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return sqlEntity.getEntity();
    }
    protected synchronized List<T> updateList(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        List<T> listOfEntities = sqlEntity.getListOfEntities();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementUpdateQuery());
        for (T listOfEntity : listOfEntities) {
            sqlEntity.setEntity(listOfEntity);
            sqlEntity.setUpPreparedStatementUpdate(preparedStatement);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        return listOfEntities;
    }
    protected synchronized T delete(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementDeleteByIdQuery());
        sqlEntity.setUpPreparedStatementDeleteById(preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return sqlEntity.getEntity();
    }
    protected synchronized boolean checkIfExists(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(1) FROM " + sqlEntity.getTableName() + " WHERE ID = " + sqlEntity.getEntity().getId());
        int numberOfEntities = 0;
        if(resultSet.next()){
            numberOfEntities = resultSet.getInt("COUNT(1)");
        }
        resultSet.close();
        statement.close();
        return numberOfEntities == 1;
    }
}
