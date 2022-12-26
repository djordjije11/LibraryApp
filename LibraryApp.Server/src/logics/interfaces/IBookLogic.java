package logics.interfaces;

import java.util.List;
import models.Book;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public interface IBookLogic {
    Book createBook(Book book) throws Exception;
    Book createBook(Book book, Long amount, Long buildingID) throws Exception;
    Book findBook(Book book) throws Exception;
    List<CopyOfBook> findCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception;
    Long getCountOfCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception;
    List<Book> findBooks(Book book) throws Exception;
    List<Book> readAllBooks() throws Exception;
    Book updateBook(Book book) throws Exception;
    Book updateBook(Book book, Long amount, Long buildingID) throws Exception;
    List<CopyOfBook> addCopiesOfBookInBuilding(CopyOfBook copyOfBook, Long amount) throws Exception;
    Book deleteBook(Book book) throws Exception;
}
