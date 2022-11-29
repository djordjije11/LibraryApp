/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Djordjije
 */
public class Book implements IEntity {
    private Long id;
    private String title;
    private String description;
    private Author author;

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
}