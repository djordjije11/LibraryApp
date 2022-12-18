package logics.impl;

import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import java.util.List;
import logics.interfaces.IBookLogic;
import models.Book;
import models.CopyOfBook;

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
        try{
            bookBroker.openConnection();
            bookBroker.openTransaction();
            Book createdBook = bookBroker.createBook(book);
            bookBroker.commitTransaction();
            return createdBook;
        } catch(Exception ex){
            bookBroker.rollbackTransaction();
            throw ex;
        } finally{
            bookBroker.closeConnection();
        }
    }
    @Override
    public Book findBook(Book book) throws Exception {
        try{
            bookBroker.openConnection();
            return bookBroker.findBook(book);
        } finally{
            bookBroker.closeConnection();
        }
    }
    @Override
    public List<Book> findBooks(Book book) throws Exception {
        try{
            bookBroker.openConnection();
            return bookBroker.findBooks(book);
        } finally{
            bookBroker.closeConnection();
        }
    }
    @Override
    public List<Book> readAllBooks() throws Exception {
        try{
            bookBroker.openConnection();
            return bookBroker.readAllBooks(new Book());
        } finally{
            bookBroker.closeConnection();
        }
    }
    @Override
    public Book updateBook(Book book) throws Exception {
        try{
            bookBroker.openConnection();
            bookBroker.openTransaction();
            Book updatedBook = bookBroker.updateBook(book);
            bookBroker.commitTransaction();
            return updatedBook;
        } catch(Exception ex){
            bookBroker.rollbackTransaction();
            throw ex;
        } finally{
            bookBroker.closeConnection();
        }
    }
    @Override
    public Book deleteBook(Book book) throws Exception {
        //ovu logiku treba obnoviti kasnije
        try{
            bookBroker.openConnection();
            bookBroker.openTransaction();
            Book deletedBook = bookBroker.deleteBook(book);
            bookBroker.commitTransaction();
            return deletedBook;
        } catch(Exception ex){
            bookBroker.rollbackTransaction();
            throw ex;
        } finally{
            bookBroker.closeConnection();
        }
    }

    @Override
    public Book createBook(Book book, Long amount, Long buildingID) throws Exception {
        try{
            bookBroker.openConnection();
            bookBroker.openTransaction();
            Book createdBook = bookBroker.createBook(book);
            copyOfBookBroker.createCopiesOfBook(new CopyOfBook(book.getId(), buildingID), amount);
            bookBroker.commitTransaction();
            return createdBook;
        } catch(Exception ex){
            bookBroker.rollbackTransaction();
            throw ex;
        } finally{
            bookBroker.closeConnection();
        }
    }

    @Override
    public List<CopyOfBook> findCopiesOfBookInBuilding(CopyOfBook copyOfbook) throws Exception {
        try{
            bookBroker.openConnection();
            return copyOfBookBroker.readAllCopiesOfBookInBuilding(copyOfbook);
        } finally{
            bookBroker.closeConnection();
        }
    }

    @Override
    public Book updateBook(Book book, Long amount, Long buildingID) throws Exception {
        try{
            bookBroker.openConnection();
            bookBroker.openTransaction();
            Book updatedBook = bookBroker.updateBook(book);
            copyOfBookBroker.createCopiesOfBook(new CopyOfBook(book.getId(), buildingID), amount);
            bookBroker.commitTransaction();
            return updatedBook;
        } catch(Exception ex){
            bookBroker.rollbackTransaction();
            throw ex;
        } finally{
            bookBroker.closeConnection();
        }
    }

    @Override
    public List<CopyOfBook> addCopiesOfBookInBuilding(CopyOfBook copyOfBook, Long amount) throws Exception {
        try{
            bookBroker.openConnection();
            bookBroker.openTransaction();
            List<CopyOfBook> copiesOfBook = copyOfBookBroker.createCopiesOfBook(copyOfBook, amount);
            bookBroker.commitTransaction();
            return copiesOfBook;
        } catch(Exception ex){
            bookBroker.rollbackTransaction();
            throw ex;
        } finally{
            bookBroker.closeConnection();
        }
    }
}
