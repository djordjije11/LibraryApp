package logics.operations.employee;

import database.sql.brokers.impl.SqlEmployeeBroker;
import database.sql.brokers.interfaces.IEmployeeBroker;
import java.sql.Connection;
import logics.operations.Operation;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public class GetEmployeeWithIdAndPassword extends Operation<Employee> {
    private final IEmployeeBroker employeeBroker;
    private Employee employee;
    
    public GetEmployeeWithIdAndPassword(){
        employeeBroker = new SqlEmployeeBroker();
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        //NO PRECONDITION
    }

    @Override
    protected Employee executeOperation(Connection connection) throws Exception {
        return employeeBroker.findEmployeeWithIdAndPassword(employee, connection);
    }

    @Override
    protected boolean isTransaction() {
        return false;
    }
}
