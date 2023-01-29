package database.configurations;

/**
 *
 * @author Djordjije
 */
public interface IConfigurationManager {
    String getConfigParam(String key) throws Exception;
}
