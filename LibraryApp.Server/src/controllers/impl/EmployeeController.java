package controllers.impl;

import controllers.interfaces.IController;
import database.sql.brokers.impl.SqlEmployeeBroker;
import jdk.jshell.spi.ExecutionControl;
import logics.impl.EmployeeLogic;
import logics.interfaces.IEmployeeLogic;
import message.Request;
import message.Response;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public class EmployeeController implements IController {
    private IEmployeeLogic employeeLogic;
    
    public EmployeeController(){
        employeeLogic = new EmployeeLogic(new SqlEmployeeBroker());
    }
    
    @Override
    public Response handle(Request request) throws Exception {
        Response response = new Response();
        Employee employee = (Employee)request.getObject();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case LOGIN: //GET THE EMPLOYEE BY LOGIN INFORMATIONS INCLUDING THE buildingID OF THE BUILDING WHERE THE LOGGED IN EMPLOYEE WORKS
                dbObject = employeeLogic.loginEmployee(employee);
                break;
            default:
                throw new ExecutionControl.NotImplementedException("The request method is not able for a Employee object.");
            }
            response.setObject(dbObject);
            response.setConfirmed(true);
            return response;
        } catch(Exception ex){
            response.setConfirmed(false);
            response.setException(ex);
            return response;
        }
    }
    
}
