package database.sql.sqlmodels;

import helper.HashPassword;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Building;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public class SqlEmployee extends SqlEntity<Employee> {
    public SqlEmployee(Employee employee){
        super(employee);
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
        return "SELECT e.*, b.name FROM " + getTableName() + " AS e INNER JOIN " + new SqlBuilding().getTableName() + " AS b ON (e.buildingID = b.id) WHERE e.ID = " + entity.getId();
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
    public Employee getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Employee(resultSet.getLong("ID"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("password"), 
                new Building(resultSet.getLong("buildingID"), resultSet.getString("name")));
    }
    
    public String getStatementSelectWithIdAndPasswordQuery() throws NoSuchAlgorithmException {
        return "SELECT e.*, b.name FROM " + getTableName() + " AS e INNER JOIN " + new SqlBuilding().getTableName() + " AS b ON (e.buildingID = b.id) WHERE e.ID = " + entity.getId() + " AND e.password = '" + HashPassword.hashPassword(entity.getPassword()) + "'";
    }
}
