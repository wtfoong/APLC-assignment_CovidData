/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplcassignment;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static aplcassignment.Task1.AllCountryConfirmCasesTableData;

/**
 *
 * @author rainy
 */
public class MainMenu extends javax.swing.JFrame {
    DefaultTableModel allCountrySumCaseModel;
    
    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        cmbTask1ActionPerformed(null);
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
        tblstatistic.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
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
                .addComponent(cmbTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 656, Short.MAX_VALUE)
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
        try {
            List<Country> confirmCaseList = CovidData.provideC19GlobalConfirmedCaseData();
            switch (cmbTask1.getSelectedIndex()) {
                default -> cmbTask1.setSelectedIndex(0);
                case 0 -> {
                    setCountrySumCaseTable(confirmCaseList);
                }
                case 1 -> {
                    DateTimeFormatter weekNYearDateFormat = DateTimeFormatter.ofPattern("ww,Y",Locale.getDefault());
                    setCountryWeeklyCaseTable(confirmCaseList,weekNYearDateFormat);
                }
                case 2 -> {
                    DateTimeFormatter weekNYearDateFormat = DateTimeFormatter.ofPattern("MMMM,Y",Locale.UK);
                    setCountryMonthlyCaseTable(confirmCaseList,weekNYearDateFormat);
                }
                
                
                
            }
        } catch (IOException | CsvException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_cmbTask1ActionPerformed

    private void setCountrySumCaseTable(List<Country> confirmCaseList){         
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
            ans = Task1.AllCountryConfirmCasesTableData(confirmCaseList);
            if (ans!=null) {
                int i=0;
                for(String[] test :AllCountryConfirmCasesTableData(confirmCaseList) ){
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
        tblstatistic.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
    }
    
    private void setCountryWeeklyCaseTable(List<Country> confirmCaseList,DateTimeFormatter dateFormat){         
        String[] table1Columns = Task1.getAllWeeksStartNEndDateHeader();
        String[][] table1Data = Task1.weeklyNMonthlyCasesForCountriesTabledata(confirmCaseList, dateFormat);
        DefaultTableModel model = new DefaultTableModel(table1Data, table1Columns) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
        tblstatistic.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstatistic.setModel(model);
        DefaultTableCellRenderer centerrenderer = new DefaultTableCellRenderer();
        centerrenderer.setHorizontalAlignment(JLabel.CENTER);
        tblstatistic.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblstatistic.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        for (int i = 2; i < table1Columns.length; i++) {
             tblstatistic.getColumnModel().getColumn(i).setPreferredWidth(165);
             tblstatistic.getColumnModel().getColumn(i).setCellRenderer(centerrenderer);
        }
       
    }
    
    private void setCountryMonthlyCaseTable(List<Country> confirmCaseList,DateTimeFormatter dateFormat){         
        String[] table1Columns = Task1.getAllMonthHeader();
        String[][] table1Data = Task1.weeklyNMonthlyCasesForCountriesTabledata(confirmCaseList, dateFormat);
        DefaultTableModel model = new DefaultTableModel(table1Data, table1Columns) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
        tblstatistic.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstatistic.setModel(model);
        DefaultTableCellRenderer centerrenderer = new DefaultTableCellRenderer();
        centerrenderer.setHorizontalAlignment(JLabel.CENTER);
        tblstatistic.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblstatistic.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        for (int i = 2; i < table1Columns.length; i++) {
             tblstatistic.getColumnModel().getColumn(i).setPreferredWidth(100);
             tblstatistic.getColumnModel().getColumn(i).setCellRenderer(centerrenderer);
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
