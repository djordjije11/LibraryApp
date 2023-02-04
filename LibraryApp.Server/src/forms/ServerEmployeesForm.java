package forms;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import tcp.TcpServer;

/**
 *
 * @author Djordjije
 */
public class ServerEmployeesForm extends javax.swing.JDialog {

    /**
     * Creates new form ServerEmployeesForm
     */
    public ServerEmployeesForm(java.awt.Frame parent, boolean modal, List<TcpServer> tcpServerList) {
        super(parent, modal);
        initComponents();
        pack();
        getContentPane().setBackground(new Color(150,194,215));
        setLocationRelativeTo(null);
        tblEmployees.setModel(new EmployeesTableModel(tcpServerList));
        designTable();
    }
    private void designTableColumns(){
        tblEmployees.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel columnModel = tblEmployees.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);    
        columnModel.getColumn(1).setPreferredWidth(160);
        columnModel.getColumn(2).setPreferredWidth(160);
        columnModel.getColumn(3).setPreferredWidth(400);
    }
    private void designTable(){
        tblEmployees.getTableHeader().setOpaque(false);
        tblEmployees.getTableHeader().setBackground(new Color(107,158,183));
        tblEmployees.getTableHeader().setForeground(Color.white);
        tblEmployees.getTableHeader().setFont(new Font("Cascadia Code", Font.PLAIN, 14));
        tblEmployees.setBackground(new Color(217,238,255));
        designTableColumns();
    }
    
    public void fireTableChange(){
        ((EmployeesTableModel)tblEmployees.getModel()).fireTableDataChanged();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista prijavljenih korisnika");

        tblEmployees.setBackground(new java.awt.Color(217, 238, 255));
        tblEmployees.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255)));
        tblEmployees.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        tblEmployees.setFillsViewportHeight(true);
        tblEmployees.setGridColor(new java.awt.Color(153, 204, 255));
        tblEmployees.setSelectionBackground(new java.awt.Color(217, 238, 255));
        jScrollPane1.setViewportView(tblEmployees);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEmployees;
    // End of variables declaration//GEN-END:variables
}
