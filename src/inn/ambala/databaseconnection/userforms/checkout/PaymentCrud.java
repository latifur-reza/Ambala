/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.checkout;

import inn.ambala.dashboard.userforms.checkout.model.PayAbout;
import inn.ambala.dashboard.userforms.checkout.model.Payment;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author REZA
 */
public class PaymentCrud {
    public static int addPayment(Payment payment){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            java.sql.Date departure = new java.sql.Date(payment.getDepartureDate().getTime());
            
            pst = connection.prepareStatement("insert into payment(mrno,grandtotal,lessamount,netpayable,advance,pay,finaldue,paymentmethod,cardno,departuredate) values(?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, payment.getMrNo());
            pst.setInt(2, payment.getGrandTotal());
            pst.setInt(3, payment.getLessAmount());
            pst.setInt(4, payment.getNetPayable());
            pst.setInt(5, payment.getAdvance());
            pst.setInt(6, payment.getPay());
            pst.setInt(7, payment.getFinalDue());
            pst.setString(8, payment.getPaymentMethod());
            pst.setString(9, payment.getCardNo());
            pst.setDate(10, departure);
            
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
    
    public static ArrayList<Payment> getPaymentDetails(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<Payment> allPaymentDetails = new ArrayList<Payment>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from payment");
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                int grandTotal = rs.getInt("grandtotal");
                int lessAmount = rs.getInt("lessamount");
                int netPayable = rs.getInt("netpayable");
                int advance = rs.getInt("advance");
                int pay = rs.getInt("pay");
                int finalDue = rs.getInt("finaldue");
                String paymentMethod = rs.getString("paymentmethod");
                String cardNo = rs.getString("cardno");
                Date departureDate = rs.getDate("departuredate");
                
                Payment paymentDetailsModel = new Payment(mrNo, grandTotal, lessAmount, netPayable, advance, pay, finalDue, paymentMethod, cardNo, departureDate);
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
    
    public static ArrayList<Payment> getPaymentDetailsByDate(Date from , Date to){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<Payment> allPaymentDetails = new ArrayList<Payment>();
        try{
            java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
            java.sql.Date sqlto = new java.sql.Date(to.getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from payment where departuredate >= ? and departuredate <= ?");
            pst.setDate(1, sqlFrom);
            pst.setDate(2, sqlto);
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                int grandTotal = rs.getInt("grandtotal");
                int lessAmount = rs.getInt("lessamount");
                int netPayable = rs.getInt("netpayable");
                int advance = rs.getInt("advance");
                int pay = rs.getInt("pay");
                int finalDue = rs.getInt("finaldue");
                String paymentMethod = rs.getString("paymentmethod");
                String cardNo = rs.getString("cardno");
                Date departureDate = rs.getDate("departuredate");
                
                Payment paymentDetailsModel = new Payment(mrNo, grandTotal, lessAmount, netPayable, advance, pay, finalDue, paymentMethod, cardNo, departureDate);
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
            
            pst = connection.prepareStatement("delete from payment where mrno=?");
            
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
