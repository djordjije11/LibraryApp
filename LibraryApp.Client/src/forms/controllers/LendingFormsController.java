package forms.controllers;

import services.CopyOfBookService;
import services.LendingService;
import services.MemberService;
import forms.MainForm;
import forms.lending.LendingForm;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Book;
import models.CopyOfBook;
import models.Lending;
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
    private LendingService lendingService;
    private CopyOfBookService copyOfBookService;
    private MemberService memberService;
    
    public LendingFormsController(TcpClient tcpClient, MainForm parentForm) throws Exception {
        lendingService = new LendingService(tcpClient);
        copyOfBookService = new CopyOfBookService(tcpClient);
        memberService = new MemberService(tcpClient);
        this.parentForm = parentForm;
        lendingForm = new LendingForm(parentForm, true);
        setFindListeners();
        setApproveListener();
        lendingForm.setVisible(true);
    }
    
    private void setFindListeners(){
        setFindBookListener();
        setFindMemberListener();
    }
    private void setFindBookListener(){
        lendingForm.getFindBookButton().addActionListener((ActionEvent e) -> {
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
                    CopyOfBook dbCopyOfBook = copyOfBookService.getEntity(copyOfBook);
                    dbCopiesOfBook = new ArrayList();
                    dbCopiesOfBook.add(dbCopyOfBook);
                    lendingForm.setBooksTableData(dbCopiesOfBook);
                } else {
                    String title = lendingForm.getBookTitleTextField().getText().trim();
                    if(title != null && title.isBlank() == false){
                        Book book = new Book();
                        book.setTitle(title);
                        copyOfBook.setBook(book);
                        dbCopiesOfBook = copyOfBookService.findEntities(copyOfBook);
                        lendingForm.setBooksTableData(dbCopiesOfBook);
                    }
                }
            } catch(Exception ex){
                JOptionPane.showMessageDialog(lendingForm, "Pretraga primeraka knjiga nije uspesno izvresna.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    private void setFindMemberListener(){
        lendingForm.getFindMemberButton().addActionListener((ActionEvent e) -> {
            String firstname = lendingForm.getMemberFirstnameTextField().getText().trim();
            String lastname = lendingForm.getMemberLastnameTextField().getText().trim();
            if((firstname == null || firstname.isBlank() == true) && (lastname == null || lastname.isBlank() == true)){
                return;
            }
            Member member = new Member();
            member.setFirstname(firstname);
            member.setLastname(lastname);
            try{
                List<Member> dbMembers = memberService.findEntities(member);
                lendingForm.setUpMembers(dbMembers);
            } catch(Exception ex){
                JOptionPane.showMessageDialog(lendingForm, "Pretraga clanova nije uspesno izvresna.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    private void setApproveListener() {
        lendingForm.getApproveButton().addActionListener((ActionEvent e) -> {
            Member member = lendingForm.getSelectedMemberToLend();
            if(member == null){
                JOptionPane.showMessageDialog(lendingForm, "Nije odabran clan biblioteke koji iznajmljuje knjige,", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            List<CopyOfBook> copiesOfBook = lendingForm.getListOfSelectedCopiesOfBooksToLend();
            if(copiesOfBook == null || copiesOfBook.isEmpty()){
                JOptionPane.showMessageDialog(lendingForm, "Nije odabran nijedan primerak knjige za iznajmljivanje,", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            List<Lending> lendings = new ArrayList<>();
            LocalDate date = LocalDate.now();
            for (CopyOfBook copyOfBook : copiesOfBook) {
                lendings.add(new Lending(copyOfBook, member, date));
            }
            try {
                List<Lending> dbLendings = lendingService.createEntities(lendings);
                JOptionPane.showMessageDialog(lendingForm, "Iznajmljivanje knjiga je uspesno zabelezeno.", "Knjige su iznajmljene", JOptionPane.INFORMATION_MESSAGE);
                
                /*
                StringBuilder sb = new StringBuilder();
                for (Lending dbLending : dbLendings) {
                    sb.append(dbLending).append("\n\n");
                }
                */
                
                lendingForm.refreshForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(lendingForm, "Iznajmljivanje knjiga nije uspesno zabelezeno.", "GRESKA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    public void closeForms(){
        if(lendingForm != null){
            lendingForm.dispose();
        }
    }
}
