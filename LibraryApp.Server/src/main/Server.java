package main;

import database.configurations.ConfigFilePaths;
import database.configurations.SqlJsonFileConfigurationManager;
import database.sql.connection.SqlConnectionFactory;
import forms.ServerEmployeesForm;
import forms.ServerForm;
import helper.RandomID;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;
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
    private ServerEmployeesForm employeesForm;
    private SqlJsonFileConfigurationManager configManager;

    public Server() throws ParseException, IOException {
        configManager = new SqlJsonFileConfigurationManager(ConfigFilePaths.SQL_JSON);
    }
    public SqlJsonFileConfigurationManager getSqlConfigurationManager(){
        return configManager;
    }
    public List<TcpServer> getConnectedUsers(){
        return tcpServersList;
    }
    public void setServerEmployeesForm(ServerEmployeesForm form){
        employeesForm = form;
    }
    public boolean isServerUp(){
        return serverUp;
    }
    public void openServer() throws Exception{
        SqlConnectionFactory.initialize(configManager);
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
        SqlConnectionFactory.shutdown();
    }
    private void acceptClients() throws IOException{
        while(serverUp){
            TcpServer tcpServer = new TcpServer(socket.accept());
            addTcpServer(tcpServer);
            new ClientHandler(this, tcpServer, RandomID.getRandomID()).start();
        }
    }
    private synchronized void addTcpServer(TcpServer tcpServer){
        tcpServersList.add(tcpServer);
    }
    public synchronized void removeTcpServer(TcpServer tcpServer) throws IOException{
        tcpServer.closeConnection();
        tcpServersList.remove(tcpServer);
        refreshEmployeesTableData();
    }
    private synchronized void removeAllTcpServers() throws IOException{
        for (TcpServer tcpServer : tcpServersList) {
            tcpServer.closeConnection();
        }
        tcpServersList.clear();
        refreshEmployeesTableData();
    }
    public void refreshEmployeesTableData(){
        if(employeesForm != null){
            employeesForm.fireTableChange();
        }
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