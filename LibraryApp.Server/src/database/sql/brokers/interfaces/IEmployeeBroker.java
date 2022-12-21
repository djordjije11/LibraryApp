package database.sql.brokers.interfaces;

import java.sql.Connection;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public interface IEmployeeBroker {
    Employee findEmployeeWithIdAndPassword(Employee employee, Connection connection) throws Exception;
}
