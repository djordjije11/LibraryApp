package forms.controllers;

import controllers.CopyOfBookController;
import controllers.MemberController;
import forms.MainForm;
import forms.lending.LendingForm;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Book;
import models.CopyOfBook;
import models.Member;
import session.Session;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class LendingFormsController {
    private MainForm parentForm;
    private LendingForm lendingForm;
    private CopyOfBookController copyOfBookController;
    private MemberController memberController;
    
    public LendingFormsController(TcpClient tcpClient, MainForm parentForm) throws Exception {
        copyOfBookController = new CopyOfBookController(tcpClient);
        memberController = new MemberController(tcpClient);
        this.parentForm = parentForm;
        lendingForm = new LendingForm(parentForm, true);
        setFindListener();
        
        lendingForm.setVisible(true);
    }
    public void setFindListener(){
        lendingForm.getFindButton().addActionListener((ActionEvent e) -> {
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
                List<CopyOfBook> dbCopiesOfBook;
                CopyOfBook copyOfBook = new CopyOfBook();
                copyOfBook.setBuildingId(Session.getBuilding().getId());
                if(oneCopy == true){
                    copyOfBook.setId(copyOfBookID);
                    CopyOfBook dbCopyOfBook = copyOfBookController.getEntity(copyOfBook);
                    dbCopiesOfBook = new ArrayList();
                    dbCopiesOfBook.add(dbCopyOfBook);
                    lendingForm.setBooksTableData(dbCopiesOfBook);
                } else {
                    Book book = new Book();
                    String title = lendingForm.getBookTitleTextField().getText().trim();
                    if(title != null && title.isBlank() == false){
                        book.setTitle(title);
                        copyOfBook.setBook(book);
                        dbCopiesOfBook = copyOfBookController.findEntities(copyOfBook);
                        lendingForm.setBooksTableData(dbCopiesOfBook);
                    }
                }
            } catch(Exception ex){
                JOptionPane.showMessageDialog(lendingForm, "Pretraga primeraka knjiga nije uspesno izvresna.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
            String firstname = lendingForm.getMemberFirstnameTextField().getText().trim();
            String lastname = lendingForm.getMemberLastnameTextField().getText().trim();
            if((firstname == null || firstname.isBlank() == true) && (lastname == null || lastname.isBlank() == true)){
                return;
            }
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
