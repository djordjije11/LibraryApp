/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Djordjije
 */
public class SqlConnector {
    private static Connection connection;
    private static final String configPath = "D:\\MyDocs\\Repositories\\LibraryApp\\LibraryApp.SqlDataAccess\\configurations\\config.sql.json";
    
    public synchronized static void closeConnection() throws SQLException{
        if(connection == null) 
            return;
        connection.close();
        connection = null;
    }
    
    public synchronized static Connection getConnection() throws SQLException, ParseException, IOException{
        if(connection == null)
            openConnection();
        return connection;
    }
    
    private static void openConnection() throws SQLException, ParseException, IOException{
        //String url = "jdbc:mysql://localhost:3306/library_app_schema";
        //String user = "root";
        //String password = "admin";
        JSONObject jsonObject = parseJSONFile(configPath);
        String url = jsonObject.get("url").toString();
        String user = jsonObject.get("user").toString();
        String password = jsonObject.get("password").toString();
        connection = DriverManager.getConnection(url, user, password);
    }
    private static JSONObject parseJSONFile(String path) throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new FileReader(new File(path)));
        return object;
    }
}
