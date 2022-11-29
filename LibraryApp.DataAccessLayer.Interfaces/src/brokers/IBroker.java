/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package brokers;

/**
 *
 * @author Djordjije
 */
public interface IBroker {
    String getBrokerName();
    void closeConnection() throws Exception;
}
