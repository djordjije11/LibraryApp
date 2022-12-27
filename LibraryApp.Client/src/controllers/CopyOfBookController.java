package controllers;

import models.CopyOfBook;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class CopyOfBookController extends EntityController<CopyOfBook> {
    
    public CopyOfBookController(TcpClient tcpClient){
        super(tcpClient);
    }
}
