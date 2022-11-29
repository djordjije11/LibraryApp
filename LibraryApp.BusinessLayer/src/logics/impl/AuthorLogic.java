/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logics.impl;

import brokers.IAuthorBroker;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void createAuthor(String firstname, String lastname) {
        try {
            Author author = authorBroker.createAuthor(new Author(firstname, lastname));
            System.out.println(author.getId() + " " + author.getFirstname() + " " + author.getLastname());
        } catch (Exception ex) {
            Logger.getLogger(AuthorLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
