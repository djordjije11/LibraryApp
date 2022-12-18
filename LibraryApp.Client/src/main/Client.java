package main;

import controllers.MainController;
import java.io.IOException;
import javax.swing.JOptionPane;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class Client {
    public static void main(String[] args) {
        try {
            TcpClient tcpClient = new TcpClient("localhost", 9001);
            //login
            //poslati podatke serveru, znaci poruku LOGIN i OBJEKAT EMPLOYEE
            //ako je sve okej, dobija se LoginDto objekat ciji se podaci cuvaju u Session.setEmployee(...) i Session.setBuilding(...)
            
            new MainController(tcpClient);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Uspostavljanje konekcije je neuspesno.", "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }
}
