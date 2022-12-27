package models;

import java.io.Serializable;
import java.util.Objects;
import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class Author implements IEntity, Serializable {
    private Long id;
    private String firstname;
    private String lastname;

    public Author(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Author() {
    }
   
    @Override
    public ModelElement getModelElement() {
        return ModelElement.AUTHOR;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    @Override
    public String toString() {
        String firstnameToPrint = this.firstname != null ? this.firstname : "";
        String lastnameToPrint = this.lastname != null ? this.lastname : "";
        return firstnameToPrint + " " + lastnameToPrint;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Author author){
            return Objects.equals(this.id, author.id);
        } else return false;
    }
}
