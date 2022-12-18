package controllers;

import forms.MainForm;
import forms.member.MemberForm;
import forms.member.ViewMembersForm;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import models.Member;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class MemberController extends EntityController<Member> {
    
    private ViewMembersForm viewMembersForm;
    private MemberForm memberForm;
    private MainForm parentForm;
    
    public MemberController(TcpClient tcpClient, MainForm parentForm) throws Exception{
        super(tcpClient);
        this.parentForm = parentForm;
        viewMembersForm = new ViewMembersForm(parentForm, true);
        refreshViewMembersForm();
        setViewMembersFormListeners();
        viewMembersForm.setVisible(true);
    }
    public void closeForms(){
        if(viewMembersForm != null)
            viewMembersForm.dispose();
        if(memberForm != null)
            memberForm.dispose();
    }
    private void setViewMembersFormFindListener() {
        viewMembersForm.getFindButton().addActionListener((ActionEvent e) -> {
            Member member = new Member();
            member.setFirstname(viewMembersForm.getFirstNameTextField().getText().trim());
            member.setLastname(viewMembersForm.getLastNameTextField().getText().trim());
            try {
                List<Member> members = findEntities(member);
                viewMembersForm.setMembersTableData(members);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewMembersForm, "Pretraga clanova nije uspesno izvrsena.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void setViewMembersFormCreateListener() {
        viewMembersForm.getCreateButton().addActionListener((ActionEvent e) -> {
            memberForm = new MemberForm(parentForm, true, null);
            setMemberFormListeners();
            memberForm.setVisible(true);
        });
    }
    private void setViewMembersFormOpenMemberFormListener(){
        viewMembersForm.getOpenMemberFormButton().addActionListener((ActionEvent e) -> {
            memberForm = new MemberForm(parentForm, true, viewMembersForm.getSelectedMember());
            setMemberFormListeners();
            memberForm.setVisible(true);
        });
    }
    private void setMemberFormDeleteListener() {
        memberForm.getDeleteButton().addActionListener((ActionEvent e) -> {
            try {
                Member member = deleteEntity(memberForm.getMember());
                refreshViewMembersForm();
                JOptionPane.showMessageDialog(memberForm, "Clan " + member + " je uspesno obrisan.", "Clan obrisan", JOptionPane.INFORMATION_MESSAGE);
                memberForm.dispose();
                memberForm = null;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(memberForm, "Clan nije uspesno obrisan.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void setMemberFormSaveListener() {
        memberForm.getSaveButton().addActionListener((ActionEvent e) -> {
            Member member = memberForm.getMember();
            boolean isMemberNew = false;
            boolean hasChanges = false;
            if(member == null) {
                member = new Member();
                isMemberNew = true;
                hasChanges = true;
            }
            String firstname = memberForm.getFirstNameTextField().getText().trim();
            String lastname = memberForm.getLastNameTextField().getText().trim();
            LocalDate birthday = memberForm.getBirthday();
            String email = memberForm.getEmailTextField().getText().trim();
            if(hasChanges == false && (member.getFirstname().equals(firstname) == false || member.getLastname().equals(lastname) == false
                    || member.getBirthday().isEqual(birthday) == false || member.getEmail().equals(email) == false)){
                hasChanges = true;
            }
                if(hasChanges == false){
                    JOptionPane.showMessageDialog(memberForm, "Podaci o clanu nisu promenjeni.", "Podaci nisu promenjeni", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                member.setFirstname(firstname);
                member.setLastname(lastname);
                member.setBirthday(birthday);
                member.setEmail(email);
                //VALIDIRATI PODATKE
            try {
                Member dbMember = isMemberNew == true ? createEntity(member) : updateEntity(member);
                memberForm.setMember(dbMember);
                refreshViewMembersForm();
                JOptionPane.showMessageDialog(memberForm, "Clan " + dbMember + " je uspesno sacuvan.", "Clan sacuvan", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(memberForm, "Clan nije uspesno sacuvan.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void setViewMembersFormListeners(){
        setViewMembersFormFindListener();
        setViewMembersFormCreateListener();
        setViewMembersFormOpenMemberFormListener();
    }
    private void setMemberFormListeners(){
        setMemberFormDeleteListener();
        setMemberFormSaveListener();
    }
    private void refreshViewMembersForm() throws Exception{
        if(viewMembersForm == null)
            return;
        viewMembersForm.emptyFirstNameTextField();
        viewMembersForm.emptyLastNameTextField();
        viewMembersForm.setMembersTableData(readAllEntities(new Member()));
    }
}
