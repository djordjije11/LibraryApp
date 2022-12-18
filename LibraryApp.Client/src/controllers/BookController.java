package controllers;

import forms.MainForm;
import forms.book.BookForm;
import forms.book.ViewBooksForm;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import models.Author;
import models.dto.BookDto;
import session.Session;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class BookController extends EntityController<BookDto> {
    private ViewBooksForm viewBooksForm;
    private BookForm bookForm;
    private MainForm parentForm;
    private AuthorController authorController;
    private boolean areAuthorsSetUp = false;
    private boolean isBookForBookFormSetUp = false;
    
    public BookController(TcpClient tcpClient, MainForm parentForm) throws Exception {
        super(tcpClient);
        this.parentForm = parentForm;
        viewBooksForm = new ViewBooksForm(parentForm, true);
        refreshViewBooksForm();
        setViewBooksFormListeners();
        viewBooksForm.setVisible(true);
    }
    
    public void closeForms(){
        if(viewBooksForm != null)
            viewBooksForm.dispose();
        if(bookForm != null)
            bookForm.dispose();
    }
    
    private void setViewBooksFormFindListener() {
        viewBooksForm.getFindButton().addActionListener((ActionEvent e) -> {
            BookDto book = new BookDto();
            book.setTitle(viewBooksForm.getTitleTextField().getText().trim());
            try {
                List<BookDto> books = findEntities(book);
                viewBooksForm.setBooksTableData(books); //need to implement,,,
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewBooksForm, "Pretraga knjiga nije uspesno izvrsena.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void setViewBooksFormCreateListener() throws Exception {
        viewBooksForm.getCreateButton().addActionListener((ActionEvent e) -> {
            bookForm = new BookForm(parentForm, true, null);
            try {
                setUpAuthorsData();
                areAuthorsSetUp = true;
            } catch (Exception ex) {
                areAuthorsSetUp = false;
                return;
            }
            setBookFormListeners();
            bookForm.setVisible(true);
        });
        if(areAuthorsSetUp == false) throw new Exception("Authors are not set up!");
    }

    private void setViewBooksFormOpenBookFormListener() throws Exception{
        viewBooksForm.getOpenBookFormButton().addActionListener((ActionEvent e) -> {
            BookDto book = viewBooksForm.getSelectedBook();
            BookDto dbBook;
            try {
                dbBook = getEntity(book);
            } catch (Exception ex) {
                isBookForBookFormSetUp = false;
                return;
            }
            bookForm = new BookForm(parentForm, true, dbBook);
            isBookForBookFormSetUp = true;
            try {
                setUpAuthorsData();
                areAuthorsSetUp = true;
            } catch (Exception ex) {
                areAuthorsSetUp = false;
                return;
            }
            setBookFormListeners();
            bookForm.setVisible(true);
        });
        if(isBookForBookFormSetUp == false) throw new Exception("The book is not set up!");
        if(areAuthorsSetUp == false) throw new Exception("Authors are not set up!");
    }
    
    private void setBookFormDeleteListener() {
        bookForm.getDeleteButton().addActionListener((ActionEvent e) -> {
            try {
                BookDto book = bookForm.getBook();
                if(book.getCurrentAmount() > 0){
                    JOptionPane.showMessageDialog(bookForm, "Nije dozvoljeno brisanje knjiga ciji primerci su cuvani u bazi.", "Nedozvoljena operacija", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                BookDto dbBook = deleteEntity(book);
                refreshViewBooksForm();
                JOptionPane.showMessageDialog(bookForm, "Knjiga " + dbBook + " je uspesno obrisana.", "Knjiga obrisana", JOptionPane.INFORMATION_MESSAGE);
                bookForm.dispose();
                bookForm = null;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookForm, "Knjiga nije uspesno obrisana.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void setBookFormSaveListener() {
        bookForm.getSaveButton().addActionListener((ActionEvent e) -> {
            BookDto book = bookForm.getBook();
            boolean isBookNew = false;
            boolean hasChanges = false;
            if(book == null) {
                book = new BookDto();
                isBookNew = true;
                hasChanges = true;
                book.setIsUpdated(true);
            }
            String title = bookForm.getTitleTextField().getText().trim();
            String description = bookForm.getDescriptionTextArea().getText().trim();
            Author author = bookForm.getSelectedAuthor();
            Long addingAmount;
            try{
                addingAmount = Long.parseLong(bookForm.getAddingAmount().getText());
            } catch(NumberFormatException ex){            
                JOptionPane.showMessageDialog(bookForm, "Pogresno uneti podaci.", "Za broj dodatih primeraka knjige mora biti unet poztivan celi broj.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(addingAmount < 0){
                JOptionPane.showMessageDialog(bookForm, "Pogresno uneti podaci.", "Za broj dodatih primeraka knjige mora biti unet poztivan celi broj.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(hasChanges == false && (book.getTitle().equals(title) == false || book.getDescription().equals(description) == false
                    || book.getAuthor().equals(author) == false)){
                hasChanges = true;
                book.setIsUpdated(true);
            }
            if(hasChanges == false && addingAmount > 0){
                hasChanges = true;
            }
                if(hasChanges == false){
                    JOptionPane.showMessageDialog(bookForm, "Podaci o clanu nisu promenjeni.", "Podaci nisu promenjeni", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                book.setTitle(title);
                book.setDescription(description);
                book.setAuthor(author);
                book.setAddingAmount(addingAmount);
                book.setBuildingId(Session.getBuilding().getId());
                //VALIDIRATI PODATKE
            try {
                BookDto dbBook = isBookNew == true ? createEntity(book) : updateEntity(book);
                bookForm.setBook(dbBook);
                refreshViewBooksForm();
                JOptionPane.showMessageDialog(bookForm, "Knjiga " + dbBook + " je uspesno sacuvana.", "Knjiga sacuvan", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookForm, "Knjiga nije uspesno sacuvana.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void setViewBooksFormListeners() throws Exception{
        setViewBooksFormFindListener();
        setViewBooksFormCreateListener();
        setViewBooksFormOpenBookFormListener();
    }
    private void setBookFormListeners(){
        setBookFormDeleteListener();
        setBookFormSaveListener();
    }
    private void refreshViewBooksForm() throws Exception{
        if(viewBooksForm == null)
            return;
        viewBooksForm.emptyTitleTextField();
        viewBooksForm.setBooksTableData(readAllEntities(new BookDto()));
    }
    private void setUpAuthorsData() throws Exception{
        if(bookForm == null)
            return;
        if(authorController == null)
            authorController = new AuthorController(tcpClient);
        bookForm.setUpAuthors(authorController.readAllEntities(new Author()));
    }
}
