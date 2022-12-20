package models;

import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class CopyOfBook implements IEntity {
    private Long id;
    private Long bookId;
    private Long buildingId;
    
    public CopyOfBook(){}
    public CopyOfBook(Long id, Long bookId, Long buildingId){
        this.id = id;
        this.bookId = bookId;
        this.buildingId = buildingId;
    }
    public CopyOfBook(Long bookId, Long buildingId){
        this.bookId = bookId;
        this.buildingId = buildingId;
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
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public Long getBuildingId() {
        return buildingId;
    }
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
