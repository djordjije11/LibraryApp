package logics.impl;

import logics.interfaces.IEmployeeLogic;
import logics.operations.employee.GetEmployeeWithIdAndPassword;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public class EmployeeLogic implements IEmployeeLogic {
    @Override
    public Employee getEmployeeWithIdAndPassword(Employee employee) throws Exception {
        GetEmployeeWithIdAndPassword operation = new GetEmployeeWithIdAndPassword();
        operation.setEmployee(employee);
        return operation.execute();
    }
}
