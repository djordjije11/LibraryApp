package models;

import java.io.Serializable;
import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class Building implements IEntity, Serializable {
    private Long id;
    private String name;

    public Building(Long id, String name) {
        this(id);
        this.name = name;
    }
    public Building(Long id){
        this.id = id;
    }
    public Building() {
    }
    
    @Override
    public ModelElement getModelElement() {
        return ModelElement.BUILDING;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public Long getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + name;
    }
}
