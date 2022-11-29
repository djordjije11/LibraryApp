/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import brokers.SqlAuthorBroker;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logics.impl.AuthorLogic;

/**
 *
 * @author Djordjije
 */
public class Test {
    public static void main(String[] args) {
        try {
            new AuthorLogic(new SqlAuthorBroker()).createAuthor("Dositej", "Obradović");
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
