/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.reports.controller;

import inn.ambala.admin.model.RoomDetailsModel;
import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.dashboard.userforms.model.UsersRoomInfoModel;
import inn.ambala.databaseconnection.admin.RoomDetailsCrud;
import inn.ambala.databaseconnection.userforms.addforms.LaundryAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.OthersAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.RestaurantAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.ServicesMiniCrud;
import inn.ambala.databaseconnection.userforms.forms.UserInfoCrud;
import inn.ambala.databaseconnection.userforms.forms.UserRoomInfoCrud;
import inn.ambala.resources.ReportSource;
import inn.ambala.resources.Resources;
import java.awt.BorderLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author REZA
 */
public class GuestDueJR extends javax.swing.JFrame {

    private JPanel contentPanel;
    /**
     * Creates new form GuestDueJR
     */
    public GuestDueJR() {
        initComponents();
        initExtraComponents();
        readyReport();
    }
    
    private void initExtraComponents(){
        ImageIcon key = new ImageIcon(Resources.getPath() + "icon-01.jpg");
        Image image = key.getImage();
        setIconImage(image);
        setTitle("Ambala Inn");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPanel);
    }
    private void readyReport(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<UserInfoModel> allUserInfo = UserInfoCrud.getAllUserInfo();
        ArrayList<UsersRoomInfoModel> allUserRoomInfo = UserRoomInfoCrud.getAllUserRoomInfo();
        try{
            int totalDues = 0;
            List<Map<String,?>> dataSource = new ArrayList<Map<String,?>>();
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

                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("mrNo", urim.getMrNo());
                            map.put("roomNo", String.valueOf(urim.getRoomNo()));
                            map.put("name", uim.getName());
                            map.put("arrival", sdf.format(urim.getArrivalDate()));
                            int dueBill = total-paidBill;
                            totalDues += dueBill;
                            map.put("dues", String.valueOf(dueBill));
                            dataSource.add(map);

                        }
                    }
                }
            }
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("totalDues","Total Dues : " + String.valueOf(totalDues));
            dataSource.add(map);
            
            JRDataSource jrdataSource = new JRBeanCollectionDataSource(dataSource);
            String sourceName = ReportSource.getPath() + "GuestDue.jrxml";
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fileReport = JasperFillManager.fillReport(report, null,jrdataSource);
            this.getContentPane().add(new JRViewer(fileReport));
            this.pack();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Printer Error!");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GuestDueJR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuestDueJR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuestDueJR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuestDueJR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuestDueJR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
