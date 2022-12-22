package session;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import models.Building;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public final class Session {
    private static Building building;
    private static Employee employee;

    public static Building getBuilding() {
        return building;
    }
    public static void setBuilding(Building building) {
       Session.building = building;
    }
    public static Employee getEmployee() {
        return employee;
    }
    public static void setEmployee(Employee employee) {
        Session.employee = employee;
    }
}
