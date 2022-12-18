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
    public abstract IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException;
    protected String constructSelectWithConditionsQuery(List<String> conditions){
        String query = getStatementSelectAllQuery();
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
        System.out.println(query);
        return query;
    }
}
