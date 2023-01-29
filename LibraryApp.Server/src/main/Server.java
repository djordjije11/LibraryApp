package main;

import database.configurations.ConfigFilePaths;
import database.configurations.SqlJsonFileConfigurationManager;
import database.sql.connection.SqlConnectionFactory;
import forms.ServerForm;
import helper.RandomID;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tcp.TcpServer;

/**
 *
 * @author Djordjije
 */
public class Server implements Runnable {
    private ServerSocket socket;
    private final int PORT_NUMBER = 9001;
    private boolean serverUp = false;
    private final List<TcpServer> tcpServersList = new ArrayList<>();
    private SqlJsonFileConfigurationManager configManager;
    
    public boolean isServerUp(){
        return serverUp;
    }
    public void openServer() throws Exception{
        initializeSqlConnectionFactory();
        try {
            socket = new ServerSocket(PORT_NUMBER);
            serverUp = true;
            new Thread(this).start();
        } catch (IOException ex) {
            serverUp = false;
            throw ex;
        }
    }
    public void closeServer() throws Exception{
        serverUp = false;
        if(socket.isClosed() == false){
            socket.close();
            socket = null;
        }
        removeAllTcpServers();
        shutdownSqlConnectionFactory();
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
    
    private void initializeSqlConnectionFactory() throws Exception{
        configManager = new SqlJsonFileConfigurationManager(ConfigFilePaths.SQL_JSON);
        SqlConnectionFactory.initialize(configManager);
    }
    
    private void shutdownSqlConnectionFactory() throws SQLException{
        SqlConnectionFactory.shutdown();
    }
    
    public static void main(String[] args) throws Exception {
        new ServerForm(new Server()).setVisible(true);
    }        

    @Override
    public void run() {
        try {
            System.out.println("Pokrenuto prihvatanje klijenata");
            acceptClients();
        } catch (IOException ex) {
            System.out.println("Prekinuto prihvatanje klijenata");
        }
    }
}
