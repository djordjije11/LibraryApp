package models.dto;

import java.io.Serializable;
import java.util.List;
import message.ModelElement;
import models.Author;
import models.Book;
import models.CopyOfBook;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public class CopiesOfBookDto implements IEntity, Serializable {
    private Long id;
    private String title;
    private String description;
    private Author author;
    private Long copyOfBookId;
    private List<CopyOfBook> copiesOfBook;
    private Long buildingId;
    
    public CopiesOfBookDto() {
    }
    public CopiesOfBookDto(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.author = book.getAuthor();
    }
    public CopiesOfBookDto(Book book, Long buildingID){
        this(book);
        this.buildingId = buildingID;
    }
    public CopiesOfBookDto(Book book, Long buildingID, List<CopyOfBook> copiesOfBook){
        this(book, buildingID);
        this.copiesOfBook = copiesOfBook;
    }
    
    public Book getBookFromCopiesOfBookDto(){
        return new Book(id, title, description, author);
    }
    public CopyOfBook getCopyOfBookFromCopiesOfBookDto(){
        Book book = getBookFromCopiesOfBookDto();
        return new CopyOfBook(copyOfBookId, book);
    }
    @Override
    public ModelElement getModelElement() {
        return ModelElement.COPYOFBOOK;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public Long getCopyOfBookId(){
        return copyOfBookId;
    }
    public void setCopyOfBookId(Long copyOfBookId){
        this.copyOfBookId = copyOfBookId;
    }
    public Long getBuildingId() {
        return buildingId;
    }
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
    public List<CopyOfBook> getCopiesOfBook(){
        return copiesOfBook;
    }
    public int getNumberOfCopiesOfBook(){
        if(copiesOfBook == null || copiesOfBook.isEmpty())
            return 0;
        return copiesOfBook.size();
    }
}
