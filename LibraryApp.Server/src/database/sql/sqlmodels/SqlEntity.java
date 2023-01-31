package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public abstract class SqlEntity<T extends IEntity> {
    protected T entity;
    protected List<T> listOfEntities;
    protected SqlEntity(T entity){
        this.entity = entity;
    }
    protected SqlEntity(List<T> listOfEntities){
        this(listOfEntities.get(0));
        this.listOfEntities = listOfEntities;
    }
    protected SqlEntity() {}
    public T getEntity(){
        return entity;
    }
    public void setEntity(T entity){
        this.entity = entity;
    }
    public List<T> getListOfEntities(){
        return listOfEntities;
    }
    public abstract String getTableName();
    public abstract String getPreparedStatementInsertQuery();
    public abstract void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException;
    public abstract String getPreparedStatementUpdateQuery();
    public abstract void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException;
    public String getPreparedStatementDeleteByIdQuery(){
        return "DELETE FROM " + getTableName() + " WHERE ID = ?";
    }
    public void setUpPreparedStatementDeleteById(PreparedStatement preparedStatement) throws SQLException{
        preparedStatement.setLong(1, entity.getId());
    }
    public String getStatementSelectByIdQuery() {
        return "SELECT * FROM " + getTableName() + " WHERE ID = " + entity.getId();
    }
    public abstract String getStatementSelectWithConditionQuery();
    public String getStatementSelectAllQuery(){
        return "SELECT * FROM " + getTableName();
    }
    public abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;
    public String constructQueryWithConditions(String query, List<String> conditions){
        if(conditions == null || conditions.isEmpty()){
            return query;
        }
        query += " WHERE ";
        int conditionsNumber = conditions.size();
        for (int i = 0; i < conditionsNumber; i++) {
            query += conditions.get(i);
            if(i + 1 == conditionsNumber){
                break;
            }
            query += " AND ";
        }
        return query;
    }
    public String constructSelectWithConditionsQuery(List<String> conditions){
        return constructQueryWithConditions(getStatementSelectAllQuery(), conditions);
    }
}
