/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author Djordjije
 */
public class Member implements IEntity {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private String email;
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
