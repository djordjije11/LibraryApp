/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database.sql.brokers.interfaces;

import java.util.List;
import models.Author;

/**
 *
 * @author Djordjije
 */
public interface IAuthorBroker extends IEntityBroker {
    Author createAuthor(Author author) throws Exception;
    Author readAuthor(Author author) throws Exception;
    List<Author> readAllAuthors(Author author) throws Exception;
    Author updateAuthor(Author author) throws Exception;
    Author deleteAuthor(Author author) throws Exception;
}
