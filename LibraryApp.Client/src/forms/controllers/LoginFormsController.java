package forms.controllers;

import forms.LoginForm;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import message.Method;
import message.ModelElement;
import message.Request;
import message.Response;
import models.Employee;
import session.Session;
import tcp.TcpClient;
import validations.EmployeeValidator;
import validations.Validator;
import validations.exceptions.ValidationException;

/**
 *
 * @author Djordjije
 */
public class LoginFormsController implements IClosable {
    private final TcpClient tcpClient;
    private LoginForm loginForm;
    private final Validator employeeValidator;
    
    public LoginFormsController(TcpClient tcpClient){
        this.tcpClient = tcpClient;
        loginForm = new LoginForm();
        employeeValidator = new EmployeeValidator();
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
                employeeValidator.isValid(employee);
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(loginForm,  ex.getMessage(), "Pogresno uneti podaci", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                sendLogin(employee);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(loginForm, "Neuspesno poslati podaci za prijavljivanje u sistem.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                if(isLoggedIn() == true){
                    closeForms();
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

    @Override
    public void closeForms() {
        loginForm.dispose();
        loginForm = null;
        synchronized(tcpClient){
            tcpClient.notify(); //informs the main thread that the login window is closed
        }
    }
}
