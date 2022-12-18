/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controllers.MainController;
import forms.MainForm;
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
            //login
            TcpClient tcpClient = new TcpClient("localhost", 9001);
            new MainController(tcpClient);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Uspostavljanje konekcije je neuspesno.", "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }
}
