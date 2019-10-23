/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.forms;

import inn.ambala.dashboard.userforms.model.UserOthersInfoModel;
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
public class UserOthersInfoCrud {
    DefaultTableModel model = null;

    public UserOthersInfoCrud() {
    }
    
    public UserOthersInfoCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    public static int addUserOthersInfo(UserOthersInfoModel userOthersInfoModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into userothersinfo(mrno,roomno,registrationby,localcompanyname,localcompanyaddress,probablestay,totalbillcollected,whereissued,noofguest) values(?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, userOthersInfoModel.getMrNo());
            pst.setInt(2, userOthersInfoModel.getRoomNo());
            pst.setString(3, userOthersInfoModel.getRegistrationMadeBy());
            pst.setString(4, userOthersInfoModel.getLocalCompany());
            pst.setString(5, userOthersInfoModel.getLocalCompanyAddress());
            pst.setString(6, userOthersInfoModel.getProbableStay());
            pst.setInt(7, userOthersInfoModel.getTotalBillCollected());
            pst.setString(8, userOthersInfoModel.getWhereIssued());
            pst.setInt(9, userOthersInfoModel.getNoOfGuest());
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
    
    public static ArrayList<UserOthersInfoModel> getAllUserOthersInfo(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<UserOthersInfoModel> allUserOthersInfo = new ArrayList<UserOthersInfoModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from userothersinfo");
            
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                int roomNo = rs.getInt("roomno");
                String registrationBy = rs.getString("registrationby");
                String localcompanyname = rs.getString("localcompanyname");
                String localcompanyaddress = rs.getString("localcompanyaddress");
                String probablestay = rs.getString("probablestay");
                int totalbillcollected = rs.getInt("totalbillcollected");
                String whereIssued = rs.getString("whereissued");
                int noOfGuest = rs.getInt("noofguest");
                
                UserOthersInfoModel userOthersInfoModel = new UserOthersInfoModel(mrNo, roomNo, registrationBy, localcompanyname, localcompanyaddress, probablestay, totalbillcollected, whereIssued, noOfGuest);
                allUserOthersInfo.add(userOthersInfoModel);
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
        return allUserOthersInfo;
    }
    
    public static UserOthersInfoModel getSingleUserOthersInfo(String mrNum){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserOthersInfoModel userOthersInfoModel = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from userothersinfo where mrno = ?");
            pst.setString(1, mrNum);
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                int roomNo = rs.getInt("roomno");
                String registrationBy = rs.getString("registrationby");
                String localcompanyname = rs.getString("localcompanyname");
                String localcompanyaddress = rs.getString("localcompanyaddress");
                String probablestay = rs.getString("probablestay");
                int totalbillcollected = rs.getInt("totalbillcollected");
                String whereIssued = rs.getString("whereissued");
                int noOfGuest = rs.getInt("noofguest");
                
                userOthersInfoModel = new UserOthersInfoModel(mrNo, roomNo, registrationBy, localcompanyname, localcompanyaddress, probablestay, totalbillcollected, whereIssued, noOfGuest);
                
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
        return userOthersInfoModel;
    }
    
    public static int updateUserOthersInfoRoomNo(String mrNo,int newRoomNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update userothersinfo set roomno = ? where mrno = ?");
            
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
    
    public static int deleteUsingMR(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from userothersinfo where mrno=?");
            
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
