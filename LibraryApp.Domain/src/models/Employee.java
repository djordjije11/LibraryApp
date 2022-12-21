package models;

import java.io.Serializable;
import message.ModelElement;

/**
 *
 * @author Djordjije 
 */
public class Employee implements IEntity, Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private Building building;
    
    public Employee(){}
    public Employee(Long id, String password){
        this.id = id;
        this.password = password;
    }
    public Employee(Long id, String firstname, String lastname, String password, Building building) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.building = building;
    }
    
    @Override
    public ModelElement getModelElement() {
        return ModelElement.EMPLOYEE;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public Long getId() {
        return id;
    }
    public String getPassword(){
        return password;
    }
    public Building getBuilding(){
        return building;
    }
}
