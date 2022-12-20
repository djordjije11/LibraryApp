package controllers;

import forms.LoginForm;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import main.Client;
import message.Method;
import message.ModelElement;
import message.Request;
import message.Response;
import models.Employee;
import models.dto.LoginDto;
import session.Session;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class LoginController {
    private final TcpClient tcpClient;
    private final Client client;
    private LoginForm loginForm;
    
    public LoginController(TcpClient tcpClient, Client client){
        this.tcpClient = tcpClient;
        this.client = client;
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
            }
            try {
                if(isLoggedIn() == true){
                    loginForm.dispose();
                    loginForm = null;
                    synchronized(client){
                        client.notify();
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(loginForm, "Neuspesno prijavljivanje u sistem.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(loginForm, "Neuspesno prijavljivanje u sistem.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void sendLogin(Employee employee) throws IOException{
        Request request = new Request(employee, ModelElement.EMPLOYEE, Method.LOGIN);
        tcpClient.sendEntity(request);
    }
    private boolean isLoggedIn() throws IOException, ClassNotFoundException{
        Response response = tcpClient.readEntity();
        if(response.isConfirmed() == true){
            LoginDto loginInformation = (LoginDto) response.getObject();
            Session.setEmployee(loginInformation.getEmployee());
            Session.setBuilding(loginInformation.getBuilding());
            return true;
        } else return false;
    }
}
