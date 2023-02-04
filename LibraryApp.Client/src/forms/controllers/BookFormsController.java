package forms.controllers;

import services.AuthorService;
import services.BookService;
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
import validations.BookDtoValidator;
import validations.Validator;
import validations.exceptions.ValidationException;

/**
 *
 * @author Djordjije
 */
public class BookFormsController implements IClosable {
    private MainForm parentForm;
    private ViewBooksForm viewBooksForm;
    private BookForm bookForm;
    private Validator bookDtoValidator;
    private BookService bookService;
    private AuthorService authorService;
    
    public BookFormsController(TcpClient tcpClient, MainForm parentForm) throws Exception {
        bookService = new BookService(tcpClient);
        authorService = new AuthorService(tcpClient);
        this.parentForm = parentForm;
        bookDtoValidator = new BookDtoValidator();
        viewBooksForm = new ViewBooksForm(parentForm, true);
        refreshViewBooksForm();
        setViewBooksFormListeners();
        viewBooksForm.setVisible(true);
    }

    @Override
    public void closeForms(){
        if(viewBooksForm != null)
            viewBooksForm.dispose();
        if(bookForm != null)
            bookForm.dispose();
    }
    private void setViewBooksFormFindListener() {
        viewBooksForm.getFindButton().addActionListener((ActionEvent e) -> {
            BookDto bookDto = new BookDto();
            bookDto.setTitle(viewBooksForm.getTitleTextField().getText().trim());
            try {
                List<BookDto> books = bookService.findEntities(bookDto);
                viewBooksForm.setBooksTableData(books);
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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookForm, "Autori nisu uspesno ucitani!.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
            setBookFormListeners();
            bookForm.setVisible(true);
        });
    }
    private void setViewBooksFormOpenBookFormListener() throws Exception{
        viewBooksForm.getOpenBookFormButton().addActionListener((ActionEvent e) -> {
            BookDto bookDto = viewBooksForm.getSelectedBook();
            if(bookDto == null){
                JOptionPane.showMessageDialog(bookForm, "Nije odabrana nijedna knjiga.", "Odaberite knjigu", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            bookDto.setBuildingId(Session.getBuilding().getId());
            BookDto dbBookDto;
            try {
                dbBookDto = bookService.getEntity(bookDto);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookForm, "Knjiga nije uspesno ucitana.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
            bookForm = new BookForm(parentForm, true, dbBookDto);
            try {
                setUpAuthorsData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookForm, "Autori nisu uspesno ucitani!.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
            setBookFormListeners();
            bookForm.setVisible(true);
        });
    }
    private void setBookFormDeleteListener() {
        bookForm.getDeleteButton().addActionListener((ActionEvent e) -> {
            try {
                BookDto bookDto = bookForm.getBook();
                if(bookDto.getCurrentAmount() > 0){
                    JOptionPane.showMessageDialog(bookForm, "Nije dozvoljeno brisanje knjiga ciji primerci su cuvani u bazi.", "Nedozvoljena operacija", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                BookDto dbBookDto = bookService.deleteEntity(bookDto);
                refreshViewBooksForm();
                JOptionPane.showMessageDialog(bookForm, "Knjiga je uspesno obrisana.\n\n" + dbBookDto.singlePrint(), "Knjiga obrisana", JOptionPane.INFORMATION_MESSAGE);
                bookForm.dispose();
                bookForm = null;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookForm, "Knjiga nije uspesno obrisana.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void setBookFormSaveListener() {
        bookForm.getSaveButton().addActionListener((ActionEvent e) -> {
            BookDto bookDto = bookForm.getBook();
            boolean isBookNew = false;
            boolean hasChanges = false;
            if(bookDto == null) {
                bookDto = new BookDto();
                isBookNew = true;
                hasChanges = true;
            }
            String title = bookForm.getTitleTextField().getText().trim();
            String description = bookForm.getDescriptionTextArea().getText().trim();
            Author author = bookForm.getSelectedAuthor();
            Long addingAmount;
            try{
                addingAmount = Long.parseLong(bookForm.getAddingAmount().getText());
            } catch(NumberFormatException ex){            
                JOptionPane.showMessageDialog(bookForm, "Za broj dodatih primeraka knjige mora biti unet poztivan celi broj.", "Pogresno uneti podaci.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(addingAmount < 0){
                JOptionPane.showMessageDialog(bookForm, "Za broj dodatih primeraka knjige mora biti unet poztivan celi broj.", "Pogresno uneti podaci.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(hasChanges == false && (bookDto.getTitle().equals(title) == false || bookDto.getDescription().equals(description) == false
                    || bookDto.getAuthor().equals(author) == false)){
                hasChanges = true;
                bookDto.setIsUpdated(true);
            }
            if(hasChanges == false && addingAmount > 0){
                hasChanges = true;
            }
            if(hasChanges == false){
                    JOptionPane.showMessageDialog(bookForm, "Podaci o knjizi nisu promenjeni.", "Podaci nisu promenjeni", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
            bookDto.setTitle(title);
            bookDto.setDescription(description);
            bookDto.setAuthor(author);
            bookDto.setAddingAmount(addingAmount);
            bookDto.setBuildingId(Session.getBuilding().getId());
            try {
                bookDtoValidator.isValid(bookDto);
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(bookForm, ex.getMessage(), "Knjiga nije sacuvana", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                BookDto dbBook = isBookNew == true ? bookService.createEntity(bookDto) : bookService.updateEntity(bookDto);
                bookForm.setBook(dbBook);
                refreshViewBooksForm();
                JOptionPane.showMessageDialog(bookForm, "Knjiga je uspesno sacuvana.\n\n" + dbBook.singlePrint(), "Knjiga sacuvana", JOptionPane.INFORMATION_MESSAGE);
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
        if(viewBooksForm != null){
            viewBooksForm.emptyTitleTextField();
            viewBooksForm.setBooksTableData(bookService.readAllEntities(new BookDto()));
        }
    }
    private void setUpAuthorsData() throws Exception{
        if(bookForm != null){
            bookForm.setUpAuthors(authorService.readAllEntities(new Author()));
        }
    }
}
