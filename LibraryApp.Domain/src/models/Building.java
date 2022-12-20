/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class Building implements IEntity {
    private Long id;
    private String name;
    
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
}
