package database.configurations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Djordjije
 */
public class SqlJsonFileConfigurationManager implements IConfigurationManager {
    private final Map<String, String> config = new HashMap<>();
    private final String filePath;
    
    public SqlJsonFileConfigurationManager(String filePath) throws ParseException, IOException{
        this.filePath = filePath;
        refresh();
    }
    public final void refresh() throws ParseException, IOException{
        JSONObject jsonObject = parseJSONFile(filePath);
        String url = jsonObject.get(ConfigParamKeys.URL).toString();
        String user = jsonObject.get(ConfigParamKeys.USER).toString();
        String password = jsonObject.get(ConfigParamKeys.PASSWORD).toString();
        config.put(ConfigParamKeys.URL, url);
        config.put(ConfigParamKeys.USER, user);
        config.put(ConfigParamKeys.PASSWORD, password);
    }
    @Override
    public String getConfigParam(String key) throws Exception {
        if(config.containsKey(key) == false)
            throw new Exception("Missing ConfigParam with key '" + key + "'.");
        return config.get(key);
    }
    public void updateData(HashMap<String, String> data) throws IOException{
        HashMap<String, String> newConfig = new HashMap<>();
        for (Map.Entry<String, String> entry : config.entrySet()) {
            newConfig.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(key != null && key.equals(ConfigParamKeys.URL) || key.equals(ConfigParamKeys.USER) || key.equals(ConfigParamKeys.PASSWORD)){
                newConfig.put(key, value);
            }
        }
        try(PrintWriter fileWriter = new PrintWriter(new FileWriter(filePath))) {
            fileWriter.write(new JSONObject(newConfig).toJSONString());
        }
    }
    private JSONObject parseJSONFile(String filePath) throws ParseException, FileNotFoundException, IOException {
        return (JSONObject) (new JSONParser().parse(new FileReader(new File(filePath))));
    }
    public void test(){
        for (Map.Entry<String, String> entry : config.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " " + value);
        }
    }
}
