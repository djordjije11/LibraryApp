package logics.operations.book;

import database.sql.brokers.interfaces.IAuthorBroker;
import database.sql.brokers.interfaces.IBookBroker;
import java.sql.Connection;
import logics.operations.Operation;
import models.Author;
import models.Book;

/**
 *
 * @author Djordjije
 */
public class CreateBook extends Operation<Book> {
    protected Book book;
    protected final IBookBroker bookBroker;
    protected final IAuthorBroker authorBroker;
    
    public CreateBook(IBookBroker bookBroker, IAuthorBroker authorBroker){
        this.bookBroker = bookBroker;
        this.authorBroker = authorBroker;
    }
    public void setBook(Book book){
        this.book = book;
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        String title = book.getTitle();
        if(title == null || title.isBlank() == true){
            throw new Exception("The book cannot be created because there is no title.");
        }
        Author author = book.getAuthor();
        if(author == null)
            return; //IF THE BOOK DOESN'T HAVE THE AUTHOR
        if(authorBroker.findAuthor(book.getAuthor(), connection) == null)
            throw new Exception("The author of the book is not in the database!");
    }
    @Override
    protected Book executeOperation(Connection connection) throws Exception {
        return bookBroker.createBook(book, connection);
    }

    @Override
    protected boolean isTransaction() {
        return true;
    }
}
