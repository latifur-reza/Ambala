/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms;

import inn.ambala.dashboard.userforms.addforms.OthersAdd;
import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.dashboard.userforms.model.UsersRoomInfoModel;
import inn.ambala.dashboard.userforms.print.OthersPrint;
import inn.ambala.databaseconnection.userforms.addforms.OthersAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.ServicesMiniCrud;
import inn.ambala.databaseconnection.userforms.forms.UserInfoCrud;
import inn.ambala.databaseconnection.userforms.forms.UserRoomInfoCrud;
import inn.ambala.user.model.ServicesMiniModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class Others extends javax.swing.JPanel {

    private static DefaultTableModel dataTableModel = null;
    private static DefaultTableModel model = null;
    private static String mrNo = "xxxx";
    /**
     * Creates new form Others
     */
    public Others() {
        initComponents();
        initExtraComponents();
    }
    
    private void initExtraComponents() {
        model = (DefaultTableModel) dataTable.getModel();
        dataTableModel = (DefaultTableModel) checkInTable.getModel();
        refreshTable(mrNo);
    }
    
    public static void refreshTable(String mr) {
        new OthersAddCrud(model).showAllOthers(mr);
        showCurrentCheckIns();
    }
    
    public static void showCurrentCheckIns(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dataTableModel.setRowCount(0);
        ArrayList<UserInfoModel> allUserInfo = UserInfoCrud.getAllUserInfo();
        ArrayList<UsersRoomInfoModel> allUserRoomInfo = UserRoomInfoCrud.getAllUserRoomInfo();
        for(UsersRoomInfoModel urim : allUserRoomInfo){
            if(urim.getStatus().equals("CheckedIn")){
                for(UserInfoModel uim : allUserInfo){
                    if(urim.getMrNo().equals(uim.getMrNo())){
                        dataTableModel.addRow(new Object[]{urim.getMrNo(),urim.getRoomNo(),uim.getName(),sdf.format(urim.getArrivalDate()),urim.getStatus(),""});
                        
                    }
                }
            }
        }
    }
    
    private void addNewButtonPressed(){
        if(roomNoTF.getText().equals("") || mrNoTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Room No and MR No");
        }
        else{
            try{
                OthersAdd othersAdd = new OthersAdd(Integer.valueOf(roomNoTF.getText()), mrNoTF.getText());
                othersAdd.setVisible(true);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Enter Correct Information");
            }
        }
        
    }
    
    private void doneButtonPressed(){
        dueTFAction();
        try{
            String mrNos = mrNoTF.getText();
            int roomNo = Integer.valueOf(roomNoTF.getText());
            int totalBill = Integer.valueOf(totalTF.getText());
            int pay = Integer.valueOf(payTF.getText());
            Date date = new Date();
            String services = "others";
            String status = "deliveried";

            ServicesMiniModel servicesMiniModel = new ServicesMiniModel(mrNos, totalBill, pay, date, status, services);

            ServicesMiniCrud.addServicesMiNi(servicesMiniModel);

            mrNoTF.setText("");
            roomNoTF.setText("");
            totalTF.setText("");
            payTF.setText("");
            dueTF.setText("");
            model.setRowCount(0);
            new OthersPrint(mrNos, roomNo, totalBill, pay, totalBill-pay);
            JOptionPane.showMessageDialog(null, "Added Successfully");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Enter Correct Information");
        }
    }
    
    private void deleteButtonPressed(){
        int result = 0;
        int rowNo = dataTable.getSelectedRow();
        if (rowNo != -1) {
            String othersNo = dataTable.getModel().getValueAt(rowNo, 0).toString();
            int option = JOptionPane.showConfirmDialog(null, "Do You Really Want to Delete?", "Delete", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                result = OthersAddCrud.deleteOthers(othersNo);

                refreshTable(mrNoTF.getText());
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Deleted Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Not Successfully Deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "For Delete Select Any Row");
        }

        refreshTable(mrNoTF.getText());
    }
    
    private void totalTFAction(){
        int total = 0;
        for(int i = 0; i < dataTable.getRowCount(); i++){
            total = total +(int) dataTable.getModel().getValueAt(i, 2);
        }
        totalTF.setText(total+"");
    }
    
    private void payTFAction(){
        totalTFAction();
        try{
            int total = Integer.valueOf(totalTF.getText());
            if(payTF.getText().equals("")){
                payTF.setText("0");
            }
            int pay = Integer.valueOf(payTF.getText());
            int due = total - pay;
            dueTF.setText(due+"");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Enter Correct Information");
        }
    }
    
    private void dueTFAction(){
        payTFAction();
    }
    
    private void dataTableMouseClickedAction(){
        int rowNo = checkInTable.getSelectedRow();
        mrNoTF.setText(checkInTable.getModel().getValueAt(rowNo, 0).toString());
        roomNoTF.setText(checkInTable.getModel().getValueAt(rowNo, 1).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guestRegistrationFormLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        roomNoTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mrNoTF = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        addNewButton = new javax.swing.JButton();
        totalTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        payTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dueTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        doneButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        checkInTable = new javax.swing.JTable();

        guestRegistrationFormLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        guestRegistrationFormLabel.setText("Others");

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Others No", "Description", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dataTable);

        jLabel2.setText("Room No.");

        jLabel3.setText("MR No.");

        mrNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mrNoTFActionPerformed(evt);
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

        addNewButton.setBackground(new java.awt.Color(59, 180, 74));
        addNewButton.setForeground(new java.awt.Color(255, 255, 255));
        addNewButton.setText("Add New");
        addNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewButtonActionPerformed(evt);
            }
        });

        totalTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTFActionPerformed(evt);
            }
        });

        jLabel1.setText("Total");

        payTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payTFActionPerformed(evt);
            }
        });

        jLabel4.setText("Pay");

        dueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueTFActionPerformed(evt);
            }
        });

        jLabel5.setText("Due");

        doneButton.setBackground(new java.awt.Color(59, 180, 74));
        doneButton.setForeground(new java.awt.Color(255, 255, 255));
        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        checkInTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MR No.", "Room No.", "Guest Name", "Arrival Date", "Room Status", "PABx Ext"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        checkInTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkInTableMouseClicked(evt);
            }
        });
        checkInTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                checkInTableKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(checkInTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mrNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(totalTF, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(payTF, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(dueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(addNewButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteButton)
                                .addGap(148, 148, 148)
                                .addComponent(doneButton))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(guestRegistrationFormLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(guestRegistrationFormLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(mrNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(payTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dueTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addNewButton)
                    .addComponent(deleteButton)
                    .addComponent(doneButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteButtonPressed();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewButtonActionPerformed
        addNewButtonPressed();
    }//GEN-LAST:event_addNewButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        doneButtonPressed();
    }//GEN-LAST:event_doneButtonActionPerformed

    private void mrNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mrNoTFActionPerformed
        refreshTable(mrNoTF.getText());
    }//GEN-LAST:event_mrNoTFActionPerformed

    private void checkInTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkInTableMouseClicked
        dataTableMouseClickedAction();
    }//GEN-LAST:event_checkInTableMouseClicked

    private void checkInTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkInTableKeyReleased
        dataTableMouseClickedAction();
    }//GEN-LAST:event_checkInTableKeyReleased

    private void totalTFActionPerformed(java.awt.event.ActionEvent evt){
        totalTFAction();
    }

    private void payTFActionPerformed(java.awt.event.ActionEvent evt){
        payTFAction();
    }

    private void dueTFActionPerformed(java.awt.event.ActionEvent evt){
        dueTFAction();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewButton;
    private javax.swing.JTable checkInTable;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JTextField dueTF;
    private javax.swing.JLabel guestRegistrationFormLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField mrNoTF;
    private javax.swing.JTextField payTF;
    private javax.swing.JTextField roomNoTF;
    private javax.swing.JTextField totalTF;
    // End of variables declaration//GEN-END:variables
}
