/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.time.LocalDate;
import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public class Lending implements IEntity, Serializable {
    private Long id;
    private Book book;
    private CopyOfBook copyOfBook;
    private Member member;
    private LocalDate lendingDate;
    private LocalDate returnDate;
    
    @Override
    public ModelElement getModelElement() {
        return ModelElement.LENDING;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public Long getId() {
        return id;
    }
}
