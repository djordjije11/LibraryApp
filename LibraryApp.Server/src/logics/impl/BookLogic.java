package logics.impl;

import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import database.sql.connection.SqlConnectionFactory;
import java.util.List;
import logics.interfaces.IBookLogic;
import models.Book;
import models.CopyOfBook;
import java.sql.Connection;

/**
 *
 * @author Djordjije
 */
public class BookLogic implements IBookLogic {
    private final IBookBroker bookBroker;
    private final ICopyOfBookBroker copyOfBookBroker;
    
    public BookLogic(IBookBroker bookBroker, ICopyOfBookBroker copyOfBookBroker){
        this.bookBroker = bookBroker;
        this.copyOfBookBroker = copyOfBookBroker;
    }
    @Override
    public Book createBook(Book book) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            Book createdBook = bookBroker.createBook(book, connection);
            connection.commit();
            return createdBook;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public Book findBook(Book book) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return bookBroker.findBook(book, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public List<Book> findBooks(Book book) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return bookBroker.findBooks(book, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public List<Book> readAllBooks() throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return bookBroker.readAllBooks(new Book(), connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
    @Override
    public Book updateBook(Book book) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            Book updatedBook = bookBroker.updateBook(book, connection);
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
    public Book deleteBook(Book book) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            if(copyOfBookBroker.getNumberOfAllCopiesOfBook(new CopyOfBook(book.getId()), connection) > 0)
                throw new Exception("Book can't be deleted if it is already have been copied.");
            Book deletedBook = bookBroker.deleteBook(book, connection);
            connection.commit();
            return deletedBook;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }

    @Override
    public Book createBook(Book book, Long amount, Long buildingID) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            Book createdBook = bookBroker.createBook(book, connection);
            copyOfBookBroker.createCopiesOfBook(new CopyOfBook(book.getId(), buildingID), amount, connection);
            connection.commit();
            return createdBook;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }

    @Override
    public List<CopyOfBook> findCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return copyOfBookBroker.readAllCopiesOfBookInBuilding(copyOfBook, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }

    @Override
    public Book updateBook(Book book, Long amount, Long buildingID) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            Book updatedBook = bookBroker.updateBook(book, connection);
            copyOfBookBroker.createCopiesOfBook(new CopyOfBook(book.getId(), buildingID), amount, connection);
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
            connection.setAutoCommit(false);
            List<CopyOfBook> copiesOfBook = copyOfBookBroker.createCopiesOfBook(copyOfBook, amount, connection);
            connection.commit();
            return copiesOfBook;
        } catch(Exception ex){
            connection.rollback();
            throw ex;
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }

    @Override
    public Long getNumberOfCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
        Connection connection = SqlConnectionFactory.getConnection();
        try{
            return copyOfBookBroker.getNumberOfCopiesOfBookInBuilding(copyOfBook, connection);
        } finally{
            SqlConnectionFactory.releaseConnection(connection);
        }
    }
}
