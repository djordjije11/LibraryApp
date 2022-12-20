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
    
    public Employee(){}
    public Employee(Long id, String password){
        this.id = id;
        this.password = password;
    }
    
    @Override
    public String getClassName() {
        return "Employee";
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
}
