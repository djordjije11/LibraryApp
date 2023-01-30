package tcp;

import java.io.IOException;
import java.net.Socket;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public class TcpServer extends TcpCommunicator {
    private Employee employee;
    public TcpServer(Socket socketCommunication) throws IOException{
        super(socketCommunication);
    }
    public Employee getEmployee(){
        return employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
