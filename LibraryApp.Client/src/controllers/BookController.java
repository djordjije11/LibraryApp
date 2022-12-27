package controllers;

import models.dto.BookDto;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class BookController extends EntityController<BookDto> {
    public BookController(TcpClient tcpClient){
        super(tcpClient);
    }
}
