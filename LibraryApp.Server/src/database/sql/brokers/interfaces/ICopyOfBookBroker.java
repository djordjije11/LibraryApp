package database.sql.brokers.interfaces;

import java.sql.Connection;
import java.util.List;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public interface ICopyOfBookBroker {
    List<CopyOfBook> createCopiesOfBook(List<CopyOfBook> copiesOfBook, Connection connection) throws Exception;
    List<CopyOfBook> updateCopiesOfBook(List<CopyOfBook> copiesOfBook, Connection connection) throws Exception;
    CopyOfBook findCopyOfBookInBuilding(CopyOfBook copyOfBook, Connection connection) throws Exception;
    List<CopyOfBook> readAllCopiesOfBookInBuilding(CopyOfBook copyOfBook, Connection connection) throws Exception;
    Long getCountOfCopiesOfBookInBuilding(CopyOfBook copyOfBook, Connection connection) throws Exception;
    Long getCountOfAllCopiesOfBook(CopyOfBook copyOfBook, Connection connection) throws Exception;
}
