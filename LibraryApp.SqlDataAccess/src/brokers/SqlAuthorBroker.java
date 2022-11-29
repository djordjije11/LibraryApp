/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brokers;

import helper.EntitiesConverter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import models.Author;
import org.json.simple.parser.ParseException;
import sqlmodels.SqlAuthor;

/**
 *
 * @author Djordjije
 */
public class SqlAuthorBroker extends SqlEntityBroker implements IAuthorBroker  {

    public SqlAuthorBroker() throws SQLException{
        super();
    }
    
    @Override
    public synchronized Author createAuthor(Author author) throws SQLException, ParseException, IOException {
        return (Author) create(new SqlAuthor(author));
    }

    @Override
    public synchronized Author readAuthor(Author author) throws SQLException, ParseException, IOException {
        return (Author) read(new SqlAuthor(author));
    }

    @Override
    public synchronized List<Author> readAllAuthors(Author author) throws SQLException, ParseException, IOException {
        return EntitiesConverter.<Author>convertList(readAll(new SqlAuthor(author)));
    }

    @Override
    public synchronized Author updateAuthor(Author author) throws SQLException, ParseException, IOException {
        return (Author) update(new SqlAuthor(author));
    }

    @Override
    public synchronized Author deleteAuthor(Author author) throws SQLException, ParseException, IOException {
        return (Author) delete(new SqlAuthor(author));
    }
}
