package main;

import controllers.LoginController;
import controllers.MainController;
import java.io.IOException;
import javax.swing.JOptionPane;
import message.ModelElement;
import message.Request;
import session.Session;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class Client {
    private final static String IP_ADDRESS = "localhost";
    private final static int PORT_NUMBER = 9001;
    
    public static void main(String[] args) {
        try {
            TcpClient tcpClient = new TcpClient(IP_ADDRESS, PORT_NUMBER);
            while(Session.doesClientWantToLogin() == true){
                new LoginController(tcpClient);
                synchronized(tcpClient){
                    tcpClient.wait();
                }
                new MainController(tcpClient);
                synchronized(tcpClient){
                    tcpClient.wait();
                }
            }
            tcpClient.sendObject(new Request(null, ModelElement.EXIT_MESSAGE, null));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Uspostavljanje konekcije je neuspesno.", "GRESKA", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
