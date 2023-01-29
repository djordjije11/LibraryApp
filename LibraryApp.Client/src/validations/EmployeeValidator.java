package validations;

import models.Employee;

/**
 *
 * @author Djordjije
 */
public class EmployeeValidator extends Validator<Employee> {
    @Override
    protected void validate(Employee entity) {
        if(entity == null){
            addError("Entity is null!");
            return;
        }
        Long id = entity.getId();
        String password = entity.getPassword();
        if(id == null || id <= 0){
            addError("ID zaposlenog mora biti pozitivan ceo broj.");
        }
        if(password == null || password.isBlank() || password.contains(" ")){
            addError("Lozinka nije pravilno uneta.");
        }
    }
}
