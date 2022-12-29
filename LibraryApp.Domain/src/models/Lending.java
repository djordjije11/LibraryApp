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
    private CopyOfBook copyOfBook;
    private Member member;
    private LocalDate lendingDate;
    private LocalDate returnDate;
    
    public Lending(){}
    public Lending(CopyOfBook copyOfBook, Member member, LocalDate lendingDate){
        this.copyOfBook = copyOfBook;
        this.member = member;
        this.lendingDate = lendingDate;
    }
    public Lending(Long id, CopyOfBook copyOfBook, Member member, LocalDate lendingDate){
        this(copyOfBook, member, lendingDate);
        this.id = id;
    }
    
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

    public CopyOfBook getCopyOfBook() {
        return copyOfBook;
    }

    public void setCopyOfBook(CopyOfBook copyOfBook) {
        this.copyOfBook = copyOfBook;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    public String singlePrint(){
        return "ID: " + id + "\nKnjiga: " + copyOfBook + "\nClan biblioteke: " + member + "\nDatuma: " + lendingDate;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; " + lendingDate + "; " + copyOfBook.getId() + " - " + copyOfBook.getBook().getTitle() + "; " + member.getFirstname() + " " + member.getLastname();
    }
}
