/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.conference;

import inn.ambala.dashboard.userforms.conference.model.PaymentDetailsModel;
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
public class PaymentDetailsCrud {
    
    
    public static int addPaymentDetails(PaymentDetailsModel paymentDetailsModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            java.sql.Date paymentDate = new java.sql.Date(paymentDetailsModel.getPaymentDate().getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into conferencepayment(voucharno,total,payment,paymentdate,due) values(?,?,?,?,?)");
            
            pst.setString(1, paymentDetailsModel.getVoucharNo());
            pst.setInt(2, paymentDetailsModel.getTotal());
            pst.setInt(3, paymentDetailsModel.getPayment());
            pst.setDate(4, paymentDate);
            pst.setInt(5, paymentDetailsModel.getDue());
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
    
    public static ArrayList<PaymentDetailsModel> getPaymentDetails(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<PaymentDetailsModel> allPaymentDetails = new ArrayList<PaymentDetailsModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from conferencepayment");
            
            rs = pst.executeQuery();
            while(rs.next()){
                String voucharNo = rs.getString("voucharno");
                int total = rs.getInt("total");
                int payment = rs.getInt("payment");
                Date paymentDate = rs.getDate("paymentdate");
                int due = rs.getInt("due");
                
                PaymentDetailsModel paymentDetailsModel = new PaymentDetailsModel(voucharNo, total, payment, paymentDate, due);
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
    
    public static int updatePaymentDetails(PaymentDetailsModel paymentDetailsModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            java.sql.Date paymentDate = new java.sql.Date(paymentDetailsModel.getPaymentDate().getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update conferencepayment set payment = ?,paymentdate = ?,due = ? where voucharno = ?");
            
            pst.setInt(1, paymentDetailsModel.getPayment());
            pst.setDate(2, paymentDate);
            pst.setInt(3, paymentDetailsModel.getDue());
            pst.setString(4, paymentDetailsModel.getVoucharNo());
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
    
    public static int deleteUsingMR(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from conferencepayment where voucharno=?");
            
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
