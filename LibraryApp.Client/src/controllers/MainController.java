/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forms.MainForm;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class MainController {
    private final MainForm form;
    private final TcpClient tcpClient;
    
    public MainController(TcpClient tcpClient){
        this.tcpClient = tcpClient;
        form = new MainForm();
        setMembersMenuListener();
        form.setVisible(true);
    }

    private void setMembersMenuListener() {
        form.getMembersMenu().addActionListener((ActionEvent e) -> {
            try {
                new MemberController(tcpClient, form);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Greska prilikom otvaranja forme za clanove.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
