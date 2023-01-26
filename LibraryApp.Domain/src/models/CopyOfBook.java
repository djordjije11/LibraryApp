package models;

import java.io.Serializable;
import java.util.Objects;
import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class CopyOfBook implements IEntity, Serializable {
    private Long id;
    private Book book;
    private Long buildingId;
    
    public CopyOfBook(){}
    public CopyOfBook(Long id) {
        this.id = id;
    }
    public CopyOfBook(Long id, Long buildingId){
        this(id);
        this.buildingId = buildingId;
    }
    public CopyOfBook(Long id, Book book){
        this(id);
        this.book = book;
    }
    public CopyOfBook(Book book){
        this.book = book;
    }
    public CopyOfBook(Book book, Long buildingId){
        this.book = book;
        this.buildingId = buildingId;
    }
    public CopyOfBook(Long id, Book book, Long buildingId){
        this(id, buildingId);
        this.book = book;
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
    public Long getId(){
        return id;
    }
    public Book getBook(){
        return book;
    }
    public void setBook(Book book){
        this.book = book;
    }
    public Long getBuildingId() {
        return buildingId;
    }
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", " + book;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CopyOfBook copyOfBook){
            return Objects.equals(this.id, copyOfBook.getId());
        } else return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
