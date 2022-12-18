package models.dto;

import java.io.Serializable;
import models.Author;
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
}
