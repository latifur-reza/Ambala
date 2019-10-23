/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.forms;

import inn.ambala.dashboard.userforms.model.UsersRoomInfoModel;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class UserRoomInfoCrud {
    DefaultTableModel model = null;

    public UserRoomInfoCrud() {
    }
    
    public UserRoomInfoCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    public static int addUserRoomInfo(UsersRoomInfoModel usersRoomInfoModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            //Date arrivalDate = new Date(usersRoomInfoModel.getArrivalDate().getTime());
            Timestamp arrivalDate = new Timestamp(usersRoomInfoModel.getArrivalDate().getTime());
            Date departureDate = new Date(usersRoomInfoModel.getDepartureDate().getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into userroominfo(mrno,roomno,roomtype,arrivaldate,departuredate,discountpercent,discountcash,roomtarrif,roombill,status) values(?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, usersRoomInfoModel.getMrNo());
            pst.setInt(2, usersRoomInfoModel.getRoomNo());
            pst.setString(3, usersRoomInfoModel.getRoomType());
            pst.setTimestamp(4, arrivalDate);
            pst.setDate(5, departureDate);
            pst.setInt(6, usersRoomInfoModel.getDiscountPercent());
            pst.setInt(7, usersRoomInfoModel.getDiscountCash());
            pst.setInt(8, usersRoomInfoModel.getRoomTarrif());
            pst.setInt(9, usersRoomInfoModel.getRoomBill());
            pst.setString(10, usersRoomInfoModel.getStatus());
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
    
    public static ArrayList<UsersRoomInfoModel> getAllUserRoomInfo(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<UsersRoomInfoModel> allUserRoomInfo = new ArrayList<UsersRoomInfoModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from userroominfo");
            
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                int roomNo = rs.getInt("roomno");
                String roomtype = rs.getString("roomtype");
                Timestamp arrivaldate = rs.getTimestamp("arrivaldate");
                Date departuredate = rs.getDate("departuredate");
                int discountpercent = rs.getInt("discountpercent");
                int discountcash = rs.getInt("discountcash");
                int roomtarrif = rs.getInt("roomtarrif");
                int roombill = rs.getInt("roombill");
                String status = rs.getString("status");
                
                UsersRoomInfoModel usersRoomInfoModel = new UsersRoomInfoModel(mrNo, roomNo, roomtype, arrivaldate, departuredate, discountpercent, discountcash, roomtarrif, roombill, status);
                allUserRoomInfo.add(usersRoomInfoModel);
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
        return allUserRoomInfo;
    }
    
    public static ArrayList<UsersRoomInfoModel> getAllUserRoomInfoByDate(java.util.Date from, java.util.Date to){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<UsersRoomInfoModel> allUserRoomInfo = new ArrayList<UsersRoomInfoModel>();
        try{
            Date sqlFrom = new Date(from.getTime());
            Date sqlTo = new Date(to.getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from userroominfo where arrivaldate >= ? and arrivaldate <= ?");
            pst.setDate(1, sqlFrom);
            pst.setDate(2, sqlTo);
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                int roomNo = rs.getInt("roomno");
                String roomtype = rs.getString("roomtype");
                Timestamp arrivaldate = rs.getTimestamp("arrivaldate");
                Date departuredate = rs.getDate("departuredate");
                int discountpercent = rs.getInt("discountpercent");
                int discountcash = rs.getInt("discountcash");
                int roomtarrif = rs.getInt("roomtarrif");
                int roombill = rs.getInt("roombill");
                String status = rs.getString("status");
                
                UsersRoomInfoModel usersRoomInfoModel = new UsersRoomInfoModel(mrNo, roomNo, roomtype, arrivaldate, departuredate, discountpercent, discountcash, roomtarrif, roombill, status);
                allUserRoomInfo.add(usersRoomInfoModel);
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
        return allUserRoomInfo;
    }
    
    public static int updateUserRoomInfoRoomNo(String mrNo,int newRoomNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update userroominfo set roomno = ? where mrno = ?");
            
            pst.setInt(1, newRoomNo);
            pst.setString(2, mrNo);
            
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
    
    public static int updateUserRoomInfoStatus(String mrNo,String status){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update userroominfo set status = ? where mrno = ?");
            
            pst.setString(1, status);
            pst.setString(2, mrNo);
            
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
    
    public static UsersRoomInfoModel getSingleUserRoomInfo(String mrNoGet){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        UsersRoomInfoModel singleUserRoomInfo = new UsersRoomInfoModel();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from userroominfo where mrno = ?");
            pst.setString(1, mrNoGet);
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                int roomNo = rs.getInt("roomno");
                String roomtype = rs.getString("roomtype");
                Timestamp arrivaldate = rs.getTimestamp("arrivaldate");
                Date departuredate = rs.getDate("departuredate");
                int discountpercent = rs.getInt("discountpercent");
                int discountcash = rs.getInt("discountcash");
                int roomtarrif = rs.getInt("roomtarrif");
                int roombill = rs.getInt("roombill");
                String status = rs.getString("status");
                
                singleUserRoomInfo = new UsersRoomInfoModel(mrNo, roomNo, roomtype, arrivaldate, departuredate, discountpercent, discountcash, roomtarrif, roombill, status);
                
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
        return singleUserRoomInfo;
    }
    
    public static int deleteUsingMR(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from userroominfo where mrno=?");
            
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
