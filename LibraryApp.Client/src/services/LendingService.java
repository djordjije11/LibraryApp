package services;

import models.Lending;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class LendingService extends EntityService<Lending> {
    public LendingService(TcpClient tcpClient) {
        super(tcpClient);
    }
}
