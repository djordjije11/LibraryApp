package forms.controllers;

import controllers.CopiesOfBookController;
import controllers.MemberController;
import forms.MainForm;
import forms.lending.LendingForm;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.CopyOfBook;
import models.Member;
import models.dto.CopiesOfBookDto;
import session.Session;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class LendingFormsController {
    private MainForm parentForm;
    private LendingForm lendingForm;
    private CopiesOfBookController copiesOfBookController;
    private MemberController memberController;
    
    public LendingFormsController(TcpClient tcpClient, MainForm parentForm) throws Exception {
        copiesOfBookController = new CopiesOfBookController(tcpClient);
        memberController = new MemberController(tcpClient);
        this.parentForm = parentForm;
        lendingForm = new LendingForm(parentForm, true);
        setFindListener();
        
        lendingForm.setVisible(true);
    }
    public void setFindListener(){
        lendingForm.getFindButton().addActionListener((ActionEvent e) -> {
            String title = lendingForm.getBookTitleTextField().getText().trim();
            CopiesOfBookDto copiesOfBookDto = new CopiesOfBookDto();
            copiesOfBookDto.setBuildingId(Session.getBuilding().getId());
            boolean oneCopy;
            Long copyOfBookID = null;
            try {
                copyOfBookID = Long.parseLong(lendingForm.getCopyOfBookIDTextField().getText().trim());
                oneCopy = true;
            } catch(NumberFormatException ex){            
                oneCopy = false;
                //JOptionPane.showMessageDialog(lendingForm, "Pogresno uneti podaci.", "ID primerka knjige mora biti poztivan celi broj.", JOptionPane.WARNING_MESSAGE);
            }
            try{
                List<CopyOfBook> dbCopiesOfBook = new ArrayList<>();
                if(oneCopy == true){
                    copiesOfBookDto.setCopyOfBookId(copyOfBookID);
                    CopiesOfBookDto dbCopiesOfBookDto = copiesOfBookController.getEntity(copiesOfBookDto);
                    CopyOfBook dbCopyOfBook = dbCopiesOfBookDto.getCopyOfBookFromCopiesOfBookDto();
                    dbCopiesOfBook.add(dbCopyOfBook);
                    lendingForm.setBooksTableData(dbCopiesOfBook);
                } else {
                    copiesOfBookDto.setTitle(title);
                    List<CopiesOfBookDto> dbListOfCopiesOfBookDto = copiesOfBookController.findEntities(copiesOfBookDto);
                    for (CopiesOfBookDto dbCopiesOfBookDto : dbListOfCopiesOfBookDto) {
                        dbCopiesOfBook.addAll(dbCopiesOfBookDto.getCopiesOfBook());
                    }
                    lendingForm.setBooksTableData(dbCopiesOfBook);
                }
            } catch(Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(lendingForm, "Pretraga primeraka knjiga nije uspesno izvresna.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
            String firstname = lendingForm.getMemberFirstnameTextField().getText().trim();
            String lastname = lendingForm.getMemberLastnameTextField().getText().trim();
            Member member = new Member();
            member.setFirstname(firstname);
            member.setLastname(lastname);
            try{
                List<Member> dbMembers = memberController.findEntities(member);
                lendingForm.setUpMembers(dbMembers);
            } catch(Exception ex){
                JOptionPane.showMessageDialog(lendingForm, "Pretraga clanova nije uspesno izvresna.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    public void closeForms(){
        
    }
    
    
    
}
