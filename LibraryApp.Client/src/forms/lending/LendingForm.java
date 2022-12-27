package forms.lending;

import forms.lending.table.CopiesOfBookTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import models.CopyOfBook;
import models.Member;

/**
 *
 * @author Djordjije
 */
public class LendingForm extends javax.swing.JDialog {

    private DefaultListModel listModel;
    
    public LendingForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        listModel = new DefaultListModel<CopyOfBook>();
        listSelectedCopiesOfBook.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMemberFirstname = new javax.swing.JTextField();
        txtMemberLastname = new javax.swing.JTextField();
        txtBookTitle = new javax.swing.JTextField();
        txtCopyOfBookID = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCopiesOfBook = new javax.swing.JTable();
        btnFind = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSelectedCopiesOfBook = new javax.swing.JList<>();
        txtSelectedMember = new javax.swing.JTextField();
        cmbxMembers = new javax.swing.JComboBox<>();
        btnAddSelectedCopyOfBook = new javax.swing.JButton();
        btnReturnSelectedCopyOfBook = new javax.swing.JButton();
        btnApprove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Iznajmljivanje knjiga");

        tblCopiesOfBook.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblCopiesOfBook);

        btnFind.setText("PRETRAZI");
        btnFind.setFocusable(false);

        jScrollPane1.setViewportView(listSelectedCopiesOfBook);

        txtSelectedMember.setEditable(false);

        cmbxMembers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxMembersActionPerformed(evt);
            }
        });

        btnAddSelectedCopyOfBook.setText("DODAJ ODABRANI PRIMERAK");
        btnAddSelectedCopyOfBook.setFocusable(false);
        btnAddSelectedCopyOfBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSelectedCopyOfBookActionPerformed(evt);
            }
        });

        btnReturnSelectedCopyOfBook.setText("VRATI ODABRANI PRIMERAK");
        btnReturnSelectedCopyOfBook.setFocusable(false);
        btnReturnSelectedCopyOfBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnSelectedCopyOfBookActionPerformed(evt);
            }
        });

        btnApprove.setText("POTVRDI");
        btnApprove.setFocusable(false);

        jLabel1.setText("Ime:");

        jLabel2.setText("Prezime:");

        jLabel3.setText("Naslov:");

        jLabel4.setText("Clan biblioteke");

        jLabel5.setText("Knjiga");

        jLabel6.setText("ID primerka:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ODABERI CLANA BIBLIOTEKE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMemberFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(txtMemberLastname)))
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCopyOfBookID)
                            .addComponent(txtBookTitle)))
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAddSelectedCopyOfBook, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbxMembers, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReturnSelectedCopyOfBook, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtSelectedMember)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtCopyOfBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMemberFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtMemberLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtSelectedMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbxMembers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(42, 42, 42)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReturnSelectedCopyOfBook, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddSelectedCopyOfBook, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbxMembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxMembersActionPerformed
        Object member = cmbxMembers.getSelectedItem();
        if(member != null){
            txtSelectedMember.setText(member.toString());
        }
    }//GEN-LAST:event_cmbxMembersActionPerformed

    private void btnAddSelectedCopyOfBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSelectedCopyOfBookActionPerformed
        int[] selectedRows = tblCopiesOfBook.getSelectedRows();
        if(selectedRows.length == 0){
            JOptionPane.showMessageDialog(this, "Niste odabrali nijedan primerak knjige.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Collection<CopyOfBook> copiesOfBook = new ArrayList<>();
        CopiesOfBookTableModel copiesOfBookTableModel = (CopiesOfBookTableModel) tblCopiesOfBook.getModel();
        for (int selectedRow : selectedRows) {
            CopyOfBook copyOfBook = copiesOfBookTableModel.getCopyOfBook(selectedRow);
            if(listModel.contains(copyOfBook)){
                //JOptionPane.showMessageDialog(this, "Trazeni primerak knjige je vec odabran.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else{
                copiesOfBook.add(copyOfBook);
            }
        }
        listModel.addAll(copiesOfBook);
    }//GEN-LAST:event_btnAddSelectedCopyOfBookActionPerformed

    private void btnReturnSelectedCopyOfBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnSelectedCopyOfBookActionPerformed
        int[] selectedRows = listSelectedCopiesOfBook.getSelectedIndices();
        if(selectedRows == null || selectedRows.length == 0){
            JOptionPane.showMessageDialog(this, "Niste odabrali nijedan primerak knjige.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            listModel.remove(selectedRows[i]);
        }
    }//GEN-LAST:event_btnReturnSelectedCopyOfBookActionPerformed

    public void setBooksTableData(List<CopyOfBook> copiesOfBook) throws Exception{
        tblCopiesOfBook.setModel(new CopiesOfBookTableModel(copiesOfBook));
    }
    public void setUpMembers(List<Member> members){
        Member[] membersArray = new Member[members.size() + 1];
        membersArray[0] = null;
        for (int i = 1; i < membersArray.length; i++) {
            membersArray[i] = members.get(i - 1);
        }
        cmbxMembers.setModel(new DefaultComboBoxModel<>(membersArray));
    }
    public List<CopyOfBook> getListOfSelectedCopiesOfBooksToLend(){
        List<CopyOfBook> copiesOfBook = new ArrayList<>();
        for (int i = 0, size = listModel.getSize(); i < size; i++) {
            copiesOfBook.add((CopyOfBook)listModel.getElementAt(i));
        }
        return copiesOfBook;
    }
    public Member getSelectedMemberToLend(){
        return (Member)cmbxMembers.getSelectedItem();
    }
    public JButton getFindButton(){
        return btnFind;
    }
    public JButton getApproveButton(){
        return btnApprove;
    }
    public JTextField getBookTitleTextField(){
        return txtBookTitle;
    }
    public JTextField getCopyOfBookIDTextField(){
        return txtCopyOfBookID;
    }
    public JTextField getMemberFirstnameTextField(){
        return txtMemberFirstname;
    }
    public JTextField getMemberLastnameTextField(){
        return txtMemberLastname;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSelectedCopyOfBook;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnReturnSelectedCopyOfBook;
    private javax.swing.JComboBox<Member> cmbxMembers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<CopyOfBook> listSelectedCopiesOfBook;
    private javax.swing.JTable tblCopiesOfBook;
    private javax.swing.JTextField txtBookTitle;
    private javax.swing.JTextField txtCopyOfBookID;
    private javax.swing.JTextField txtMemberFirstname;
    private javax.swing.JTextField txtMemberLastname;
    private javax.swing.JTextField txtSelectedMember;
    // End of variables declaration//GEN-END:variables
}
