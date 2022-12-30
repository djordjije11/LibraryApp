package services;

import models.CopyOfBook;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class CopyOfBookService extends EntityService<CopyOfBook> {
    
    public CopyOfBookService(TcpClient tcpClient){
        super(tcpClient);
    }
}
