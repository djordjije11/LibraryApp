package main;

import database.configurations.ConfigFilePaths;
import database.configurations.IConfigurationManager;
import database.configurations.JsonFileConfigurationManager;
import database.sql.connection.SqlConnectionFactory;
import forms.ServerForm;
import helper.RandomID;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import tcp.TcpServer;

/**
 *
 * @author Djordjije
 */
public class Server implements Runnable {
    ServerSocket socket;
    private final int PORT_NUMBER = 9001;
    private boolean serverUp = false;
    private final List<TcpServer> tcpServersList = new ArrayList<>();
    
    public boolean isServerUp(){
        return serverUp;
    }
    public void openServer() throws IOException{
        try {
            socket = new ServerSocket(PORT_NUMBER);
            serverUp = true;
            new Thread(this).start();
        } catch (IOException ex) {
            serverUp = false;
            throw ex;
        }
    }
    public void closeServer() throws IOException{
        serverUp = false;
        if(socket.isClosed() == false){
            socket.close();
            socket = null;
        }
        removeAllTcpServers();
    }
    private void acceptClients() throws IOException{
        while(serverUp){
            TcpServer tcpServer = new TcpServer(socket.accept());
            tcpServersList.add(tcpServer);
            new ClientHandler(this, tcpServer, RandomID.getRandomID()).start();
        }
    }
    public synchronized void removeTcpServer(TcpServer tcpServer) throws IOException{
        tcpServer.closeConnection();
        tcpServersList.remove(tcpServer);
    }
    private synchronized void removeAllTcpServers() throws IOException{
        for (TcpServer tcpServer : tcpServersList) {
            tcpServer.closeConnection();
        }
        tcpServersList.clear();
    }
    
    public static void main(String[] args) throws Exception {
        IConfigurationManager configManager = new JsonFileConfigurationManager();
        configManager.initialize(ConfigFilePaths.SQL_JSON);
        SqlConnectionFactory.initialize(configManager);
        new ServerForm(new Server()).setVisible(true);
    }        

    @Override
    public void run() {
        try {
            acceptClients();
        } catch (IOException ex) {
            System.out.println("Prekinuto prihvatanje klijenata");
        }
    }
}
