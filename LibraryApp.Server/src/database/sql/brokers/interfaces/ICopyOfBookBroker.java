package database.sql.brokers.interfaces;

import java.util.List;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public interface ICopyOfBookBroker {
    List<CopyOfBook> createCopiesOfBook(CopyOfBook copyOfBook, Long amount) throws Exception;
    List<CopyOfBook> readAllCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception;
    Long getNumberOfCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception;
}
