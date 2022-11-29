/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Djordjije
 */
public class SqlConnector {
    private static SqlConnector connector;
    private Connection connection;
    
    private SqlConnector() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/library_app_schema";
        String user = "root";
        String password = "admin";
        connection = DriverManager.getConnection(url, user, password);
    }
    
    public static SqlConnector getInstance() throws SQLException{
        if(connector == null) connector = new SqlConnector();
        return connector;
    }
    
    public void closeConnection() throws SQLException{
        if(connection == null) 
            return;
        connection.close();
        connector = null;
        connection = null;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
