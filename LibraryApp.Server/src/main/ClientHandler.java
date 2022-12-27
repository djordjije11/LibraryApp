package main;

import controllers.impl.AuthorController;
import controllers.impl.BookController;
import controllers.impl.CopyOfBookController;
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
    private final Server server;
    private final TcpServer tcpServer;
    private final String threadID;
    
    public ClientHandler(Server server, TcpServer tcpServer, String threadID){
        this.server = server;
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
                        server.removeTcpServer(tcpServer);
                        System.out.println("Connection closed - thread ID: " + threadID );
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
                    case COPYOFBOOK:
                        response = new CopyOfBookController().handle(request);
                        break;
                    default:
                        response = new Response();
                        response.setConfirmed(false);
                        response.setException(new ExecutionControl.NotImplementedException("There is not implemented action for revieved request."));
                }
                tcpServer.sendObject(response);
            } catch (IOException | ClassNotFoundException ex) {
                try {
                    server.removeTcpServer(tcpServer);
                    System.out.println("Connection closed - thread ID: " + threadID );
                } catch (IOException ex1) {
                    System.out.println("Exception while trying to close the socket communication in the ClientHandler " + threadID + ": \n" + ex1.getMessage());
                }
                return;
            } catch(Exception ex){
                Response response = new Response();
                response.setConfirmed(false);
                response.setException(ex);
                try {
                    tcpServer.sendObject(response);
                } catch (IOException ex1) {
                    try {
                        server.removeTcpServer(tcpServer);
                        System.out.println("Connection closed - thread ID: " + threadID );
                    } catch (IOException ex2) {
                        System.out.println("Exception while trying to close the socket communication in the ClientHandler " + threadID + ": \n" + ex1.getMessage());
                    }
                    return;
                }
            }
        }
    }
}
