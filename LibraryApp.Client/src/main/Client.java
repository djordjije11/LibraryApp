package main;

import controllers.LoginController;
import controllers.MainController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class Client {
    public static void main(String[] args) {
        try {
            Client client = new Client();
            TcpClient tcpClient = new TcpClient("localhost", 9001);
            new LoginController(tcpClient, client);
            synchronized(client){
                client.wait();
            }
            new MainController(tcpClient);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Uspostavljanje konekcije je neuspesno.", "GRESKA", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
