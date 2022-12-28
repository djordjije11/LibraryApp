package database.sql.brokers.impl;

import models.IEntity;
import database.sql.sqlmodels.SqlEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
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
        ResultSet result = preparedStatement.getGeneratedKeys();
        if(result.next())
            entity.setId(result.getLong(1));
        result.close();
        preparedStatement.close();
        return entity;
    }
    protected synchronized List<T> createList(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        List<T> listOfEntities = sqlEntity.getListOfEntities();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEntity.getPreparedStatementInsertQuery(), Statement.RETURN_GENERATED_KEYS);
        for (T listOfEntity : listOfEntities) {
            sqlEntity.setEntity(listOfEntity);
            sqlEntity.setUpPreparedStatementInsert(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            //check if this works or you have to try with for(int i = 0...)
            if(result.next())
                listOfEntity.setId(result.getLong(1));
            result.close();
        }
        preparedStatement.close();
        return listOfEntities;
    }
    protected synchronized T find(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEntity.getStatementSelectByIdQuery());
        resultSet.next();
        T instance = sqlEntity.getEntityFromResultSet(resultSet);
        resultSet.close();
        statement.close();
        return instance;
    }
    protected synchronized List<T> findEntities(SqlEntity<T> sqlEntity, Connection connection) throws Exception{
        List<T> entities = new LinkedList<>();
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
        List<T> entities = new LinkedList<>();
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
        List<T> entities = new LinkedList<>();
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
}
