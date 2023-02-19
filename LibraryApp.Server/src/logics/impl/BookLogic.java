package logics.impl;

import java.util.List;
import logics.interfaces.IBookLogic;
import models.Book;
import models.CopyOfBook;
import logics.operations.book.AddCopiesOfBookInBuilding;
import logics.operations.book.CreateBook;
import logics.operations.book.DeleteBook;
import logics.operations.book.FindBook;
import logics.operations.book.FindBooks;
import logics.operations.book.FindCopyOfBookInBuilding;
import logics.operations.book.GetCountOfCopiesOfBookInBuilding;
import logics.operations.book.ReadAllBooks;
import logics.operations.book.ReadAllCopiesOfBookInBuilding;
import logics.operations.book.UpdateBook;

/**
 *
 * @author Djordjije
 */
public class BookLogic implements IBookLogic {
    @Override
    public Book createBook(Book book) throws Exception {
        CreateBook operation = new CreateBook();
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public Book findBook(Book book) throws Exception {
        FindBook operation = new FindBook();
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public List<Book> findBooks(Book book) throws Exception {
        FindBooks operation = new FindBooks();
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public List<Book> readAllBooks() throws Exception {
        return new ReadAllBooks().execute();
    }
    @Override
    public Book updateBook(Book book) throws Exception {
        UpdateBook operation = new UpdateBook();
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public Book deleteBook(Book book) throws Exception {
        DeleteBook operation = new DeleteBook();
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public Book createBook(Book book, Long amount, Long buildingID) throws Exception {
        CreateBook operation1 = new CreateBook();
        operation1.setBook(book);
        Book dbBook = operation1.execute();
        AddCopiesOfBookInBuilding operation2 = new AddCopiesOfBookInBuilding();
        operation2.setCopyOfBook(new CopyOfBook(dbBook, buildingID));
        operation2.setAmount(amount);
        operation2.execute();
        return dbBook;
    }
    @Override
    public List<CopyOfBook> readAllCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
        ReadAllCopiesOfBookInBuilding operation = new ReadAllCopiesOfBookInBuilding();
        operation.setCopyOfBook(copyOfBook);
        return operation.execute();
    }
    @Override
    public Book updateBook(Book book, Long amount, Long buildingID) throws Exception {
        UpdateBook operation1 = new UpdateBook();
        operation1.setBook(book);
        Book dbBook = operation1.execute();
        AddCopiesOfBookInBuilding operation2 = new AddCopiesOfBookInBuilding();
        operation2.setCopyOfBook(new CopyOfBook(dbBook, buildingID));
        operation2.setAmount(amount);
        operation2.execute();
        return dbBook;
    }
    @Override
    public List<CopyOfBook> addCopiesOfBookInBuilding(CopyOfBook copyOfBook, Long amount) throws Exception {
        AddCopiesOfBookInBuilding operation = new AddCopiesOfBookInBuilding();
        operation.setCopyOfBook(copyOfBook);
        operation.setAmount(amount);
        return operation.execute();
    }
    @Override
    public Long getCountOfCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
        GetCountOfCopiesOfBookInBuilding operation = new GetCountOfCopiesOfBookInBuilding();
        operation.setCopyOfBook(copyOfBook);
        return operation.execute();
    }
    @Override
    public CopyOfBook findCopyOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
        FindCopyOfBookInBuilding operation = new FindCopyOfBookInBuilding();
        operation.setCopyOfBook(copyOfBook);
        return operation.execute();
    }
}
