/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.admin;

import inn.ambala.admin.model.RoomRentModel;
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
public class RoomRentCrud {
    
    public static int addRoomRent(RoomRentModel roomRentModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into roomrent(roomtype,roomrent) values(?,?)");
            
            pst.setString(1, roomRentModel.getRoomType());
            pst.setInt(2, roomRentModel.getRoomRent());
            
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
    
    public static ArrayList<RoomRentModel> showAllRoomRents(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<RoomRentModel> allRents = new ArrayList<RoomRentModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from roomrent order by roomid");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                int roomid = rs.getInt("roomid");
                String roomType = rs.getString("roomType");
                int roomRent = rs.getInt("roomrent");
                RoomRentModel newRent = new RoomRentModel(roomid, roomType, roomRent);
                allRents.add(newRent);
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
        return allRents;
    }
    
    public static int updateRoomRent(RoomRentModel roomRentModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update roomrent set roomrent = ? where roomtype = ?");
            
            pst.setInt(1, roomRentModel.getRoomRent());
            pst.setString(2, roomRentModel.getRoomType());
            
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
    
    public static int deleteRoomRent(String roomType){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from roomrent where roomtype=?");
            
            pst.setString(1, roomType);
            
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
