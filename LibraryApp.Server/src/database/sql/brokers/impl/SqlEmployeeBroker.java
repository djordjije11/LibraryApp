package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IEmployeeBroker;
import database.sql.sqlmodels.SqlEmployee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import models.Employee;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public class SqlEmployeeBroker extends SqlEntityBroker<Employee> implements IEmployeeBroker {
    @Override
    public synchronized Employee findEmployeeWithIdAndPassword(Employee employee, Connection connection) throws Exception {
        SqlEmployee sqlEmployee = new SqlEmployee(employee);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlEmployee.getStatementSelectWithIdAndPasswordQuery());
        resultSet.next();
        IEntity instance = sqlEmployee.getEntityFromResultSet(resultSet);
        resultSet.close();
        statement.close();
        return (Employee)instance;
    }
}
