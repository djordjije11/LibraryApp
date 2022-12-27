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
}
