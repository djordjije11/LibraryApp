package database.sql.brokers.interfaces;

import java.util.List;
import models.Lending;
import java.sql.Connection;

/**
 *
 * @author Djordjije
 */
public interface ILendingBroker {
    List<Lending> createLendings(List<Lending> lendings, Connection connection) throws Exception;
    List<Lending> updateLendings(List<Lending> lendings, Connection connection) throws Exception;
}
