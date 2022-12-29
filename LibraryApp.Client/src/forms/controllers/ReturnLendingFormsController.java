package forms.controllers;

import controllers.LendingController;
import controllers.MemberController;
import forms.MainForm;
import forms.lending.ReturnLendingForm;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import models.Lending;
import models.Member;
import session.Session;
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
        setApproveListener();
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
            if(member == null){
                return;
            }
            returnLendingForm.getSelectedMemberTextField().setText(member.toString());
            Lending lending = new Lending();
            lending.setMember((Member) member);
            try {
                List<Lending> dbLendings = lendingController.findEntities(lending);
                returnLendingForm.setLendingsTableData(dbLendings);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(returnLendingForm, "Pretraga iznajmljivanja knjiga trazenog clana nije uspesno izvresna.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
    private void setApproveListener() {
        returnLendingForm.getApproveButton().addActionListener((ActionEvent e) -> {
            Member member = returnLendingForm.getSelectedMemberToReturnLendings();
            List<Lending> lendings = returnLendingForm.getListOfSelectedLendingsToReturn();
            if(member == null || lendings == null || lendings.isEmpty()){
                JOptionPane.showMessageDialog(returnLendingForm, "Nisu uneti potrebni podaci,", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            LocalDate date = LocalDate.now();
            Long buildingID = Session.getBuilding().getId();
            for (Lending lending : lendings) {
                lending.setReturnDate(date);
                lending.getCopyOfBook().setBuildingId(buildingID);
            }
            try{
                lendingController.updateEntities(lendings);
                JOptionPane.showMessageDialog(returnLendingForm, "Vracanje iznajmljenih knjiga je uspesno zabelezeno.", "Knjige su vracene", JOptionPane.INFORMATION_MESSAGE);
                returnLendingForm.refreshForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(returnLendingForm, "Vracanje iznajmljenih knjiga nije uspesno zabelezeno.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    public void closeForms(){
        if(returnLendingForm != null){
            returnLendingForm.dispose();
        }
    }
}
