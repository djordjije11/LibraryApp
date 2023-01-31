package logics.operations.book;

import database.sql.brokers.impl.SqlAuthorBroker;
import database.sql.brokers.impl.SqlBookBroker;
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
    private Book book;
    private final IBookBroker bookBroker;
    private final IAuthorBroker authorBroker;
    
    public CreateBook(){
        bookBroker = new SqlBookBroker();
        authorBroker = new SqlAuthorBroker();
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
        //IF THE BOOK DOESN'T HAVE AN AUTHOR IT WILL BE NULL AND THERE WILL BE NO EXCEPTION
        if(author != null && authorBroker.checkIfAuthorExists(author, connection) == false){
            throw new Exception("The author of the book is not in the database!");
        }
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
