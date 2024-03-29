package forms.controllers;

import services.MemberService;
import forms.MainForm;
import forms.member.MemberForm;
import forms.member.ViewMembersForm;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import models.Member;
import tcp.TcpClient;
import validations.MemberValidator;
import validations.Validator;
import validations.exceptions.ValidationException;

/**
 *
 * @author Djordjije
 */
public class MemberFormsController implements IClosable {
    private final MainForm parentForm;
    private final ViewMembersForm viewMembersForm;
    private MemberForm memberForm;
    private final Validator memberValidator;
    private final MemberService memberService;
    
    public MemberFormsController(TcpClient tcpClient, MainForm parentForm) throws Exception{
        memberService = new MemberService(tcpClient);
        this.parentForm = parentForm;
        memberValidator = new MemberValidator();
        viewMembersForm = new ViewMembersForm(parentForm, true);
        refreshViewMembersForm();
        setViewMembersFormListeners();
        viewMembersForm.setVisible(true);
    }
    @Override
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
                List<Member> members = memberService.findEntities(member);
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
            Member member = viewMembersForm.getSelectedMember();
            if(member == null){
                JOptionPane.showMessageDialog(viewMembersForm, "Nije odabran nijedan clan.", "Odaberite clana", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            memberForm = new MemberForm(parentForm, true, member);
            setMemberFormListeners();
            memberForm.setVisible(true);
        });
    }
    private void setMemberFormDeleteListener() {
        memberForm.getDeleteButton().addActionListener((ActionEvent e) -> {
            try {
                Member dbMember = memberService.deleteEntity(memberForm.getMember());
                refreshViewMembersForm();
                JOptionPane.showMessageDialog(memberForm, "Clan je uspesno obrisan.\n\n" +  dbMember.singlePrint(), "Clan obrisan", JOptionPane.INFORMATION_MESSAGE);
                memberForm.dispose();
                memberForm = null;
            } catch (Exception ex) {
                String message = "Clan nije uspesno obrisan.";
                if(ex.getMessage().startsWith("A member can't be deleted")){
                    message = "Nije dozvoljeno brisanje clana biblioteke koji nije vratio sve pozajmljene knjige.";
                }
                JOptionPane.showMessageDialog(memberForm, message, "GRESKA", JOptionPane.ERROR_MESSAGE);
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
                try{
                    memberValidator.isValid(member);
                } catch(ValidationException ex){
                    JOptionPane.showMessageDialog(memberForm, ex.getMessage(), "Clan nije sacuvan", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            try {
                Member dbMember = isMemberNew == true ? memberService.createEntity(member) : memberService.updateEntity(member);
                memberForm.setMember(dbMember);
                refreshViewMembersForm();
                JOptionPane.showMessageDialog(memberForm, "Clan je uspesno sacuvan.\n\n" + dbMember.singlePrint(), "Clan sacuvan", JOptionPane.INFORMATION_MESSAGE);
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
        viewMembersForm.setMembersTableData(memberService.readAllEntities(new Member()));
    }
}
