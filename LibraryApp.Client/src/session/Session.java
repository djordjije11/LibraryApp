package session;

import models.Building;
import models.Employee;

/**
 *
 * @author Djordjije
 */
public final class Session {
    private static Building building;
    private static Employee employee;
    private static boolean clientWantsToLogin = true;

    public static boolean doesClientWantToLogin(){
        return clientWantsToLogin;
    }
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
    public static void restartSession(){
        clientWantsToLogin = true;
        Session.setEmployee(null);
        Session.setBuilding(null);
    }
    public static void exitSession(){
        clientWantsToLogin = false;
        Session.setEmployee(null);
        Session.setBuilding(null);
    }
}
