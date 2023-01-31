package logics.interfaces;

import models.Employee;

/**
 *
 * @author Djordjije
 */
public interface IEmployeeLogic {
    Employee getEmployeeWithIdAndPassword(Employee employee) throws Exception;
}
