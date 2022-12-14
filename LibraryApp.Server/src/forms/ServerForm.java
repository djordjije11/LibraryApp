package forms;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JButton;
import main.Server;

/**
 *
 * @author Djordjije
 */
public class ServerForm extends javax.swing.JFrame {
    private final Server server;
    
    
    public ServerForm(Server server) {
        initComponents();
        this.server = server;
        pack();
        getContentPane().setBackground(new Color(150,194,215));
        setLocationRelativeTo(null);
        setUpButtons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server biblioteke");
        setResizable(false);

        btnStart.setBackground(new java.awt.Color(217, 238, 255));
        btnStart.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        btnStart.setText("POKRENI SERVER");
        btnStart.setFocusable(false);
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setBackground(new java.awt.Color(217, 238, 255));
        btnStop.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        btnStop.setText("UGASI SERVER");
        btnStop.setFocusable(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        lblMessage.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("Server nije pokrenut.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        try {
            server.openServer();
            setUpButtons();
            lblMessage.setText("Server je pokrenut.");
        } catch (IOException ex) {
            lblMessage.setText("Desila se greska prilikom pokretanja servera!");
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        try {
            server.closeServer();
            lblMessage.setText("Server je ugasen.");
            setUpButtons();
        } catch (IOException ex) {
            lblMessage.setText("Desila se greska prilikom gasenja servera!");
        }
    }//GEN-LAST:event_btnStopActionPerformed

    public JButton getStartButton(){
        return btnStart;
    }
    public JButton getStopButton(){
        return btnStop;
    }
    
    private void setUpButtons(){
        btnStart.setEnabled(server.isServerUp() == false);
        btnStop.setEnabled(server.isServerUp() == true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables
}
