/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logics.impl;

import database.sql.brokers.interfaces.IAuthorBroker;
import logics.interfaces.IAuthorLogic;
import models.Author;

/**
 *
 * @author Djordjije
 */
public class AuthorLogic implements IAuthorLogic {
    private final IAuthorBroker authorBroker;
    public AuthorLogic(IAuthorBroker authorBroker){
        this.authorBroker = authorBroker;
    }
    
    @Override
    public Author createAuthor(String firstname, String lastname) throws Exception {
        try{
            Author author = authorBroker.createAuthor(new Author(firstname, lastname));
            return author;
        } finally{
            authorBroker.closeConnection();
        }
    }
}