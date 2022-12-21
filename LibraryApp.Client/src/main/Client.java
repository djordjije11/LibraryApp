package main;

import controllers.LoginController;
import controllers.MainController;
import java.io.IOException;
import javax.swing.JOptionPane;
import message.ModelElement;
import message.Request;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class Client {
    public static void main(String[] args) {
        try {
            TcpClient tcpClient = new TcpClient("localhost", 9001);
            /*
            new LoginController(tcpClient);
            synchronized(tcpClient){
                tcpClient.wait();
            }
            */
             new MainController(tcpClient);
             synchronized(tcpClient){
                 tcpClient.wait();
            }
            
            tcpClient.sendObject(new Request(null, ModelElement.EXIT_MESSAGE, null));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Uspostavljanje konekcije je neuspesno.", "GRESKA", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
