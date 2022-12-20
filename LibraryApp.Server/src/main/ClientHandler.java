package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Request;
import tcp.TcpServer;

/**
 *
 * @author Djordjije
 */
public class ClientHandler extends Thread {

    private final TcpServer tcpServer;
    
    public ClientHandler(TcpServer tcpServer){
        this.tcpServer = tcpServer;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                Request request = tcpServer.readObject();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
