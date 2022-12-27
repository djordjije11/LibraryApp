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
