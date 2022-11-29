/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public abstract class SqlEntity {
    protected IEntity entity;
    protected SqlEntity(IEntity entity){
        this.entity = entity;
    }
    protected SqlEntity() {}
    abstract String getTableName();
    abstract String getPreparedStatementInsertQuery();
    abstract void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException;
    abstract String getPreparedStatementUpdateQuery();
    abstract void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException;
    abstract String getPreparedStatementDeleteQuery();
    abstract void setUpPreparedStatementDelete(PreparedStatement preparedStatement) throws SQLException;
    abstract String getStatementSelectQuery();
    abstract String getStatementSelectAllQuery();
    abstract IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
