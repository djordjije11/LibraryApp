package forms;

import javax.swing.JMenuItem;

/**
 *
 * @author Djordjije
 */
public class MainForm extends javax.swing.JFrame {
    
    public MainForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        menuModels = new javax.swing.JMenu();
        menuItemMembers = new javax.swing.JMenuItem();
        menuItemBooks = new javax.swing.JMenuItem();
        menuItemLending = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuModels.setText("Forme");

        menuItemMembers.setText("Otvori formu za clanove");
        menuModels.add(menuItemMembers);

        menuItemBooks.setText("Otvori formu za knjige");
        menuModels.add(menuItemBooks);

        menuItemLending.setText("jMenuItem1");
        menuModels.add(menuItemLending);

        jMenuBar2.add(menuModels);

        menuLogout.setText("Log out");
        jMenuBar2.add(menuLogout);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JMenuItem getMembersMenu(){
        return menuItemMembers;
    }
    public JMenuItem getBooksMenu(){
        return menuItemBooks;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem menuItemBooks;
    private javax.swing.JMenuItem menuItemLending;
    private javax.swing.JMenuItem menuItemMembers;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuModels;
    // End of variables declaration//GEN-END:variables
}
