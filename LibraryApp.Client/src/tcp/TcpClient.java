package tcp;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Djordjije
 */
public class TcpClient extends TcpCommunicator {
    
    public TcpClient(String IPAddress, int portNumber) throws IOException {
        super(new Socket(IPAddress, portNumber));
    }
}