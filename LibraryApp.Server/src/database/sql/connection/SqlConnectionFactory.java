package database.sql.connection;

import java.util.ArrayList;
import java.util.List;
import database.configurations.ConfigParamKeys;
import database.configurations.IConfigurationManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Djordjije
 */
public class SqlConnectionFactory {
    private static List<Connection> connectionPool;
    private static List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 30;
    private static final int MAX_TIMEOUT = 10000;
    private static IConfigurationManager configManager;
    
    public static void initialize(IConfigurationManager configManager) throws Exception{
        SqlConnectionFactory.configManager = configManager;
        connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }
    }
    
    private static Connection createConnection() throws Exception{
        if(configManager == null) 
            throw new Exception("Missing ConfigurationManager!");
        return DriverManager.getConnection(configManager.getConfigParam(ConfigParamKeys.URL), configManager.getConfigParam(ConfigParamKeys.USER), configManager.getConfigParam(ConfigParamKeys.PASSWORD));
    }
    
    public static Connection getConnection() throws Exception {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection());
            } else {
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        if(connection == null || connection.isValid(MAX_TIMEOUT) == false){
            connection = createConnection();
        }
        usedConnections.add(connection);
        connection.setAutoCommit(false);
        return connection;
    }
    
    public static boolean releaseConnection(Connection connection) throws SQLException {
        if(connection == null || connection.isValid(MAX_TIMEOUT) == false)
            return false;
        if(usedConnections.remove(connection) == true)
            return connectionPool.add(connection);
        else return false;
    }
    public static void shutdown() throws SQLException {
        //usedConnections.forEach(this::releaseConnection);
        for (Connection usedConnection : usedConnections) {
            releaseConnection(usedConnection);
        }
        for (Connection connection : connectionPool) {
            if(connection != null && connection.isClosed() == false){
                connection.close();
            }
        }
        connectionPool.clear();
        configManager = null;
    }
}
