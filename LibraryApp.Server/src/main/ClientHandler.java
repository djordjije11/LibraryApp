package main;

import controllers.impl.AuthorController;
import controllers.impl.BookController;
import controllers.impl.EmployeeController;
import controllers.impl.MemberController;
import java.io.IOException;
import jdk.jshell.spi.ExecutionControl;
import message.Request;
import message.Response;
import tcp.TcpServer;

/**
 *
 * @author Djordjije
 */
public class ClientHandler extends Thread {
    private final TcpServer tcpServer;
    private final String threadID;
    
    public ClientHandler(TcpServer tcpServer, String threadID){
        this.tcpServer = tcpServer;
        this.threadID = threadID;
        System.out.println("Connection opened: thread ID - " + threadID);
    }
    
    @Override
    public void run() {
        while(true){
            try {
                Request request = tcpServer.<Request>readObject();
                Response response = null;
                switch (request.getModelElement()) {
                    case EXIT_MESSAGE:
                        tcpServer.closeConnection();
                        return;
                    case EMPLOYEE:
                        response = new EmployeeController().handle(request);
                        break;
                    case MEMBER:
                        response = new MemberController().handle(request);
                        break;
                    case AUTHOR:
                        response = new AuthorController().handle(request);
                        break;
                    case BOOK:
                        response = new BookController().handle(request);
                        break;
                    default:
                        response = new Response();
                        response.setConfirmed(false);
                        response.setException(new ExecutionControl.NotImplementedException("There is not implemented action for revieved request."));
                }
                tcpServer.sendObject(response);
                
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Connection closed - thread ID: " + threadID );
                return;
            } catch(Exception ex){
                ex.printStackTrace();
                Response response = new Response();
                response.setConfirmed(false);
                response.setException(ex);
                try {
                    tcpServer.sendObject(response);
                } catch (IOException ex1) {
                    System.out.println("Connection closed - thread ID: " + threadID );
                    return;
                }
            }
        }
    }
}
