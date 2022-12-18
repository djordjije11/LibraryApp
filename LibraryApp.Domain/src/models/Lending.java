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
public class Lending implements IEntity {
    private Long id;
    private Book book;
    private CopyOfBook copyOfBook;
    private Member member;
    private LocalDate lendingDate;
    private LocalDate returnDate;
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getClassName() {
        return "Lending";
    }
}
