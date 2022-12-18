package models;

import java.io.Serializable;

/**
 *
 * @author Djordjije
 */
public class Book implements IEntity, Serializable {
    private Long id;
    private String title;
    private String description;
    private Author author;
    
    @Override
    public String getClassName() {
        return "Book";
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
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
}
