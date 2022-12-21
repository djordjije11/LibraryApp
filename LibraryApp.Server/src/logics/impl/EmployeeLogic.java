package logics.impl;

import database.sql.brokers.interfaces.IEmployeeBroker;
import database.sql.connection.SqlConnectionFactory;
import java.sql.Connection;
import logics.interfaces.IEmployeeLogic;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public class EmployeeLogic implements IEmployeeLogic {
    private final IEmployeeBroker employeeBroker;
    public EmployeeLogic(IEmployeeBroker employeeBroker){
        this.employeeBroker = employeeBroker;
    }
    @Override
    public Employee loginEmployee(Employee employee) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return employeeBroker.findEmployeeWithIdAndPassword(employee, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    
}
