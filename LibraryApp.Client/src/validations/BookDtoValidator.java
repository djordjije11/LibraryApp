package validations;

import models.dto.BookDto;

/**
 *
 * @author Djordjije
 */
public class BookDtoValidator extends Validator<BookDto> {
    @Override
    protected void validate(BookDto entity) {
        if(entity == null){
            addError("Entity is null!");
            return;
        }
        String title = entity.getTitle();
        Long currentAmount = entity.getCurrentAmount();
        Long addingAmount = entity.getAddingAmount();
        if(title == null || title.isBlank()){
            addError("Knjiga mora imati naslov!");
        }
        if(currentAmount != null && currentAmount < 0){
            addError("Nemoguce je da broj knjiga na stanju bude negativan broj!");
        }
        if(addingAmount != null && addingAmount < 0){
            addError("Nemoguce je da broj knjiga koji se dodaje bude negativan broj!");
        }
    }
}
