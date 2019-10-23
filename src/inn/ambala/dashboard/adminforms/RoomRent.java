/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.adminforms;

import inn.ambala.admin.model.RoomRentModel;
import inn.ambala.databaseconnection.admin.RoomRentCrud;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class RoomRent extends javax.swing.JPanel {

    DefaultTableModel model = null;

    /**
     * Creates new form RoomRent
     */
    public RoomRent() {
        initComponents();
        initExtraComponents();
    }

    private void initExtraComponents() {
        comboBox();
        refreshTable();
    }

    private void refreshTable() {
        model = (DefaultTableModel) dataTable.getModel();
        ArrayList<RoomRentModel> roomRentModel = new ArrayList<>();
        roomRentModel = RoomRentCrud.showAllRoomRents();
        model.setRowCount(0);
        for (RoomRentModel roomRent : roomRentModel) {
            model.addRow(new Object[]{roomRent.getRoomType(), roomRent.getRoomRent()});
        }
        totalLabel.setText("Total : " + model.getRowCount());
    }

    private void comboBox() {
        ArrayList<String> roomTypes = new ArrayList<String>();
        roomTypes.add("Single");
        roomTypes.add("Double");
        roomTypes.add("Double+Extra Bed");

        roomTypeCB.removeAllItems();
        for (String types : roomTypes) {
            roomTypeCB.addItem(types);
        }
        roomTypeCB.setSelectedIndex(0);
    }

    private void addNewButtonPressed() {
        boolean flag = true;
        if (roomRentTF.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Fill the Field Carefully");
        } else {
            int roomId = 0; //database auto increment will handle it
            String roomType = (String) roomTypeCB.getSelectedItem();
            int roomRent = 0;
            try{
                roomRent = Integer.valueOf(roomRentTF.getText());
            }catch(Exception e){
                flag = false;
                JOptionPane.showMessageDialog(null, "Digits Only");
            }
            if(flag){
                RoomRentModel roomRentModel = new RoomRentModel(roomId, roomType, roomRent);
                int result = RoomRentCrud.addRoomRent(roomRentModel);

                if (result > 0) {
                    refreshTable();
                    roomRentTF.setText("");
                    roomTypeCB.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null, "Added Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Entry");
                }
            }
        }
        flag = true;
        refreshTable();
    }

    private void updateButtonPressed() {
        boolean flag = true;
        if (roomRentTF.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Fill the Field Carefully");
        } else {
            int roomId = 0; //database auto increment will handle it
            String roomType = (String) roomTypeCB.getSelectedItem();
            int roomRent = 0;
            try{
                roomRent = Integer.valueOf(roomRentTF.getText());
            }catch(Exception e){
                flag = false;
                JOptionPane.showMessageDialog(null, "Digits Only");
            }
            if(flag){
                RoomRentModel roomRentModel = new RoomRentModel(roomId, roomType, roomRent);
                int result = RoomRentCrud.updateRoomRent(roomRentModel);

                if (result > 0) {
                    refreshTable();
                    roomRentTF.setText("");
                    roomTypeCB.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Entry");
                }
            }
        }
        flag = true;
        refreshTable();
    }

    private void deleteButtonPressed() {
        int result = 0;
        int rowNo = dataTable.getSelectedRow();
        if (rowNo != -1) {
            String roomType = dataTable.getModel().getValueAt(rowNo, 0).toString();
            int option = JOptionPane.showConfirmDialog(null, "Do You Really Want to Delete?", "Delete", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                result = RoomRentCrud.deleteRoomRent(roomType);

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
        roomRentLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roomRentTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        roomTypeCB = new javax.swing.JComboBox<>();
        updateButton = new javax.swing.JButton();
        addNewButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room Type", "Room Rent"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dataTable);

        roomRentLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        roomRentLabel.setText("Room Rent");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("Room Type :");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("Room Rent :");

        updateButton.setBackground(new java.awt.Color(236, 0, 138));
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        addNewButton.setBackground(new java.awt.Color(59, 180, 74));
        addNewButton.setForeground(new java.awt.Color(255, 255, 255));
        addNewButton.setText("Add New");
        addNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(0, 173, 238));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        totalLabel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        totalLabel.setText("Total : 5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 332, Short.MAX_VALUE)
                .addComponent(roomRentLabel)
                .addGap(293, 293, 293))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteButton)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(roomTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(roomRentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addNewButton)
                .addGap(18, 18, 18)
                .addComponent(updateButton)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(totalLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roomRentLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(roomTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(roomRentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNewButton)
                    .addComponent(updateButton))
                .addGap(18, 18, 18)
                .addComponent(totalLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteButtonPressed();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateButtonPressed();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void addNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewButtonActionPerformed
        addNewButtonPressed();
    }//GEN-LAST:event_addNewButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel roomRentLabel;
    private javax.swing.JTextField roomRentTF;
    private javax.swing.JComboBox<String> roomTypeCB;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
