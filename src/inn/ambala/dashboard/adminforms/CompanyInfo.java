/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.adminforms;

import inn.ambala.admin.model.CompanyInfoModel;
import inn.ambala.dashboard.adminforms.addforms.CompanyInfoAdd;
import inn.ambala.dashboard.adminforms.addforms.CompanyInfoDeposite;
import inn.ambala.dashboard.adminforms.addforms.CompanyInfoUpdate;
import inn.ambala.databaseconnection.admin.CompanyInfoCrud;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class CompanyInfo extends javax.swing.JPanel {

    private static DefaultTableModel model = null;

    /**
     * Creates new form CompanyInfo
     */
    public CompanyInfo() {
        initComponents();
        initExtraComponents();
    }

    private void initExtraComponents() {
        model = (DefaultTableModel) dataTable.getModel();
        refreshTable();
    }

    public static void refreshTable() {
        new CompanyInfoCrud(model).showAllCompanyInfo();
        totalLabel.setText("Total Company : " + model.getRowCount());
    }

    private void addNewButtonPressed() {
        new CompanyInfoAdd().setVisible(true);
    }

    private void updateButtonPressed() {

        int result = 0;
        int rowNo = dataTable.getSelectedRow();
        if (rowNo != -1) {
            int companyid = 0;
            int companyCode = Integer.valueOf(dataTable.getModel().getValueAt(rowNo, 0).toString());
            String companyName = dataTable.getModel().getValueAt(rowNo, 1).toString();
            String contactPerson = dataTable.getModel().getValueAt(rowNo, 2).toString();
            String phone = dataTable.getModel().getValueAt(rowNo, 3).toString();
            String email = dataTable.getModel().getValueAt(rowNo, 4).toString();
            String address = dataTable.getModel().getValueAt(rowNo, 5).toString();
            String mobile = dataTable.getModel().getValueAt(rowNo, 6).toString();
            int balance = Integer.valueOf(dataTable.getModel().getValueAt(rowNo, 7).toString());

            CompanyInfoModel companyInfoModel = new CompanyInfoModel(companyid, companyCode, companyName, contactPerson, phone, email, address, mobile, balance);

            new CompanyInfoUpdate(companyInfoModel).setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "For Update Select Any Row");
        }
    }

    private void deleteButtonPressed() {
        int result = 0;
        int rowNo = dataTable.getSelectedRow();
        if (rowNo != -1) {
            int companyCode = Integer.valueOf(dataTable.getModel().getValueAt(rowNo, 0).toString());
            int balance = Integer.valueOf(dataTable.getModel().getValueAt(rowNo, 7).toString());
            int option = JOptionPane.showConfirmDialog(null, "Do You Really Want to Delete?", "Delete", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                if(balance == 0){
                    result = CompanyInfoCrud.deleteCompanyInfo(companyCode);
                }else{
                    JOptionPane.showMessageDialog(null, "Company Balance is not 0");
                }
                refreshTable();
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Deleted Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Not Successfully Deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "For Delete Select Any Row");
        }

        refreshTable();
    }
    
    private void depositeButtonPressed(){
        int result = 0;
        int rowNo = dataTable.getSelectedRow();
        if (rowNo != -1) {
            int companyid = 0;
            int companyCode = Integer.valueOf(dataTable.getModel().getValueAt(rowNo, 0).toString());
            String companyName = dataTable.getModel().getValueAt(rowNo, 1).toString();
            String contactPerson = dataTable.getModel().getValueAt(rowNo, 2).toString();
            String phone = dataTable.getModel().getValueAt(rowNo, 3).toString();
            String email = dataTable.getModel().getValueAt(rowNo, 4).toString();
            String address = dataTable.getModel().getValueAt(rowNo, 5).toString();
            String mobile = dataTable.getModel().getValueAt(rowNo, 6).toString();
            int balance = Integer.valueOf(dataTable.getModel().getValueAt(rowNo, 7).toString());

            CompanyInfoModel companyInfoModel = new CompanyInfoModel(companyid, companyCode, companyName, contactPerson, phone, email, address, mobile, balance);

            new CompanyInfoDeposite(companyInfoModel).setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "For Deposite Select Any Row");
        }
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
        dataTable = new javax.swing.JTable();
        companyInfoLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        addNewButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        depositeButton = new javax.swing.JButton();

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Company Code", "Company Name", "Contact Person", "Phone", "Email", "Address", "Mobile", "Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dataTable);

        companyInfoLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        companyInfoLabel.setText("Company Info");

        totalLabel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        totalLabel.setText("Total Company : 100");

        addNewButton.setBackground(new java.awt.Color(59, 180, 74));
        addNewButton.setForeground(new java.awt.Color(255, 255, 255));
        addNewButton.setText("Add New");
        addNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(236, 0, 138));
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(0, 173, 238));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        depositeButton.setBackground(new java.awt.Color(59, 180, 74));
        depositeButton.setForeground(new java.awt.Color(255, 255, 255));
        depositeButton.setText("Deposite");
        depositeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(depositeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addNewButton))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(companyInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(totalLabel)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(companyInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addNewButton)
                    .addComponent(updateButton)
                    .addComponent(deleteButton)
                    .addComponent(depositeButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewButtonActionPerformed
        addNewButtonPressed();
    }//GEN-LAST:event_addNewButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateButtonPressed();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteButtonPressed();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void depositeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositeButtonActionPerformed
        depositeButtonPressed();
    }//GEN-LAST:event_depositeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewButton;
    private javax.swing.JLabel companyInfoLabel;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton depositeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel totalLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
