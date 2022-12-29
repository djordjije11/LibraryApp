package forms.lending;

import forms.lending.table.LendingsTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import models.Lending;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class ReturnLendingForm extends javax.swing.JDialog {

    private DefaultListModel listModel;
   
    public ReturnLendingForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        listModel = new DefaultListModel<Lending>();
        listSelectedLendings.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblLendings = new javax.swing.JTable();
        btnAddSelectedLending = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSelectedLendings = new javax.swing.JList<>();
        btnReturnSelectedLending = new javax.swing.JButton();
        btnApprove = new javax.swing.JButton();
        btnFindMember = new javax.swing.JButton();
        txtMemberFirstname = new javax.swing.JTextField();
        txtMemberLastname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbxMembers = new javax.swing.JComboBox<>();
        txtSelectedMember = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblLendings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblLendings);

        btnAddSelectedLending.setText("DODAJ ODABRANI PRIMERAK");
        btnAddSelectedLending.setFocusable(false);
        btnAddSelectedLending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSelectedLendingActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listSelectedLendings);

        btnReturnSelectedLending.setText("VRATI ODABRANI PRIMERAK");
        btnReturnSelectedLending.setFocusable(false);
        btnReturnSelectedLending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnSelectedLendingActionPerformed(evt);
            }
        });

        btnApprove.setText("POTVRDI");
        btnApprove.setFocusable(false);

        btnFindMember.setText("PRETRAZI CLANA");
        btnFindMember.setFocusable(false);

        jLabel2.setText("Ime:");

        jLabel3.setText("Prezime:");

        jLabel4.setText("Clan biblioteke");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ODABERI CLANA BIBLIOTEKE");

        cmbxMembers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxMembersActionPerformed(evt);
            }
        });

        txtSelectedMember.setEditable(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Iznajmljeni primerci koje clan biblioteke vraca:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFindMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMemberFirstname)
                                        .addComponent(txtMemberLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbxMembers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addComponent(txtSelectedMember)))
                    .addComponent(btnAddSelectedLending, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReturnSelectedLending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSelectedMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMemberFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtMemberLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnFindMember, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbxMembers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReturnSelectedLending, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddSelectedLending, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbxMembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxMembersActionPerformed
        Object member = cmbxMembers.getSelectedItem();
        if(member != null){
            txtSelectedMember.setText(member.toString());
        }
    }//GEN-LAST:event_cmbxMembersActionPerformed

    private void btnAddSelectedLendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSelectedLendingActionPerformed
        int[] selectedRows = tblLendings.getSelectedRows();
        if(selectedRows.length == 0){
            JOptionPane.showMessageDialog(this, "Niste odabrali nijedan primerak knjige.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Collection<Lending> lendings = new ArrayList<>();
        LendingsTableModel lendingsTableModel = (LendingsTableModel) tblLendings.getModel();
        for (int selectedRow : selectedRows) {
            Lending lending = lendingsTableModel.getLending(selectedRow);
            if(listModel.contains(lending)){
                //JOptionPane.showMessageDialog(this, "Trazeni primerak knjige je vec odabran.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else{
                lendings.add(lending);
            }
        }
        listModel.addAll(lendings);
    }//GEN-LAST:event_btnAddSelectedLendingActionPerformed

    private void btnReturnSelectedLendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnSelectedLendingActionPerformed
        int[] selectedRows = listSelectedLendings.getSelectedIndices();
        if(selectedRows == null || selectedRows.length == 0){
            JOptionPane.showMessageDialog(this, "Niste odabrali nijedan primerak knjige.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            listModel.remove(selectedRows[i]);
        }
    }//GEN-LAST:event_btnReturnSelectedLendingActionPerformed

    public void setLendingsTableData(List<Lending> lendings) throws Exception{
        tblLendings.setModel(new LendingsTableModel(lendings));
    }
    public void setUpMembers(List<Member> members){
        Member[] membersArray = new Member[members.size() + 1];
        membersArray[0] = null;
        for (int i = 1; i < membersArray.length; i++) {
            membersArray[i] = members.get(i - 1);
        }
        cmbxMembers.setModel(new DefaultComboBoxModel<>(membersArray));
    }
    public List<Lending> getListOfSelectedLendingsToReturn(){
        List<Lending> lendings = new ArrayList<>();
        for (int i = 0, size = listModel.getSize(); i < size; i++) {
            lendings.add((Lending)listModel.getElementAt(i));
        }
        return lendings;
    }
    public Member getSelectedMemberToReturnLendings(){
        return (Member)cmbxMembers.getSelectedItem();
    }
    public JButton getFindMemberButton(){
        return btnFindMember;
    }
    public JButton getApproveButton(){
        return btnApprove;
    }
    public JTextField getMemberFirstnameTextField(){
        return txtMemberFirstname;
    }
    public JTextField getMemberLastnameTextField(){
        return txtMemberLastname;
    }
    public JComboBox<Member> getMembersComboBox(){
        return cmbxMembers;
    }
    public JTextField getSelectedMemberTextField(){
        return txtSelectedMember;
    }
    
    public void refreshForm(){
        txtMemberFirstname.setText("");
        txtMemberLastname.setText("");
        txtSelectedMember.setText("");
        cmbxMembers.removeAllItems();
        ((LendingsTableModel)tblLendings.getModel()).clearData();
        listModel.clear();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSelectedLending;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnFindMember;
    private javax.swing.JButton btnReturnSelectedLending;
    private javax.swing.JComboBox<Member> cmbxMembers;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Lending> listSelectedLendings;
    private javax.swing.JTable tblLendings;
    private javax.swing.JTextField txtMemberFirstname;
    private javax.swing.JTextField txtMemberLastname;
    private javax.swing.JTextField txtSelectedMember;
    // End of variables declaration//GEN-END:variables
}
