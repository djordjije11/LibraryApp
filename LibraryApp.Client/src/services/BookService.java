package services;

import models.dto.BookDto;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class BookService extends EntityService<BookDto> {
    public BookService(TcpClient tcpClient){
        super(tcpClient);
    }
}
