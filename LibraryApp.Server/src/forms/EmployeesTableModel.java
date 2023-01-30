package forms;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Building;
import models.Employee;
import tcp.TcpServer;

/**
 *
 * @author Djordjije
 */
public class EmployeesTableModel extends AbstractTableModel {
    private List<TcpServer> tcpServerList;
    private final String[] columnNames = {"ID","Ime","Prezime", "Zgrada biblioteke"};
    private final Class[] columnTypes = {Long.class, String.class, String.class, Building.class};
    public EmployeesTableModel(List<TcpServer> tcpServerList){
        this.tcpServerList = tcpServerList;
    }
    @Override
    public int getRowCount() {
        if(tcpServerList == null)
            return 0;
        return tcpServerList.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TcpServer connectedUser = tcpServerList.get(rowIndex);
        if(connectedUser == null){
            return "n/a";
        }
        Employee employee = connectedUser.getEmployee();
        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getFirstname();
            case 2:
                return employee.getLastname();
            case 3:
                return employee.getBuilding();
            default:
                return "n/a";
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }
}
