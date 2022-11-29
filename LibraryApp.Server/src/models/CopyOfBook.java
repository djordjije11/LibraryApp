/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Djordjije
 */
public class CopyOfBook implements IEntity {
    private Long id;
    private Book book;
    private Building building;
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
