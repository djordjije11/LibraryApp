package database.sql.sqlmodels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import models.Author;
import models.Book;
import models.CopyOfBook;
import models.Lending;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class SqlLending extends SqlEntity<Lending> {
    public SqlLending(Lending lending){
        super(lending);
    }
    public SqlLending(){}
    public SqlLending(List<Lending> listOfLendings){
        super(listOfLendings);
    }
    
    @Override
    public String getTableName() {
        return "lending";
    }
    @Override
    public String getPreparedStatementInsertQuery() {
        return "INSERT INTO " + getTableName() + "(bookID, copyofbookID, memberID, lending_date) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void setUpPreparedStatementInsert(PreparedStatement preparedStatement) throws SQLException {
        CopyOfBook copyOfBook = entity.getCopyOfBook();
        Book book = copyOfBook.getBook();
        preparedStatement.setLong(1, book.getId());
        preparedStatement.setLong(2, copyOfBook.getId());
        preparedStatement.setLong(3, entity.getMember().getId());
        preparedStatement.setDate(4, Date.valueOf(entity.getLendingDate()));
    }

    @Override
    public String getPreparedStatementUpdateQuery() {
        return "UPDATE " + getTableName() + " SET return_date = ? WHERE ID = ?";
    }

    @Override
    public void setUpPreparedStatementUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(entity.getReturnDate()));
        preparedStatement.setLong(2, entity.getId());
    }

    @Override
    public String getStatementSelectWithConditionQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT l.*, cob.buildingID, b.title, b.description, b.authorID, a.firstname AS author_firstname, a.lastname AS author_lastname, "
                + "m.firstname AS member_firstname, m.lastname AS member_lastname, m.birthday, m.email FROM " +  getTableName() + 
                " AS l JOIN " + new SqlMember().getTableName() + " AS m ON (l.memberID = m.ID) JOIN " + new SqlCopyOfBook().getTableName() + 
                " AS cob ON (l.copyofbookID = cob.ID) JOIN " + new SqlBook().getTableName() + " AS b ON(cob.bookID = b.ID) JOIN " + new SqlAuthor().getTableName()  + " AS a ON(b.authorID = a.ID)";
    }

    @Override
    public Lending getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author(resultSet.getLong("authorID"), resultSet.getString("author_firstname"), resultSet.getString("author_lastname"));
        Book book = new Book(resultSet.getLong("bookID"), resultSet.getString("title"), resultSet.getString("description"), author);
        CopyOfBook copyOfBook = new CopyOfBook(resultSet.getLong("copyofbookID"), book, resultSet.getLong("buildingID"));
        Member member = new Member(resultSet.getLong("memberID"), resultSet.getString("member_firstname"), resultSet.getString("member_lastname"), resultSet.getDate("birthday").toLocalDate(), resultSet.getString("email"));
        return new Lending(copyOfBook, member, resultSet.getDate("lending_date").toLocalDate());
    }
}
