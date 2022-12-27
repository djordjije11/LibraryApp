package controllers;

import models.Lending;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class LendingController extends EntityController<Lending> {
    public LendingController(TcpClient tcpClient) {
        super(tcpClient);
    }
}
