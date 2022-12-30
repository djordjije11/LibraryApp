package services;

import models.Author;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class AuthorService extends EntityService<Author> {
    
    public AuthorService(TcpClient tcpClient){
        super(tcpClient);
    }
}
