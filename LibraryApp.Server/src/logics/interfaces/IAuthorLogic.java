/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logics.interfaces;

import models.Author;

/**
 *
 * @author Djordjije
 */
public interface IAuthorLogic {
    Author createAuthor(String firstname, String lastname) throws Exception;
}
