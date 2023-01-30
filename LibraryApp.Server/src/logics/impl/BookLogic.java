package logics.impl;

import database.sql.brokers.interfaces.IAuthorBroker;
import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import database.sql.connection.SqlConnectionFactory;
import java.util.List;
import logics.interfaces.IBookLogic;
import models.Book;
import models.CopyOfBook;
import java.sql.Connection;
import java.util.ArrayList;
import logics.operations.book.CreateBook;
import logics.operations.book.CreateBookAndAddCopies;
import logics.operations.book.DeleteBook;
import logics.operations.book.FindBook;
import logics.operations.book.FindBooks;
import logics.operations.book.ReadAllBooks;
import logics.operations.book.ReadAllCopiesOfBookInBuilding;
import logics.operations.book.UpdateBook;

/**
 *
 * @author Djordjije
 */
public class BookLogic implements IBookLogic {
    private final IBookBroker bookBroker;
    private final ICopyOfBookBroker copyOfBookBroker;
    private final IAuthorBroker authorBroker;
    
    public BookLogic(IBookBroker bookBroker, ICopyOfBookBroker copyOfBookBroker, IAuthorBroker authorBroker){
        this.bookBroker = bookBroker;
        this.copyOfBookBroker = copyOfBookBroker;
        this.authorBroker = authorBroker;
    }
    @Override
    public Book createBook(Book book) throws Exception {
        CreateBook operation = new CreateBook(bookBroker, authorBroker);
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public Book findBook(Book book) throws Exception {
        FindBook operation = new FindBook(bookBroker);
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public List<Book> findBooks(Book book) throws Exception {
        FindBooks operation = new FindBooks(bookBroker);
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public List<Book> readAllBooks() throws Exception {
        return new ReadAllBooks(bookBroker).execute();
    }
    @Override
    public Book updateBook(Book book) throws Exception {
        UpdateBook operation = new UpdateBook(bookBroker, authorBroker);
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public Book deleteBook(Book book) throws Exception {
        DeleteBook operation = new DeleteBook(bookBroker, copyOfBookBroker);
        operation.setBook(book);
        return operation.execute();
    }
    @Override
    public Book createBook(Book book, Long amount, Long buildingID) throws Exception {
        CreateBookAndAddCopies operation = new CreateBookAndAddCopies(bookBroker, authorBroker, copyOfBookBroker);
        operation.setBook(book);
        operation.setAmount(amount);
        operation.setBuildingId(buildingID);
        return operation.execute();
    }
    @Override
    public List<CopyOfBook> readAllCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
        ReadAllCopiesOfBookInBuilding operation = new ReadAllCopiesOfBookInBuilding(bookBroker, copyOfBookBroker);
        operation.setCopyOfBook(copyOfBook);
        return operation.execute();
    }

    @Override
    public Book updateBook(Book book, Long amount, Long buildingID) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            Book updatedBook = bookBroker.updateBook(book, connection);
            CopyOfBook copyOfBook = new CopyOfBook(updatedBook, buildingID);
            List<CopyOfBook> copiesOfBook = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                copiesOfBook.add(copyOfBook);
            }
            copyOfBookBroker.createCopiesOfBook(copiesOfBook, connection);
            connection.commit();
            return updatedBook;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }

    @Override
    public List<CopyOfBook> addCopiesOfBookInBuilding(CopyOfBook copyOfBook, Long amount) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            List<CopyOfBook> copiesOfBook = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                copiesOfBook.add(copyOfBook);
            }
            List<CopyOfBook> dbCopiesOfBook = copyOfBookBroker.createCopiesOfBook(copiesOfBook, connection);
            connection.commit();
            return dbCopiesOfBook;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }

    @Override
    public Long getCountOfCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return copyOfBookBroker.getCountOfCopiesOfBookInBuilding(copyOfBook, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }

    @Override
    public CopyOfBook findCopyOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return copyOfBookBroker.findCopyOfBookInBuilding(copyOfBook, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
}
