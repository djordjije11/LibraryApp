/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database.configurations;

/**
 *
 * @author Djordjije
 */
public interface IConfigurationManager {
    String getConfigParam(String key) throws Exception;
    void initialize(String filePath) throws Exception;
}
