package logics.impl;

import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import database.sql.connection.SqlConnectionFactory;
import java.util.List;
import logics.interfaces.IBookLogic;
import models.Book;
import models.CopyOfBook;
import java.sql.Connection;
import java.util.ArrayList;

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
            if(copyOfBookBroker.getCountOfAllCopiesOfBook(new CopyOfBook(book), connection) > 0)
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
            Book createdBook = bookBroker.createBook(book, connection);
            CopyOfBook copyOfBook = new CopyOfBook(createdBook, buildingID);
            List<CopyOfBook> copiesOfBook = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                copiesOfBook.add(copyOfBook);
            }
            copyOfBookBroker.createCopiesOfBook(copiesOfBook, connection);
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
    public List<CopyOfBook> readAllCopiesOfBookInBuilding(CopyOfBook copyOfBook) throws Exception {
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
