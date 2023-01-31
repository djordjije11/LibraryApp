package controllers.impl;

import controllers.interfaces.IController;
import jdk.jshell.spi.ExecutionControl;
import logics.impl.EmployeeLogic;
import logics.interfaces.IEmployeeLogic;
import main.Server;
import message.Request;
import message.Response;
import models.Employee;
import tcp.TcpServer;

/**
 *
 * @author Djordjije
 */
public class EmployeeController implements IController {
    private IEmployeeLogic employeeLogic;
    private Server server;
    private TcpServer tcpServer;
    
    public EmployeeController(){
        employeeLogic = new EmployeeLogic();
    }
    public EmployeeController(Server server, TcpServer tcpServer){
        this();
        this.server = server;
        this.tcpServer = tcpServer;
    }
    
    @Override
    public Response handle(Request request) throws Exception {
        Response response = new Response();
        Employee employee = (Employee)request.getObject();
        Object dbObject = null;
        try{
            switch (request.getMethod()) {
            case LOGIN: //GET THE EMPLOYEE BY LOGIN INFORMATIONS INCLUDING THE buildingID OF THE BUILDING WHERE THE LOGGED IN EMPLOYEE WORKS
                Employee dbEmployee = employeeLogic.getEmployeeWithIdAndPassword(employee);
                tcpServer.setEmployee(dbEmployee);
                server.refreshEmployeesTableData();
                dbObject = dbEmployee;
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
