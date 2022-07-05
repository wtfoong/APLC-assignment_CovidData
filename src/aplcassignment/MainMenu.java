/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplcassignment;

import static aplcassignment.Task1.q1AllCountryWithConfirm;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rainy
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }
    DefaultTableModel allCountrySumCaseModel;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblstatistic = new javax.swing.JTable();
        btnProlog = new javax.swing.JButton();
        cmbTask1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblstatistic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblstatistic.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblstatistic);

        btnProlog.setText("Prolog");

        cmbTask1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1. Total confirmed Covid-19 cases by country", "2.1. Confirmed Covid-19 cases by week for each country", "2.2. Confirmed Covid-19 cases by month for each country", "3. Highes/dowest death and recovered for each country" }));
        cmbTask1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTask1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cmbTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 774, Short.MAX_VALUE)
                .addComponent(btnProlog, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProlog, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTask1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTask1ActionPerformed
        switch (cmbTask1.getSelectedIndex()) {
            case 0 -> {
                setCountrySumCaseTable();
            }
               

                
        }
    }//GEN-LAST:event_cmbTask1ActionPerformed

    private void setCountrySumCaseTable(){
         tblstatistic.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                     "ID", "Country", "Total Confirmed Cases"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
         
                DefaultTableModel resetTable = (DefaultTableModel) tblstatistic.getModel();
                resetTable.setRowCount(0);   
                allCountrySumCaseModel = (DefaultTableModel) tblstatistic.getModel();
                List ans;
                try {
                    ans = Task1.q1AllCountryWithConfirm();
                    if (ans!=null) {
                        int i=0;
                        for(String[] test :q1AllCountryWithConfirm() ){
                            if (ans == null){
                                
                            } else{
                                
                                allCountrySumCaseModel.insertRow(allCountrySumCaseModel.getRowCount(), new Object[] {
                                    i+=1,test[0],test[1]
                                });
                            }
                        }
                        
                    }
                } catch (IOException | CsvException ex) {                    
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
              
               
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProlog;
    private javax.swing.JComboBox<String> cmbTask1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblstatistic;
    // End of variables declaration//GEN-END:variables
}
