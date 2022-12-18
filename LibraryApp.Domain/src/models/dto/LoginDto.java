package models.dto;

import models.Building;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public class LoginDto {
    private Employee employee;
    private Building building;
    
    public Building getBuilding() {
        return building;
    }
    public void setBuilding(Building building) {
        this.building = building;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
