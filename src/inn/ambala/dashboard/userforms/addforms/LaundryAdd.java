/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.addforms;

import inn.ambala.dashboard.userforms.Laundry;
import inn.ambala.dashboard.userforms.addforms.*;
import inn.ambala.dashboard.userforms.Restaurant;
import inn.ambala.databaseconnection.admin.LaundryCrud;
import inn.ambala.databaseconnection.admin.RestaurantCrud;
import inn.ambala.databaseconnection.userforms.addforms.LaundryAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.RestaurantAddCrud;
import inn.ambala.keypicker.KeyGenerator;
import inn.ambala.resources.Resources;
import inn.ambala.user.model.LaundryModel;
import inn.ambala.user.model.RestaurantModel;
import java.awt.Image;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author REZA
 */
public class LaundryAdd extends javax.swing.JFrame {

    /**
     * Creates new form LaundryAdd
     */
    public LaundryAdd() {
        initComponents();
        initExtraComponents();
    }
    
    public LaundryAdd(int roomNo, String mrNo) {
        initComponents();
        initExtraComponents();
        roomNoTF.setText(roomNo+"");
        mrNoTF.setText(mrNo);
    }
    
    private void initExtraComponents() {
        ImageIcon key = new ImageIcon(Resources.getPath() + "icon-01.jpg");
        Image image = key.getImage();
        setIconImage(image);
        setTitle("Ambala Inn");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        roomNoTF.setEditable(false);
        mrNoTF.setEditable(false);
        roomNoTF.setEnabled(false);
        mrNoTF.setEnabled(false);
    }
    
    private void addButtonPressed(){
        quantityTFAction();
        if(roomNoTF.getText().equals("") || mrNoTF.getText().equals("") || laundryCodeTF.getText().equals("") || laundryTypeTF.getText().equals("") || priceTF.getText().equals("") || quantityTF.getText().equals("") || totalTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Fill All Details");
        }
        else{
            try{
                int roomNo = Integer.valueOf(roomNoTF.getText());
                String mrNo =  mrNoTF.getText();
                int laundryCode = Integer.valueOf(laundryCodeTF.getText());
                String laundryType = laundryTypeTF.getText();
                String clothType = clothTypeTF.getText();
                int price = Integer.valueOf(priceTF.getText());
                int quantity = Integer.valueOf(quantityTF.getText());
                int total = Integer.valueOf(totalTF.getText());
                Date date = new Date();
                String status = "ordered";
                LaundryModel laundryModel = new LaundryModel(mrNo, roomNo, laundryCode, laundryType, clothType, price, quantity, total, date, status);
                LaundryAddCrud.addLaundry(laundryModel);
                Laundry.refreshTable(mrNo,status);
                dispose();
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Enter Correct Information");
            }
        }
        
    }
    
    
    private void addAnotherButtonPressed(){
        quantityTFAction();
        if(roomNoTF.getText().equals("") || mrNoTF.getText().equals("") || laundryCodeTF.getText().equals("") || laundryTypeTF.getText().equals("") || priceTF.getText().equals("") || quantityTF.getText().equals("") || totalTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Fill All Details");
        }
        else{
            try{
                int roomNo = Integer.valueOf(roomNoTF.getText());
                String mrNo =  mrNoTF.getText();
                int laundryCode = Integer.valueOf(laundryCodeTF.getText());
                String laundryType = laundryTypeTF.getText();
                String clothType = clothTypeTF.getText();
                int price = Integer.valueOf(priceTF.getText());
                int quantity = Integer.valueOf(quantityTF.getText());
                int total = Integer.valueOf(totalTF.getText());
                Date date = new Date();
                String status = "ordered";
                LaundryModel laundryModel = new LaundryModel(mrNo, roomNo, laundryCode, laundryType, clothType, price, quantity, total, date, status);
                LaundryAddCrud.addLaundry(laundryModel);

                laundryCodeTF.setText("");
                laundryTypeTF.setText("");
                clothTypeTF.setText("");
                priceTF.setText("");
                quantityTF.setText("");
                totalTF.setText("");

                Laundry.refreshTable(mrNo,status);

                JOptionPane.showMessageDialog(null, "Added Successfully");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Enter Correct Information");
            }
        }
    }
    
