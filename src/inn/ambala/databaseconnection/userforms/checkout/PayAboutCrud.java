/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.checkout;

import inn.ambala.dashboard.userforms.checkout.model.PayAbout;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author REZA
 */
public class PayAboutCrud {
    public static int addPayAbout(PayAbout payAbout){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into payabout(mrno,roomrent,restaurant,laundry,others,late,discountcash,discountpercent,total,servicecharge,vat) values(?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, payAbout.getMrNo());
            pst.setInt(2, payAbout.getRoomRent());
            pst.setInt(3, payAbout.getRestaurant());
            pst.setInt(4, payAbout.getLaundry());
            pst.setInt(5, payAbout.getOthers());
            pst.setInt(6, payAbout.getLate());
            pst.setInt(7, payAbout.getDiscountTK());
            pst.setInt(8, payAbout.getDiscountPercent());
            pst.setInt(9, payAbout.getTotal());
            pst.setInt(10, payAbout.getServiceCharge());
            pst.setInt(11, payAbout.getVat());
            
            result = pst.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database CRUD Error!");
        }finally{
            try {
                pst.close();
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database CRUD Error!");
            }
        }
        return result;
    }
    
    public static ArrayList<PayAbout> getPaymentAboutDetails(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<PayAbout> allPaymentDetails = new ArrayList<PayAbout>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from payabout");
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                int roomRent = rs.getInt("roomrent");
                int restaurant = rs.getInt("restaurant");
                int laundry = rs.getInt("laundry");
                int others = rs.getInt("others");
                int late = rs.getInt("late");
                int discountCash = rs.getInt("discountcash");
                int discountPercent = rs.getInt("discountpercent");
                int total = rs.getInt("total");
                int serviceCharge = rs.getInt("servicecharge");
                int vat = rs.getInt("vat");
                
                
                PayAbout paymentDetailsModel = new PayAbout(mrNo, roomRent, restaurant, laundry, others, late, discountCash, discountPercent, total, serviceCharge, vat);
                allPaymentDetails.add(paymentDetailsModel);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database CRUD Error!");
        }finally{
            try {
                pst.close();
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database CRUD Error!");
            }
        }
        return allPaymentDetails;
    }
    
    public static int deleteUsingMR(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from payabout where mrno=?");
            
            pst.setString(1, mrNo);
            
            result = pst.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database CRUD Error!");
        }finally{
            try {
                pst.close();
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database CRUD Error!");
            }
        }
        return result;
    }
}
