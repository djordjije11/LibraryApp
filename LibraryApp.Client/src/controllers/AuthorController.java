/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.Author;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class AuthorController extends EntityController<Author> {
    
    public AuthorController(TcpClient tcpClient){
        super(tcpClient);
    }
    
}
