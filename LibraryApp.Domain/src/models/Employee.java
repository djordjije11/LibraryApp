/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Djordjije
 */
public class Employee implements IEntity {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private Building building;
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getClassName() {
        return "Employee";
    }
}
