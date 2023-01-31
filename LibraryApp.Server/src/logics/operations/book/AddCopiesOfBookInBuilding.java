package logics.operations.book;

import database.sql.brokers.impl.SqlBookBroker;
import database.sql.brokers.impl.SqlBuildingBroker;
import database.sql.brokers.impl.SqlCopyOfBookBroker;
import database.sql.brokers.interfaces.IBookBroker;
import database.sql.brokers.interfaces.IBuildingBroker;
import database.sql.brokers.interfaces.ICopyOfBookBroker;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import logics.operations.Operation;
import models.Book;
import models.Building;
import models.CopyOfBook;

/**
 *
 * @author Djordjije
 */
public class AddCopiesOfBookInBuilding extends Operation<List<CopyOfBook>> {
    private final IBookBroker bookBroker;
    private final ICopyOfBookBroker copyOfBookBroker;
    private final IBuildingBroker buildingBroker;
    private CopyOfBook copyOfBook;
    private Long amount;
    
    public AddCopiesOfBookInBuilding(){
        bookBroker = new SqlBookBroker();
        copyOfBookBroker = new SqlCopyOfBookBroker();
        buildingBroker = new SqlBuildingBroker();
    }
    public void setCopyOfBook(CopyOfBook copyOfBook){
        this.copyOfBook = copyOfBook;
    }
    public void setAmount(Long amount){
        this.amount = amount;
    }
    @Override
    protected List<CopyOfBook> executeOperation(Connection connection) throws Exception {
        List<CopyOfBook> copiesOfBook = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            copiesOfBook.add(copyOfBook);
        }
        return copyOfBookBroker.createCopiesOfBook(copiesOfBook, connection);
    }
    @Override
    protected void checkPrecondition(Connection connection) throws Exception {
        if(amount < 0){
            throw new Exception("The amount of copies of book to be added must be positive.");
        }
        if(buildingBroker.checkIfBuildingExists(new Building(copyOfBook.getBuildingId()), connection) == false){
            throw new Exception("The building is not in the database.");
        }
        Book book = copyOfBook.getBook();
        if(book == null || bookBroker.checkIfBookExists(book, connection) == false){
            throw new Exception("The book is not in the database.");
        }
    }
    @Override
    protected boolean isTransaction() {
        return true;
    }
}
