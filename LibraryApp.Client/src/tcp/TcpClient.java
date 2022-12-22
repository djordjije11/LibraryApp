package tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JOptionPane;
import session.Session;

/**
 *
 * @author Djordjije
 */
public class TcpClient extends TcpCommunicator {
    
    public TcpClient(String IPAddress, int portNumber) throws IOException {
        super(new Socket(IPAddress, portNumber));
    }
    
    @Override
    public String readMessage() throws IOException{
        try{
            return input.readLine();
        } catch(SocketException ex) {
            JOptionPane.showMessageDialog(null, "Konekcija sa serverom je prekinuta.", "Prekinuta konekcija", JOptionPane.ERROR_MESSAGE);
            //Session.closeAllForms();
            System.exit(0);
            throw ex;
        }
    }
}