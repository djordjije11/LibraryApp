/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.sql.connectors;

import database.configurations.ConfigParamKeys;
import database.configurations.IConfigurationManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Djordjije
 */
public class SqlConnector {
    private static Connection connection;
    private static IConfigurationManager configManager;
    
    public static void setConfigurationManager(IConfigurationManager configManager){
        SqlConnector.configManager = configManager;
    }
    public synchronized static Connection getConnection() throws Exception{
        if(connection == null)
            openConnection();
        return connection;
    }
    public synchronized static void openConnection() throws Exception{
        if(configManager == null) 
            throw new Exception("Missing ConfigurationManager!");
        connection = DriverManager.getConnection(configManager.getConfigParam(ConfigParamKeys.URL), configManager.getConfigParam(ConfigParamKeys.USER), configManager.getConfigParam(ConfigParamKeys.PASSWORD));
    }
    public synchronized static void closeConnection() throws SQLException{
        if(connection == null) 
            return;
        connection.close();
        connection = null;
    }
}
