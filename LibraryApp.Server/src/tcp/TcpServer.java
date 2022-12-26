package tcp;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Djordjije
 */
public class TcpServer extends TcpCommunicator {
    public TcpServer(Socket socketCommunication) throws IOException{
        super(socketCommunication);
    }
}
