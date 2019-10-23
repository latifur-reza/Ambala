/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.adminforms.reports;

import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.dashboard.userforms.model.UsersRoomInfoModel;
import inn.ambala.databaseconnection.userforms.addforms.LaundryAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.OthersAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.RestaurantAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.ServicesMiniCrud;
import inn.ambala.databaseconnection.userforms.forms.UserInfoCrud;
import inn.ambala.databaseconnection.userforms.forms.UserRoomInfoCrud;
import inn.ambala.reports.controller.GuestDueJR;
import inn.ambala.resources.Resources;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class GuestDueReport extends javax.swing.JFrame {

    private DefaultTableModel model = null;
    private Date from;
    private Date to;
    /**
     * Creates new form GuestDueReport
     */
    public GuestDueReport() {
        initComponents();
        initExtraComponents();
    }
    
    public GuestDueReport(Date from, Date to) {
        this.from = from;
        this.to = to;
        initComponents();
        initExtraComponents();
    }
    
    private void initExtraComponents(){
        ImageIcon key = new ImageIcon(Resources.getPath() + "icon-01.jpg");
        Image image = key.getImage();
        setIconImage(image);
        setTitle("Ambala Inn");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        model = (DefaultTableModel) dataTable.getModel();
        refreshTable();
    }
    
    public void refreshTable(){
        showTable();
    }
    
    private void showTable(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.setRowCount(0);
        ArrayList<UserInfoModel> allUserInfo = UserInfoCrud.getAllUserInfo();
        ArrayList<UsersRoomInfoModel> allUserRoomInfo = UserRoomInfoCrud.getAllUserRoomInfo();
        for(UsersRoomInfoModel urim : allUserRoomInfo){
            if(urim.getStatus().equals("CheckedIn")){
                for(UserInfoModel uim : allUserInfo){
                    if(urim.getMrNo().equals(uim.getMrNo())){
                        String mrNo = urim.getMrNo();
                        UsersRoomInfoModel usersRoomInfoModel = UserRoomInfoCrud.getSingleUserRoomInfo(mrNo);
                        int laundryBill = LaundryAddCrud.getSingleLaundryTotal(mrNo);
                        int restaurantBill = RestaurantAddCrud.getSingleRestaurantTotal(mrNo);
                        int othersBill = OthersAddCrud.getSingleOthersTotal(mrNo);
                        int paidBill = ServicesMiniCrud.getSingleServiceTotal(mrNo);
                        
                        Date arrivalDate = usersRoomInfoModel.getArrivalDate();
                        String arrivalStr = sdf.format(arrivalDate);
                        Date today = new Date();
                        String todayStr = sdf.format(today);
                        long days = 1;
                        try{
                            Date arrival = sdf.parse(arrivalStr);
                            Date now = sdf.parse(todayStr);
                            long difference = now.getTime() - arrival.getTime();
                            days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                            if(days==0){
                                days = 1;
                            }
                        }catch(Exception e){

                        }
                        
                        int roomRent = (int) (days*usersRoomInfoModel.getRoomTarrif());
                        int total = roomRent + laundryBill + restaurantBill +othersBill;
                        total = total - usersRoomInfoModel.getDiscountCash();
                        total = total - (usersRoomInfoModel.getRoomTarrif()*usersRoomInfoModel.getDiscountPercent())/100;
                        total = (int) (total + (total*.05));
                        total = (int) (total + (total*.15));
                        
                        model.addRow(new Object[]{urim.getMrNo(),urim.getRoomNo(),uim.getName(),sdf.format(urim.getArrivalDate()),total-paidBill});
                        
                    }
                }
            }
        }
    }
    
    private void printButtonPressed(){
        try{
            new GuestDueJR().setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Print Error");
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

        dueLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dueLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        dueLabel.setText("Guest Due");

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MR No", "Room No", "Guest Name", "Arrival Date", "Due"
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

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(printButton)
                                .addGap(299, 299, 299))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dueLabel)
                                .addGap(281, 281, 281))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dueLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        printButtonPressed();
    }//GEN-LAST:event_printButtonActionPerformed

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
            java.util.logging.Logger.getLogger(GuestDueReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuestDueReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuestDueReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuestDueReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuestDueReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel dueLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printButton;
    // End of variables declaration//GEN-END:variables
}
