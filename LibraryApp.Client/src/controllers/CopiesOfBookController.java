package controllers;

import models.dto.CopiesOfBookDto;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class CopiesOfBookController extends EntityController<CopiesOfBookDto> {
    
    public CopiesOfBookController(TcpClient tcpClient){
        super(tcpClient);
    }
}
