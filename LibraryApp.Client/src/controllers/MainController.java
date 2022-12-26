package controllers;

import forms.MainForm;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import session.Session;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class MainController {
    private final MainForm form;
    private final TcpClient tcpClient;
    private MemberController memberController;
    private BookController bookController;
    
    public MainController(TcpClient tcpClient){
        this.tcpClient = tcpClient;
        form = new MainForm();
        setMembersMenuListener();
        setBooksMenuListener();
        setLogoutMenuListeners();
        form.setVisible(true);
    }

    private void setMembersMenuListener() {
        form.getMembersMenu().addActionListener((ActionEvent e) -> {
            try {
                memberController = new MemberController(tcpClient, form);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Greska prilikom otvaranja forme za clanove.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                if(memberController != null){
                    memberController.closeForms();
                    memberController = null;
                }
            }
        });
    }
    private void setBooksMenuListener(){
        form.getBooksMenu().addActionListener((ActionEvent e) -> {
            try {
                bookController = new BookController(tcpClient, form);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Greska prilikom otvaranja forme za knige.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                if(bookController != null){
                    bookController.closeForms();
                    bookController = null;
                }
            }
        });
    }
    private void setLogoutMenuListeners(){
        form.getLogoutAndLoginMenu().addActionListener((ActionEvent e) -> {
            Session.restartSession();
            closeMainForm();
        });
        form.getLogoutAndExitMenu().addActionListener((ActionEvent e) -> {
            Session.exitSession();
            closeMainForm();
        });
    }
    private void closeMainForm(){
        form.dispose();
        synchronized (tcpClient) {
            tcpClient.notify();
        }
    }
}
