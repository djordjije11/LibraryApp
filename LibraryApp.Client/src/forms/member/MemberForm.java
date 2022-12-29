package forms.member;

import java.awt.Color;
import java.time.LocalDate;
import models.Member;
import java.time.Month;
import java.time.YearMonth;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

/*
 *
 * @author Djordjije
 */
public final class MemberForm extends javax.swing.JDialog {
    private Member member;
    
    public MemberForm(java.awt.Frame parent, boolean modal, Member member) {
        super(parent, modal);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(150,194,215));
        setUpYears();
        setUpMonths();
        setUpDays();
        setMember(member);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbxDay = new javax.swing.JComboBox<>();
        cmbxMonth = new javax.swing.JComboBox<>();
        cmbxYear = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clan");
        setResizable(false);

        txtFirstName.setBackground(new java.awt.Color(236, 250, 255));
        txtFirstName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        txtLastName.setBackground(new java.awt.Color(236, 250, 255));
        txtLastName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        txtEmail.setBackground(new java.awt.Color(236, 250, 255));
        txtEmail.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel1.setText("Ime:");

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setText("Prezime:");

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setText("Datum rodjenja:");

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel4.setText("E-mail:");

        btnDelete.setBackground(new java.awt.Color(217, 238, 255));
        btnDelete.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        btnDelete.setText("OBRISI");
        btnDelete.setFocusable(false);

        btnSave.setBackground(new java.awt.Color(217, 238, 255));
        btnSave.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        btnSave.setText("SACUVAJ");
        btnSave.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel5.setText("Godina:");

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel6.setText("Dan:");

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel7.setText("Mesec:");

        cmbxDay.setBackground(new java.awt.Color(236, 250, 255));
        cmbxDay.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        cmbxMonth.setBackground(new java.awt.Color(236, 250, 255));
        cmbxMonth.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        cmbxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxMonthActionPerformed(evt);
            }
        });

        cmbxYear.setBackground(new java.awt.Color(236, 250, 255));
        cmbxYear.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        cmbxYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cmbxDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(txtEmail)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbxMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxMonthActionPerformed
        updateDays();
    }//GEN-LAST:event_cmbxMonthActionPerformed

    private void cmbxYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxYearActionPerformed
        updateDays();
    }//GEN-LAST:event_cmbxYearActionPerformed

    private void updateDays(){
        int monthLength = YearMonth.of((Integer)cmbxYear.getSelectedItem(), (Month)cmbxMonth.getSelectedItem()).lengthOfMonth();
        ComboBoxModel<Integer> cmbxModel = cmbxDay.getModel();
        int cmbxSize = cmbxModel.getSize();
        int difference = monthLength - cmbxSize;
        if(difference > 0){
            for (int i = 1; i <= difference; i++) {
                System.out.println(cmbxSize + i);
                cmbxDay.addItem(cmbxSize + i);
            }
        } else if(difference < 0){
            for (int i = 0; i > difference; i--) {
                cmbxDay.removeItem(cmbxSize + i);
            }
        }
    }
    private void setUpDays(){
        int monthLength = YearMonth.of((Integer)cmbxYear.getSelectedItem(), (Month)cmbxMonth.getSelectedItem()).lengthOfMonth();
        Integer[] days = new Integer[monthLength];
        for(int i = 1; i <= monthLength; i++){
            days[i - 1] = i;
        }
        cmbxDay.setModel(new DefaultComboBoxModel<>(days));
    }
    private void setUpMonths(){
        Month[] months = new Month[12];
        for(int i = 1; i <= 12; i++){
            months[i - 1] = Month.of(i);
        }
        cmbxMonth.setModel(new DefaultComboBoxModel<>(months));
    }
    private void setUpYears(){
        int currentYear = LocalDate.now().getYear();
        Integer[] years = new Integer[150];
        for(int i = 0; i < 150; i++){
            years[i] = currentYear - i;
        }
        cmbxYear.setModel(new DefaultComboBoxModel<>(years));
    }
    public Member getMember(){
        return member;
    }
    public void setMember(Member member){
        this.member = member;
        setUpAttributeFields();
        setUpDeleteButton();
    }
    public JButton getSaveButton(){
        return btnSave;
    }
    public JButton getDeleteButton(){
        return btnDelete;
    }
    public void setUpDeleteButton(){
        if(member == null){
            btnDelete.setVisible(false);
        } else btnDelete.setVisible(true);
    }
    public JTextField getFirstNameTextField(){
        return txtFirstName;
    }
    public JTextField getLastNameTextField(){
        return txtLastName;
    }
    public LocalDate getBirthday(){
        return LocalDate.of((int)cmbxYear.getSelectedItem(), (Month)cmbxMonth.getSelectedItem(), (int)cmbxDay.getSelectedItem());
    }
    public JTextField getEmailTextField(){
        return txtEmail;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<Integer> cmbxDay;
    private javax.swing.JComboBox<Month> cmbxMonth;
    private javax.swing.JComboBox<Integer> cmbxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    // End of variables declaration//GEN-END:variables

    private void setUpAttributeFields() {
        if(member == null)
            return;
        txtFirstName.setText(member.getFirstname());
        txtLastName.setText(member.getLastname());
        LocalDate birthday = member.getBirthday();
        cmbxYear.setSelectedItem(birthday.getYear());
        cmbxMonth.setSelectedItem(birthday.getMonth());
        cmbxDay.setSelectedItem(birthday.getDayOfMonth());
        txtEmail.setText(member.getEmail());
    }
}
