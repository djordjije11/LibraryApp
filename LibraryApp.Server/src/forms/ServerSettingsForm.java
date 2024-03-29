package forms;

import database.configurations.ConfigParamKeys;
import database.configurations.SqlJsonFileConfigurationManager;
import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Djordjije
 */
public class ServerSettingsForm extends javax.swing.JDialog {
    private ServerForm serverForm;
    private SqlJsonFileConfigurationManager configManager;
    private String url;
    private String user;
    private String password;

    public ServerSettingsForm(java.awt.Frame parent, boolean modal, SqlJsonFileConfigurationManager configManager) throws Exception {
        super(parent, modal);
        this.configManager = configManager;
        serverForm = (ServerForm) parent;
        initComponents();
        pack();
        getContentPane().setBackground(new Color(150,194,215));
        setLocationRelativeTo(null);
        initFormData();
    }
    private void initFormData() throws Exception{
        url = configManager.getConfigParam(ConfigParamKeys.URL);
        user = configManager.getConfigParam(ConfigParamKeys.USER);
        password = configManager.getConfigParam(ConfigParamKeys.PASSWORD);
        txtUrl.setText(url);
        txtUser.setText(user);
        txtPassword.setText(password);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUrl = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Konfiguracija");
        setResizable(false);

        lblUrl.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        lblUrl.setText("URL:");

        lblUser.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        lblUser.setText("User:");

        lblPassword.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        lblPassword.setText("Password:");

        txtUrl.setBackground(new java.awt.Color(252, 255, 255));
        txtUrl.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N

        txtPassword.setBackground(new java.awt.Color(252, 255, 255));
        txtPassword.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N

        txtUser.setBackground(new java.awt.Color(252, 255, 255));
        txtUser.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N

        btnSave.setBackground(new java.awt.Color(217, 238, 255));
        btnSave.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        btnSave.setText("SACUVAJ");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUrl)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPassword)
                        .addComponent(lblUser, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUrl)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUrl)
                    .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(JOptionPane.showOptionDialog(this, "Kako bi se izvrsile zeljene promene potrebno je ugasiti server ukoliko je pokrenut.\nDa li ste sigurni?", "Promena konfiguracije", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Da", "Ne"}, "Ne") == 1){
            return;
        }
        String newUrl = txtUrl.getText().trim();
        String newUser = txtUser.getText().trim();
        String newPassword = txtPassword.getText().trim();
        HashMap<String, String> sqlData = new HashMap();
        if(url.equals(newUrl) == false){
            url = newUrl;
            sqlData.put(ConfigParamKeys.URL, url);
        }
        if(user.equals(newUser) == false){
            user = newUser;
            sqlData.put(ConfigParamKeys.USER, user);
        }
        if(password.equals(newPassword) == false){
            password = newPassword;
            sqlData.put(ConfigParamKeys.PASSWORD, password);
        }
        if(sqlData.isEmpty() == true){
            return;
        }
        try {
            configManager.updateData(sqlData);
            serverForm.stopServer();
            configManager.refresh();
        } catch (ParseException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Promene u podacima nisu uspesno zabelezene.", "Neuspesno promenjena konfiguracija", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Promene u podacima su zabelezene.\nPotrebno je da pokrenete server opet.", "Uspesno promenjena konfiguracija", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUrl;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
