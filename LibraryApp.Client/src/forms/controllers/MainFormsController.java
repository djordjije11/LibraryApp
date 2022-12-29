package forms.controllers;

import forms.MainForm;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import session.Session;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class MainFormsController {
    private final MainForm form;
    private final TcpClient tcpClient;
    private MemberFormsController memberController;
    private BookFormsController bookController;
    private LendingFormsController lendingController;
    private ReturnLendingFormsController returnLendingController;
    
    public MainFormsController(TcpClient tcpClient) throws IOException{
        this.tcpClient = tcpClient;
        form = new MainForm();
        setUpMainFormData();
        setMembersMenuListener();
        setBooksMenuListener();
        setLendingsMenuListener();
        setReturnLendingsMenuListener();
        setLogoutMenuListeners();
        form.setVisible(true);
    }
    
    private void setUpMainFormData(){
        form.setBuildingText(Session.getBuilding().toString());
        form.setEmployeeText(Session.getEmployee().toString());
    }
    private void setMembersMenuListener() {
        form.getMembersMenu().addActionListener((ActionEvent e) -> {
            try {
                memberController = new MemberFormsController(tcpClient, form);
            } catch (Exception ex) {
                ex.printStackTrace();
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
                bookController = new BookFormsController(tcpClient, form);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Greska prilikom otvaranja forme za knige.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                if(bookController != null){
                    bookController.closeForms();
                    bookController = null;
                }
            }
        });
    }
    private void setLendingsMenuListener(){
        form.getLendingsMenu().addActionListener((ActionEvent e) -> {
           try{
               lendingController = new LendingFormsController(tcpClient, form);
           } catch(Exception ex){
               JOptionPane.showMessageDialog(form, "Greska prilikom otvaranja forme za iznajmljivanje knjiga.", "GRESKA", JOptionPane.ERROR_MESSAGE);
               if(lendingController != null){
                   lendingController.closeForms();
                   lendingController = null;
               }
           }
        });
    }
    private void setReturnLendingsMenuListener(){
        form.getReturnLendingsMenu().addActionListener((ActionEvent e) -> {
            try{
               returnLendingController = new ReturnLendingFormsController(tcpClient, form);
           } catch(Exception ex){
               JOptionPane.showMessageDialog(form, "Greska prilikom otvaranja forme za vracanje iznajmljenih knjiga.", "GRESKA", JOptionPane.ERROR_MESSAGE);
               if(returnLendingController != null){
                   returnLendingController.closeForms();
                   returnLendingController = null;
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
