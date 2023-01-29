package validations;

import models.Member;

/**
 *
 * @author Djordjije
 */
public class MemberValidator extends Validator<Member> {

    @Override
    protected void validate(Member entity) {
        String firstname = entity.getFirstname();
        String lastname = entity.getLastname();
        String email = entity.getEmail();
        if(firstname == null || firstname.length() < 2)
            addError("Firstname must have at least two characters");
        if(lastname == null || lastname.length() < 2)
            addError("Lastname must have at least two characters");
        //if(email == null)
            
        }
    
}
