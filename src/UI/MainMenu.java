/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import aplcassignment.Country;
import aplcassignment.CovidData;
import aplcassignment.Task1;
import aplcassignment.tableData;
import com.opencsv.exceptions.CsvException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rainy
 */
public class MainMenu extends javax.swing.JFrame {
    DefaultTableModel allCountrySumCaseModel;
    final SimpleDateFormat weekNYearFormat = new SimpleDateFormat("ww,Y");
    final SimpleDateFormat displayWeekFormat = new SimpleDateFormat("dd-MMM-yyyy");
    final SimpleDateFormat monthNYearFormat = new SimpleDateFormat("MMM-yyyy");
    final List<Country> confirmCaseList = CovidData.provideC19GlobalConfirmedCaseData();
    final List<Country> deathList = CovidData.provideC19GlobalDeathData();
    final List<Country> recoveredList = CovidData.provideC19GlobalRecoveredData();
    
    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        cmbTask1ActionPerformed(null);
        spSearchResult.setVisible(false);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                autocompleteSearchText(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               autocompleteSearchText(false);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                autocompleteSearchText(false);
            }
        });
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
        btnSearch = new javax.swing.JButton();
        spSearchResult = new javax.swing.JScrollPane();
        jlSearch = new javax.swing.JList<>();
        txtSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblstatistic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 188, 1355, 627));

        btnProlog.setText("Prolog");
        btnProlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrologActionPerformed(evt);
            }
        });
        getContentPane().add(btnProlog, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 45, 150, 42));

        cmbTask1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1. Total confirmed Covid-19 cases by country", "2.1. Confirmed Covid-19 cases by week for each country", "2.2. Confirmed Covid-19 cases by month for each country", "3. Highes/dowest death and recovered for each country" }));
        cmbTask1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTask1ActionPerformed(evt);
            }
        });
        getContentPane().add(cmbTask1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 43, 461, 46));

        btnSearch.setText("Search");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1169, 43, -1, 36));

        jlSearch.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jlSearchFocusLost(evt);
            }
        });
        jlSearch.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSearchValueChanged(evt);
            }
        });
        spSearchResult.setViewportView(jlSearch);

        getContentPane().add(spSearchResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(906, 85, 257, 90));

        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(906, 43, 257, 36));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTask1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTask1ActionPerformed
        switch (cmbTask1.getSelectedIndex()) {
            default -> cmbTask1.setSelectedIndex(0);
            case 0 -> {
                setCountrySumCaseTable(confirmCaseList);
            }
            case 1 -> {
                
                setCountryWeeklyCaseTable(confirmCaseList,weekNYearFormat);
            }
            case 2 -> {
                
                setCountryMonthlyCaseTable(confirmCaseList,monthNYearFormat);
            }
            case 3 -> {
                
                setCountryDeathRecoverHLTable(deathList,recoveredList);
            }
            
            
            
        }
        
    }//GEN-LAST:event_cmbTask1ActionPerformed

    private void btnPrologActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrologActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrologActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        
    }//GEN-LAST:event_txtSearchKeyTyped

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    autocompleteSearchText(true);
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void jlSearchValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSearchValueChanged
       if(jlSearch.getSelectedValue() == null){
           return;
        }else if(jlSearch.getSelectedValue().equals("No Result Found!")||jlSearch.getSelectedValue().equals("")){
           return;
        }else{
          showSearchResult(jlSearch.getSelectedValue()) ;
       }
    }//GEN-LAST:event_jlSearchValueChanged
    
    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_txtSearchActionPerformed

    private void jlSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlSearchFocusLost
        
    }//GEN-LAST:event_jlSearchFocusLost

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        spSearchResult.setVisible(false);
    }//GEN-LAST:event_txtSearchFocusLost

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
       
        showSearchResult(txtSearch.getText());
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
       
    }//GEN-LAST:event_btnSearchMouseClicked
    
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
            ans = tableData.AllCountryConfirmCasesTableData(confirmCaseList);
            if (ans!=null) {
                int i=0;
                for(String[] test :tableData.AllCountryConfirmCasesTableData(confirmCaseList) ){
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
    
    private void setCountryWeeklyCaseTable(List<Country> confirmCaseList,SimpleDateFormat dateFormat){         
        String[] table1Columns = tableData.getAllWeeksStartNEndDateHeader(confirmCaseList,weekNYearFormat);
        String[][] rowData = tableData.weeklyNMonthlyCasesForCountriesTabledata(confirmCaseList, dateFormat);
        DefaultTableModel model = new DefaultTableModel(rowData, table1Columns) {
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
    
    private void setCountryMonthlyCaseTable(List<Country> confirmCaseList,SimpleDateFormat dateFormat){         
        String[] table1Columns = tableData.getAllMonthHeader(confirmCaseList,monthNYearFormat);
        String[][] rowData = tableData.weeklyNMonthlyCasesForCountriesTabledata(confirmCaseList, dateFormat);
        DefaultTableModel model = new DefaultTableModel(rowData, table1Columns) {
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
    
    private void setCountryDeathRecoverHLTable(List<Country> deathList, List<Country> recoveredList){         
        String[] table1Columns = tableData.deathNRecoverHeader();
        String[][] rowData = tableData.deathNRecoverTableData(deathList, recoveredList);
        DefaultTableModel model = new DefaultTableModel(rowData, table1Columns) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
        tblstatistic.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblstatistic.setModel(model);
        
       
    }
    
    private void autocompleteSearchText(boolean errmsg){
        String countryName = txtSearch.getText();
        if (countryName.equals("") || countryName.replace(" ", "").equals("")|| countryName.equals("Country")) {
            if (errmsg) {
                JOptionPane.showMessageDialog(null, "You must enter a country name\nbefore doing the search!", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
            
            
            spSearchResult.setVisible(false);
            return;
        }
        List<Country> countryList = Task1.searchCountry(confirmCaseList, countryName);
        if (countryList == null || countryList.isEmpty()) {
            if (errmsg) {
                JOptionPane.showMessageDialog(null, "Country not found", "Error Message", JOptionPane.ERROR_MESSAGE);
            }else{
                spSearchResult.setVisible(true);
                jlSearch.setModel(new javax.swing.AbstractListModel<String>() {
                    String[] strings = {"No Result Found!"};
                    
                    public int getSize() {
                        return strings.length;
                    }
                    
                    public String getElementAt(int i) {
                        return strings[i];
                    }
                });
            }
            return;
        }
        spSearchResult.setVisible(true);
        jlSearch.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return countryList.size();
            }
            
            public String getElementAt(int i) {
                return countryList.get(i).getCountryName();
            }
        });
        
    }
    
    private void showSearchResult(String countryName){
        if(countryName==null){
            return;
        }
        try {
            String[] columnData = tableData.saerchTableHeader();
            //recoveredList.stream().map(p->p.getCountryName()).forEach(System.out::println);
            String[][] rowData = tableData.saerchTableData(confirmCaseList, deathList, recoveredList, countryName);
            DefaultTableModel model = new DefaultTableModel(rowData, columnData) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
            tblstatistic.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
            tblstatistic.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Country not found", "Error Message", JOptionPane.ERROR_MESSAGE);
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
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbTask1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> jlSearch;
    private javax.swing.JScrollPane spSearchResult;
    private javax.swing.JTable tblstatistic;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}