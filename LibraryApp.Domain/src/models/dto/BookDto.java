package models.dto;

import java.io.Serializable;
import message.ModelElement;
import models.Author;
import models.Book;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public class BookDto implements IEntity, Serializable {
    private Long id;
    private String title;
    private String description;
    private Author author;
    private Long currentAmount;
    private Long addingAmount;
    private boolean isUpdated = false;
    private Long buildingId;

    public BookDto() {
    }
    public BookDto(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.author = book.getAuthor();
    }
    public BookDto(Book book, Long buildingID){
        this(book);
        this.buildingId = buildingID;
    }
    public BookDto(Book book, Long buildingID, Long currentAmount){
        this(book, buildingID);
        this.currentAmount = currentAmount;
    }
    
    public Book getBookFromBookDto(){
        return new Book(id, title, description, author);
    }
    @Override
    public ModelElement getModelElement() {
        return ModelElement.BOOK;
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
    public Long getCurrentAmount() {
        return currentAmount;
    }
    public void setCurrentAmount(Long currentAmount) {
        this.currentAmount = currentAmount;
    }
    public Long getAddingAmount() {
        return addingAmount;
    }
    public void setAddingAmount(Long addingAmount) {
        this.addingAmount = addingAmount;
    }
    public boolean getIsUpdated() {
        return isUpdated;
    }
    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }
    public Long getBuildingId() {
        return buildingId;
    }
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
    public String singlePrint(){
        return "ID: " + id + ",\n" + title + (author == null ? "" : " - " +  author) + "\nNa stanju: " + (currentAmount == null ? 0 : currentAmount);
    }
}
