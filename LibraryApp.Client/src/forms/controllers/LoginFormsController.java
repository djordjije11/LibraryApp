package forms.controllers;

import forms.LoginForm;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import message.Method;
import message.ModelElement;
import message.Request;
import message.Response;
import models.Employee;
import session.Session;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class LoginFormsController {
    private final TcpClient tcpClient;
    private LoginForm loginForm;
    
    public LoginFormsController(TcpClient tcpClient){
        this.tcpClient = tcpClient;
        loginForm = new LoginForm();
        setLoginListener();
        loginForm.setVisible(true);
    }
    
    private void setLoginListener(){
        loginForm.getLoginButton().addActionListener((ActionEvent e) -> {
            Long employeeID;
            try{
                employeeID = loginForm.getEmployeeID();
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(loginForm, "ID zaposlenog je ceo pozitivan broj.", "Pogresno uneti podaci", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String password = loginForm.getPassword();
            Employee employee = new Employee(employeeID, password);
            try {
                sendLogin(employee);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(loginForm, "Neuspesno poslati podaci za prijavljivanje u sistem.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if(isLoggedIn() == true){
                    loginForm.dispose();
                    loginForm = null;
                    synchronized(tcpClient){
                        tcpClient.notify();
                    }
                } else{
                    JOptionPane.showMessageDialog(loginForm, "Neuspesno prijavljivanje u sistem.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(loginForm, "Neuspesno prijavljivanje u sistem.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void sendLogin(Employee employee) throws IOException{
        Request request = new Request(employee, ModelElement.EMPLOYEE, Method.LOGIN);
        tcpClient.sendObject(request);
    }
    private boolean isLoggedIn() throws IOException, ClassNotFoundException{
        Response response = tcpClient.<Response>readObject();
        if(response.isConfirmed() == true){
            Employee employee = (Employee) response.getObject();
            Session.setEmployee(employee);
            Session.setBuilding(employee.getBuilding());
            return true;
        } else return false;
    }
}
