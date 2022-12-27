package database.sql.sqlmodels;

import helper.HashPassword;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.Building;
import models.Employee;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public class SqlEmployee extends SqlEntity {
    private Employee employee;
    public SqlEmployee(Employee employee){
        super(employee);
        this.employee = employee;
    }
    public SqlEmployee(){}
    
    @Override
    protected String getTableName() {
        return "employee";
    }

    @Override
    public String getPreparedStatementInsertQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getStatementSelectByIdQuery() {
        return "SELECT e.*, b.name FROM " + getTableName() + " AS e INNER JOIN " + new SqlBuilding().getTableName() + " AS b ON (e.buildingID = b.id) WHERE e.ID = " + employee.getId();
    }
    
    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT e.*, b.name FROM " + getTableName() + " AS e INNER JOIN " + new SqlBuilding().getTableName() + " AS b ON (e.buildingID = b.id)";
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Employee(resultSet.getLong("ID"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("password"), 
                new Building(resultSet.getLong("buildingID"), resultSet.getString("name")));
    }
    
    public String getStatementSelectWithIdAndPasswordQuery() throws NoSuchAlgorithmException {
        return "SELECT e.*, b.name FROM " + getTableName() + " AS e INNER JOIN " + new SqlBuilding().getTableName() + " AS b ON (e.buildingID = b.id) WHERE e.ID = " + employee.getId() + " AND e.password = '" + HashPassword.hashPassword(employee.getPassword()) + "'";
    }

    @Override
    public List<IEntity> getListOfEntities() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
