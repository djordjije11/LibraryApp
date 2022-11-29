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
    public IEntity getEntity(){
        return entity;
    }
    abstract String getTableName();
    public abstract String getPreparedStatementInsertQuery();
    public abstract void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException;
    public abstract String getPreparedStatementUpdateQuery();
    public abstract void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException;
    public abstract String getPreparedStatementDeleteQuery();
    public abstract void setUpPreparedStatementDelete(PreparedStatement preparedStatement) throws SQLException;
    public abstract String getStatementSelectQuery();
    public abstract String getStatementSelectAllQuery();
    public abstract IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
