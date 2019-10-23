/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.forms;

import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class UserInfoCrud {
    DefaultTableModel model = null;

    public UserInfoCrud() {
    }
    
    public UserInfoCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    public static int addUserInfo(UserInfoModel userInfoModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            Date dateOfBirth = new Date(userInfoModel.getDateOfBirth().getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into userinfo(mrno,name,address,companyname,profession,nationality,passportno,dateofbirth,email,phone) values(?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, userInfoModel.getMrNo());
            pst.setString(2, userInfoModel.getName());
            pst.setString(3, userInfoModel.getAddress());
            pst.setString(4, userInfoModel.getCompanyName());
            pst.setString(5, userInfoModel.getProfession());
            pst.setString(6, userInfoModel.getNationality());
            pst.setString(7, userInfoModel.getPassportNo());
            pst.setDate(8, dateOfBirth);
            pst.setString(9, userInfoModel.getEmail());
            pst.setString(10, userInfoModel.getPhone());
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
    
    public static ArrayList<UserInfoModel> getAllUserInfo(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<UserInfoModel> allUserInfo = new ArrayList<UserInfoModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from userinfo");
            
            rs = pst.executeQuery();
            while(rs.next()){
                String mrNo = rs.getString("mrno");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String companyName = rs.getString("companyname");
                String profession = rs.getString("profession");
                String nationality = rs.getString("nationality");
                String passportno = rs.getString("passportno");
                Date dateOfBirth = rs.getDate("dateofbirth");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                
                UserInfoModel userInfoModel = new UserInfoModel(mrNo, name, address, companyName, profession, nationality, passportno, dateOfBirth, email, phone);
                allUserInfo.add(userInfoModel);
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
        return allUserInfo;
    }
    
    public static UserInfoModel getUserInfoByMr(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserInfoModel userInfoModel = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from userinfo where mrno = ?");
            pst.setString(1, mrNo);
            rs = pst.executeQuery();
            while(rs.next()){
                mrNo = rs.getString("mrno");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String companyName = rs.getString("companyname");
                String profession = rs.getString("profession");
                String nationality = rs.getString("nationality");
                String passportno = rs.getString("passportno");
                Date dateOfBirth = rs.getDate("dateofbirth");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                
                userInfoModel = new UserInfoModel(mrNo, name, address, companyName, profession, nationality, passportno, dateOfBirth, email, phone);
                
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
        return userInfoModel;
    }
    
    public static int deleteUsingMR(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from userinfo where mrno=?");
            
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
