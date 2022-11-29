/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.configurations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Djordjije
 */
public class JsonFileConfigurationManager implements IConfigurationManager {
    private Map<String, String> config = new HashMap<>();
    
    public Map<String, String> getConfig(){
        return config;
    }
    
    @Override
    public String getConfigParam(String key) throws Exception {
        if(config.containsKey(key) == false)
            throw new Exception("Missing ConfigParam with key '" + key + "'.");
        return config.get(key);
    }

    @Override
    public void initialize(String filePath) throws ParseException, IOException {
        JSONObject jsonObject = parseJSONFile(filePath);
        String url = jsonObject.get("url").toString();
        String user = jsonObject.get("user").toString();
        String password = jsonObject.get("password").toString();
        config.put(ConfigParamKeys.URL, url);
        config.put(ConfigParamKeys.USER, user);
        config.put(ConfigParamKeys.PASSWORD, password);
    }
    
    private JSONObject parseJSONFile(String filePath) throws ParseException, FileNotFoundException, IOException {
        return (JSONObject) (new JSONParser().parse(new FileReader(new File(filePath))));
    }
}