    private void quantityTFAction(){
        int quantity = Integer.valueOf(quantityTF.getText());
        int price = Integer.valueOf(priceTF.getText());
        int total = quantity * price;
        totalTF.setText(total+"");
    }
    
    private void laundryCodeTFAction(){
        inn.ambala.admin.model.LaundryModel laundryModel = LaundryCrud.getSingleItem(Integer.valueOf(laundryCodeTF.getText()));
        laundryTypeTF.setText(laundryModel.getLaundryType());
        clothTypeTF.setText(laundryModel.getClothType());
        priceTF.setText(laundryModel.getPrice()+"");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        othersLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        roomNoTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        mrNoTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        laundryCodeTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        laundryTypeTF = new javax.swing.JTextField();
        addAnotherButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        quantityTF = new javax.swing.JTextField();
        priceTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        totalTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        clothTypeTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        othersLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        othersLabel.setText("Laundry");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Room No :");

        roomNoTF.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        roomNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomNoTFActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("MR No :");

        mrNoTF.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        mrNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mrNoTFActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Laundry Code :");

        laundryCodeTF.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        laundryCodeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laundryCodeTFActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Laundry Type :");

        laundryTypeTF.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        laundryTypeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laundryTypeTFActionPerformed(evt);
            }
        });

        addAnotherButton.setBackground(new java.awt.Color(236, 0, 138));
        addAnotherButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        addAnotherButton.setForeground(new java.awt.Color(255, 255, 255));
        addAnotherButton.setText("Add & Another");
        addAnotherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAnotherButtonActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(59, 180, 74));
        addButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        quantityTF.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        quantityTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTFActionPerformed(evt);
            }
        });

        priceTF.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        priceTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTFActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Price :");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Quantity :");

        totalTF.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        totalTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTFActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Total :");

        clothTypeTF.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        clothTypeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clothTypeTFActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Cloth Type :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel7))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mrNoTF)
                            .addComponent(roomNoTF)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel12)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clothTypeTF)
                            .addComponent(laundryTypeTF)
                            .addComponent(laundryCodeTF)
                            .addComponent(priceTF)
                            .addComponent(quantityTF)
                            .addComponent(totalTF, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addAnotherButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(othersLabel)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(othersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mrNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laundryCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laundryTypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clothTypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(addAnotherButton))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roomNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomNoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomNoTFActionPerformed

    private void mrNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mrNoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mrNoTFActionPerformed

    private void laundryCodeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laundryCodeTFActionPerformed
        laundryCodeTFAction();
    }//GEN-LAST:event_laundryCodeTFActionPerformed

    private void laundryTypeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laundryTypeTFActionPerformed
        
    }//GEN-LAST:event_laundryTypeTFActionPerformed

    private void addAnotherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAnotherButtonActionPerformed
        addAnotherButtonPressed();
    }//GEN-LAST:event_addAnotherButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addButtonPressed();
    }//GEN-LAST:event_addButtonActionPerformed

    private void quantityTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTFActionPerformed
        quantityTFAction();
    }//GEN-LAST:event_quantityTFActionPerformed

    private void priceTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTFActionPerformed

    private void totalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTFActionPerformed
        quantityTFAction();
    }//GEN-LAST:event_totalTFActionPerformed

    private void clothTypeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clothTypeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clothTypeTFActionPerformed

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
            java.util.logging.Logger.getLogger(LaundryAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaundryAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaundryAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaundryAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaundryAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAnotherButton;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField clothTypeTF;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField laundryCodeTF;
    private javax.swing.JTextField laundryTypeTF;
    private javax.swing.JTextField mrNoTF;
    private javax.swing.JLabel othersLabel;
    private javax.swing.JTextField priceTF;
    private javax.swing.JTextField quantityTF;
    private javax.swing.JTextField roomNoTF;
    private javax.swing.JTextField totalTF;
    // End of variables declaration//GEN-END:variables
}
