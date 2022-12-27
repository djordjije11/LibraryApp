package models;

import java.io.Serializable;
import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class Book implements IEntity, Serializable {
    private Long id;
    private String title;
    private String description;
    private Author author;
    
    public Book(){
    }
    public Book(Long id){
        this.id = id;
    }
    public Book(Long id, String title, String description, Author author){
        this(id);
        this.title = title;
        this.description = description;
        this.author = author;
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

    @Override
    public String toString() {
        return "ID: " + id + ",\n" + title + " - " + author + ",\nopis: " + description;
    }
}
