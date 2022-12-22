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
    private static List<Window> windowForms;
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
    public static void addWindowForm(Window form){
        if(windowForms == null){
            windowForms = new ArrayList<>();
        }
        System.out.println(form);
        windowForms.add(form);
    }
    public static void removeWindowForm(Window form){
        System.out.println(form);
        if(windowForms != null && form != null){
            windowForms.remove(form);
        }
    }
    public static void closeAllForms(){
        for (Window windowForm : windowForms) {
            System.out.println(windowForm);
            windowForm.dispose();
        }
        windowForms = null;
    }
}
