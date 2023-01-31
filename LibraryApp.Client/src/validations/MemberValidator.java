package validations;

import models.Member;

/**
 *
 * @author Djordjije
 */
public class MemberValidator extends Validator<Member> {
    @Override
    protected void validate(Member entity) {
        if(entity == null){
            addError("Entity is null!");
            return;
        }
        String firstname = entity.getFirstname();
        String lastname = entity.getLastname();
        String email = entity.getEmail();
        if(firstname == null || firstname.length() < 2)
            addError("Ime mora da sadrzi minimum 2 slova.");
        if(firstname != null && firstname.isBlank() == false){
            Character firstLetter = firstname.charAt(0);
            if(firstLetter.toString().equals(firstLetter.toString().toLowerCase()))
                addError("Ime mora poceti prvim velikim slovom.");
        }
        if(lastname == null || lastname.length() < 2)
            addError("Prezime mora da sadrzi minimum 2 slova.");
        if(lastname != null && firstname.isBlank() == false){
            Character firstLetter = lastname.charAt(0);
            if(firstLetter.toString().equals(firstLetter.toString().toLowerCase()))
                addError("Prezime mora poceti prvim velikim slovom.");
        }
        if(email != null && email.isBlank() == false && (email.contains(" ") == true || email.contains("@") == false)){
            addError("E-mail nije pravilno unet.");
        }
    }
}
