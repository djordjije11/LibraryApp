/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database.sql.brokers.interfaces;

/**
 *
 * @author Djordjije
 */
public interface IEntityBroker {
    void openConnection() throws Exception;
    void closeConnection() throws Exception;
    void openTransaction() throws Exception;
    void commitTransaction() throws Exception;
    void rollbackTransaction() throws Exception;
}
