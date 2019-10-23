/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.conference;

import inn.ambala.dashboard.userforms.conference.model.BookingDetailsModel;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author REZA
 */
public class BookingDetailsCrud {
    
    public static int addBookingDetails(BookingDetailsModel bookingDetailsModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String from = sdf.format(bookingDetailsModel.getFrom());
            String to = sdf.format(bookingDetailsModel.getTo());
            java.sql.Date arrivalDate = new java.sql.Date(bookingDetailsModel.getArrivalDate().getTime());
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into conferencebookingdetails(voucharno,arrivaldate,company,address,area,areacode,reservation,reservefrom,reserveto,reason,status) values(?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, bookingDetailsModel.getVoucherNo());
            pst.setDate(2, arrivalDate);
            pst.setString(3, bookingDetailsModel.getCompany());
            pst.setString(4, bookingDetailsModel.getAddress());
            pst.setString(5, bookingDetailsModel.getArea());
            pst.setString(6, bookingDetailsModel.getAreaCode());
            pst.setString(7, bookingDetailsModel.getReservation());
            pst.setString(8, from);
            pst.setString(9, to);
            pst.setString(10, bookingDetailsModel.getReason());
            pst.setString(11, bookingDetailsModel.getStatus());
            
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
    
    public static ArrayList<BookingDetailsModel> getBookingDetails(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<BookingDetailsModel> allBookingDetails = new ArrayList<BookingDetailsModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from conferencebookingdetails");
            
            rs = pst.executeQuery();
            while(rs.next()){
                String voucherNo = rs.getString("voucharno");
                Date arrivalDate = rs.getDate("arrivaldate");
                String company = rs.getString("company");
                String address = rs.getString("address");
                String area = rs.getString("area");
                String areaCode = rs.getString("areacode");
                String reservation = rs.getString("reservation");
                String fromStr = rs.getString("reservefrom");
                Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fromStr);
                String toStr = rs.getString("reserveto");
                Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(toStr);
                String reason = rs.getString("reason");
                String status = rs.getString("status");
                
                BookingDetailsModel bookingDetailsModel = new BookingDetailsModel(voucherNo, arrivalDate, company, address, area, areaCode, reservation, from, to, reason, status);
                allBookingDetails.add(bookingDetailsModel);
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
        return allBookingDetails;
    }
    
    public static ArrayList<BookingDetailsModel> getBookingDetailsByDate(Date fromD, Date toD){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<BookingDetailsModel> allBookingDetails = new ArrayList<BookingDetailsModel>();
        try{
            java.sql.Date sqlFrom = new java.sql.Date(fromD.getTime());
            java.sql.Date sqlTo = new java.sql.Date(toD.getTime());
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from conferencebookingdetails where arrivaldate >= ? and arrivaldate <= ?");
            pst.setDate(1, sqlFrom);
            pst.setDate(2, sqlTo);
            rs = pst.executeQuery();
            while(rs.next()){
                String voucherNo = rs.getString("voucharno");
                Date arrivalDate = rs.getDate("arrivaldate");
                String company = rs.getString("company");
                String address = rs.getString("address");
                String area = rs.getString("area");
                String areaCode = rs.getString("areacode");
                String reservation = rs.getString("reservation");
                String fromStr = rs.getString("reservefrom");
                Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fromStr);
                String toStr = rs.getString("reserveto");
                Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(toStr);
                String reason = rs.getString("reason");
                String status = rs.getString("status");
                
                BookingDetailsModel bookingDetailsModel = new BookingDetailsModel(voucherNo, arrivalDate, company, address, area, areaCode, reservation, from, to, reason, status);
                allBookingDetails.add(bookingDetailsModel);
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
        return allBookingDetails;
    }
    
    public static int updateBookingDetails(String voucharNo , String status){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update conferencebookingdetails set status = ? where voucharno = ?");
            
            pst.setString(1, status);
            pst.setString(2, voucharNo);
            
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
            
            pst = connection.prepareStatement("delete from conferencebookingdetails where voucharno=?");
            
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
