/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms;

import inn.ambala.dashboard.userforms.addforms.RestaurantAdd;
import inn.ambala.dashboard.userforms.extraforms.RestaurantMenus;
import inn.ambala.dashboard.userforms.extraforms.RestaurantOrders;
import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.dashboard.userforms.model.UsersRoomInfoModel;
import inn.ambala.dashboard.userforms.print.RestaurantPrint;
import inn.ambala.databaseconnection.userforms.addforms.RestaurantAddCrud;
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
public class Restaurant extends javax.swing.JPanel {

    private static DefaultTableModel dataTableModel = null;
    private static DefaultTableModel model = null;
    private static String mrNo = "xxxx";
    /**
     * Creates new form Restaurant
     */
    public Restaurant() {
        initComponents();
        initExtraComponents();
    }
    
    private void initExtraComponents() {
        comboBox();
        model = (DefaultTableModel) dataTable.getModel();
        dataTableModel = (DefaultTableModel) checkInTable.getModel();
        refreshTable(mrNo,"ordered");
    }
    
    public static void refreshTable(String mrNo, String status) {
        new RestaurantAddCrud(model).showAllRestaurants(mrNo, status);
        
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
    
    private void comboBox() {
        ArrayList<String> foodTypes = new ArrayList<String>();
        foodTypes.add("Breakfast");
        foodTypes.add("Lunch");
        foodTypes.add("Dinner");
        foodTypes.add("Snakes");
        foodTypes.add("Drinks");
        foodTypes.add("Others");

        foodTypeCB.removeAllItems();
        for (String types : foodTypes) {
            foodTypeCB.addItem(types);
        }
        foodTypeCB.setSelectedIndex(0);
    }
    
    private void addNewButtonPressed(){
        if(roomNoTF.getText().equals("") || mrNoTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Room No and MR No");
        }
        else{
            try{
                RestaurantAdd restaurantAdd = new RestaurantAdd(Integer.valueOf(roomNoTF.getText()), mrNoTF.getText(), (String) foodTypeCB.getSelectedItem());
                restaurantAdd.setVisible(true);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Enter Correct Information");
            }
        }
        
    }
    
    private void deleteButtonPressed(){
        int result = 0;
        int rowNo = dataTable.getSelectedRow();
        String mrNos = mrNoTF.getText();
        if (rowNo != -1) {
            int itemCode = Integer.valueOf(dataTable.getModel().getValueAt(rowNo, 0).toString());
            int option = JOptionPane.showConfirmDialog(null, "Do You Really Want to Delete?", "Delete", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                result = RestaurantAddCrud.deleteRestaurants(mrNos,itemCode);

                refreshTable(mrNoTF.getText(),"ordered");
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Deleted Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Not Successfully Deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "For Delete Select Any Row");
        }

        refreshTable(mrNoTF.getText(),"ordered");
    }
    
    private void doneButtonPressed(){
        dueTFAction();
        try{
            String mrNos = mrNoTF.getText();
            int roomNo = Integer.valueOf(roomNoTF.getText());
            int totalBill = Integer.valueOf(totalTF.getText());
            int pay = Integer.valueOf(payTF.getText());
            Date date = new Date();
            String services = "restaurant";
            String status = "ordered";

            ServicesMiniModel servicesMiniModel = new ServicesMiniModel(mrNos, totalBill, pay, date, status, services);

            ServicesMiniCrud.addServicesMiNi(servicesMiniModel);

            mrNoTF.setText("");
            roomNoTF.setText("");
            totalTF.setText("");
            payTF.setText("");
            dueTF.setText("");
            model.setRowCount(0);
            foodTypeCB.setSelectedIndex(0);
            new RestaurantPrint(mrNos, roomNo, totalBill, pay, totalBill-pay);
            
            JOptionPane.showMessageDialog(null, "Added Successfully");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Enter Correct Information");
        }
    }
    
    private void totalTFAction(){
        int total = 0;
        for(int i = 0; i < dataTable.getRowCount(); i++){
            total = total +(int) dataTable.getModel().getValueAt(i, 4);
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
    
    private void menuButtonPressed(){
        new RestaurantMenus().setVisible(true);
    }
    
    private void currentOrdersButtonPressed(){
        new RestaurantOrders().setVisible(true);
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
        deleteButton = new javax.swing.JButton();
        currentOrdersButton = new javax.swing.JButton();
        addNewButton = new javax.swing.JButton();
        menuButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        totalTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        roomNoTF = new javax.swing.JTextField();
        mrNoTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dueTF = new javax.swing.JTextField();
        payTF = new javax.swing.JTextField();
        doneButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        foodTypeCB = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        checkInTable = new javax.swing.JTable();

        guestRegistrationFormLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        guestRegistrationFormLabel.setText("Restaurants");

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Price", "Quantity", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dataTable);

        deleteButton.setBackground(new java.awt.Color(0, 173, 238));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        currentOrdersButton.setBackground(new java.awt.Color(236, 0, 138));
        currentOrdersButton.setForeground(new java.awt.Color(255, 255, 255));
        currentOrdersButton.setText("Cueent Orders");
        currentOrdersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentOrdersButtonActionPerformed(evt);
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

        menuButton.setBackground(new java.awt.Color(59, 180, 74));
        menuButton.setForeground(new java.awt.Color(255, 255, 255));
        menuButton.setText("Menus");
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Total");

        totalTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTFActionPerformed(evt);
            }
        });

        jLabel2.setText("Room No.");

        mrNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mrNoTFActionPerformed(evt);
            }
        });

        jLabel3.setText("MR No.");

        jLabel4.setText("Pay");

        jLabel5.setText("Due");

        dueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueTFActionPerformed(evt);
            }
        });

        payTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payTFActionPerformed(evt);
            }
        });

        doneButton.setBackground(new java.awt.Color(59, 180, 74));
        doneButton.setForeground(new java.awt.Color(255, 255, 255));
        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Food Type");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(317, 480, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(totalTF, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(menuButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(currentOrdersButton)
                                .addGap(93, 93, 93)
                                .addComponent(addNewButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(doneButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(payTF, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(dueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mrNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(foodTypeCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(guestRegistrationFormLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(guestRegistrationFormLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(mrNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(foodTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doneButton)
                    .addComponent(addNewButton)
                    .addComponent(currentOrdersButton)
                    .addComponent(deleteButton)
                    .addComponent(menuButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteButtonPressed();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void currentOrdersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentOrdersButtonActionPerformed
        currentOrdersButtonPressed();
    }//GEN-LAST:event_currentOrdersButtonActionPerformed

    private void addNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewButtonActionPerformed
        addNewButtonPressed();
    }//GEN-LAST:event_addNewButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        doneButtonPressed();
    }//GEN-LAST:event_doneButtonActionPerformed

    private void totalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_totalTFActionPerformed

    private void payTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payTFActionPerformed
        payTFAction();
    }//GEN-LAST:event_payTFActionPerformed

    private void dueTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueTFActionPerformed
        dueTFAction();
    }//GEN-LAST:event_dueTFActionPerformed

    private void mrNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mrNoTFActionPerformed
        refreshTable(mrNoTF.getText(),"ordered");
    }//GEN-LAST:event_mrNoTFActionPerformed

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        menuButtonPressed();
    }//GEN-LAST:event_menuButtonActionPerformed

    private void checkInTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkInTableMouseClicked
        dataTableMouseClickedAction();
    }//GEN-LAST:event_checkInTableMouseClicked

    private void checkInTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkInTableKeyReleased
        dataTableMouseClickedAction();
    }//GEN-LAST:event_checkInTableKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewButton;
    private javax.swing.JTable checkInTable;
    private javax.swing.JButton currentOrdersButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JTextField dueTF;
    private javax.swing.JComboBox<String> foodTypeCB;
    private javax.swing.JLabel guestRegistrationFormLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton menuButton;
    private javax.swing.JTextField mrNoTF;
    private javax.swing.JTextField payTF;
    private javax.swing.JTextField roomNoTF;
    private javax.swing.JTextField totalTF;
    // End of variables declaration//GEN-END:variables
}
