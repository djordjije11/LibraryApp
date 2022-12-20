package main;

import database.configurations.ConfigFilePaths;
import database.configurations.IConfigurationManager;
import database.configurations.JsonFileConfigurationManager;
import database.sql.connectors.SqlConnector;
import java.net.ServerSocket;
import java.net.Socket;
import tcp.TcpCommunicator;
import tcp.TcpServer;

/**
 *
 * @author Djordjije
 */
public class Server {
    public static void main(String[] args) {
        try {
            IConfigurationManager configManager = new JsonFileConfigurationManager();
            configManager.initialize(ConfigFilePaths.SQL_JSON);
            SqlConnector.setConfigurationManager(configManager);
            ServerSocket socket = new ServerSocket(9001);
            while(true){
                Socket socketCommunication = socket.accept();
                new ClientHandler(new TcpServer(socketCommunication)).start();
            }
        } catch (Exception ex) {
            //bacio exception configManager jer ne moze da nadje fajl
            //bacio exception ServerSocket jer nije mogao da se pokrene
        }
    }
}
