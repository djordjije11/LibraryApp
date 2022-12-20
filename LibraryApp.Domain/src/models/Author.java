/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Objects;
import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class Author implements IEntity {
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
        return firstname + " " + lastname;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Author author){
            return this.id == author.id;
        } else return false;
    }
}
