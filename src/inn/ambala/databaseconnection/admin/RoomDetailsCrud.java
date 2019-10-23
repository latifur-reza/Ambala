/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.admin;

import inn.ambala.admin.model.RoomDetailsModel;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class RoomDetailsCrud {
    DefaultTableModel model = null;

    public RoomDetailsCrud() {
    }
    
    
    public RoomDetailsCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    
    
    public static int addRoom(RoomDetailsModel roomDetailsModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into roomdetails(roomno,floorno,roomtype,aircondition,status) values(?,?,?,?,?)");
            
            pst.setInt(1, roomDetailsModel.getRoomNo());
            pst.setInt(2, roomDetailsModel.getFloorNo());
            pst.setString(3, roomDetailsModel.getRoomType());
            pst.setString(4, roomDetailsModel.getAircondition());
            pst.setString(5, roomDetailsModel.getStatus());
            
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
    
    public void showAllRooms(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from roomdetails order by roomno");
            
            rs = pst.executeQuery();
            
            model.setRowCount(0);
            
            while(rs.next()){
                
                model.addRow(new Object[]{rs.getInt("roomno"),rs.getInt("floorno"),rs.getString("roomtype"),rs.getString("aircondition")});
                
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
    }
    
    public static ArrayList<RoomDetailsModel> getAllRooms(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<RoomDetailsModel> allRooms = new ArrayList<RoomDetailsModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from roomdetails order by roomno");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                RoomDetailsModel rdm = new RoomDetailsModel();
                rdm.setRoomNo(rs.getInt("roomno"));
                rdm.setFloorNo(rs.getInt("floorno"));
                rdm.setRoomType(rs.getString("roomtype"));
                rdm.setAircondition(rs.getString("aircondition"));
                rdm.setStatus(rs.getString("status"));
                allRooms.add(rdm);
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
        return allRooms;
    }
    
    public void showAllRoomsWithStatus(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from roomdetails order by roomno");
            
            rs = pst.executeQuery();
            
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("roomno"),rs.getInt("floorno"),rs.getString("roomtype"),rs.getString("aircondition"),rs.getString("status")});
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
    }
    
    public static int updateRoom(RoomDetailsModel roomDetailsModel,int roomNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update roomdetails set roomno = ?, floorno = ?, roomtype = ?, aircondition = ? where roomno = ?");
            
            pst.setInt(1, roomDetailsModel.getRoomNo());
            pst.setInt(2, roomDetailsModel.getFloorNo());
            pst.setString(3, roomDetailsModel.getRoomType());
            pst.setString(4, roomDetailsModel.getAircondition());
            pst.setInt(5, roomNo);
            
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
    
    public static int deleteRoom(int roomno){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from roomdetails where roomno=?");
            
            pst.setInt(1, roomno);
            
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
    
    public static int updateRoomStatus(int roomNo,String status){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update roomdetails set status = ? where roomno = ?");
            
            pst.setString(1, status);
            pst.setInt(2, roomNo);
            
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
    
    public void showCheckedOutRooms(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from roomdetails where status = ? order by roomno");
            pst.setString(1, "CheckedOut");
            rs = pst.executeQuery();
            
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("roomno")});
                
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
    }
    
    public static String getRoomStatus(int roomNo){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String status = "None";
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from roomdetails where roomno = ?");
            pst.setInt(1, roomNo);
            rs = pst.executeQuery();
            
            
            while(rs.next()){
                status = rs.getString("status");
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
        return status;
    }
}
