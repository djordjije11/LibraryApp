package models;

import java.io.Serializable;
import java.time.LocalDate;
import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class Member implements IEntity, Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private String email;

    public Member(Long id, String firstname, String lastname, LocalDate birthday, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
    }
    public Member(String firstname, String lastname, LocalDate birthday, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
    }
    public Member(Long id){
        this.id = id;
    }
    public Member(){}
    
    @Override
    public ModelElement getModelElement() {
        return ModelElement.MEMBER;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public Long getId() {
        return id;
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
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String singlePrint(){
        return "ID: " + id + ",\n" + firstname + " " + lastname + ",\nDatum rodjenja: " + birthday + ",\nE-mail adresa: " + email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + firstname + " " + lastname;
    }
}
