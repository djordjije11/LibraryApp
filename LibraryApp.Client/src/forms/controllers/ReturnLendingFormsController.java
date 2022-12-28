package forms.controllers;

import controllers.LendingController;
import controllers.MemberController;
import forms.MainForm;
import forms.lending.ReturnLendingForm;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Lending;
import models.Member;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class ReturnLendingFormsController {
    private MainForm parentForm;
    private ReturnLendingForm returnLendingForm;
    private LendingController lendingController;
    private MemberController memberController;
    
    public ReturnLendingFormsController(TcpClient tcpClient, MainForm parentForm) throws Exception {
        lendingController = new LendingController(tcpClient);
        memberController = new MemberController(tcpClient);
        this.parentForm = parentForm;
        returnLendingForm = new ReturnLendingForm(parentForm, true);
        setFindMemberListener();
        setFindLendingsForSelectedMemberListener();
        
        returnLendingForm.setVisible(true);
    }
    
    private void setFindMemberListener(){
        returnLendingForm.getFindMemberButton().addActionListener((ActionEvent e) -> {
            String firstname = returnLendingForm.getMemberFirstnameTextField().getText().trim();
            String lastname = returnLendingForm.getMemberLastnameTextField().getText().trim();
            if((firstname == null || firstname.isBlank() == true) && (lastname == null || lastname.isBlank() == true)){
                return;
            }
            Member member = new Member();
            member.setFirstname(firstname);
            member.setLastname(lastname);
            try{
                List<Member> dbMembers = memberController.findEntities(member);
                returnLendingForm.setUpMembers(dbMembers);
            } catch(Exception ex){
                JOptionPane.showMessageDialog(returnLendingForm, "Pretraga clanova nije uspesno izvresna.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
    private void setFindLendingsForSelectedMemberListener(){
        returnLendingForm.getMembersComboBox().addActionListener((ActionEvent e) -> {
           Object member = returnLendingForm.getMembersComboBox().getSelectedItem();
            if(member != null){
                returnLendingForm.getSelectedMemberTextField().setText(member.toString());
            } 
            Lending lending = new Lending();
            lending.setMember((Member) member);
            try {
                List<Lending> dbLendings = lendingController.findEntities(lending);
                returnLendingForm.setLendingsTableData(dbLendings);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(returnLendingForm, "Pretraga iznajmljivanja knjiga trazenog clana nije uspesno izvresna.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
    public void closeForms(){
        
    }
}
