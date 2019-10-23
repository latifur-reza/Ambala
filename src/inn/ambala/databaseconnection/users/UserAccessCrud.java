/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.users;

import inn.ambala.admin.model.UserModel;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class UserAccessCrud {
    
    DefaultTableModel model = null;
    
    public UserAccessCrud(){
    }
    
    public UserAccessCrud(DefaultTableModel model){
        this.model = model;
    }
    
    
    
    public static int addUser(UserModel userModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into users(username,password,email,department,permission) values(?,?,?,?,?)");
            
            pst.setString(1, userModel.getUserName());
            pst.setString(2, userModel.getPassword());
            pst.setString(3, userModel.getEmail());
            pst.setString(4, userModel.getDepartment());
            pst.setString(5, userModel.getPermission());
            
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
    
    
    public void showAllUsers(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from users order by userid");
            
            rs = pst.executeQuery();
            
            model.setRowCount(0);
            while(rs.next()){
                if(!rs.getString("permission").equals("admin")){
                    model.addRow(new Object[]{rs.getString("username"),rs.getString("email"),rs.getString("department"),rs.getString("permission")});
                }
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
    
    public static int updateUser(UserModel userModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update users set password = ?, email = ?, department = ? where username = ?");
            
            pst.setString(1, userModel.getPassword());
            pst.setString(2, userModel.getEmail());
            pst.setString(3, userModel.getDepartment());
            pst.setString(4, userModel.getUserName());
            
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
    
    public static int deleteUser(String userName){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from users where username=?");
            
            pst.setString(1, userName);
            
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
    
    public static int permitUser(String userName,String permission){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update users set permission = ? where username = ?");
            
            pst.setString(1, permission);
            pst.setString(2, userName);
            
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
    
    public static String checkUserPermission(String userName, String password){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String permission = "";
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select permission from users where username = ? and password = ?");
            
            pst.setString(1, userName);
            pst.setString(2, password);
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                permission = rs.getString("permission");
            }
            if(permission.equals("")){
                permission = "unregister";
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
        return permission;
    }
    
    
    public static int changeUserPassword(String userName,String oldPassword,String newPassword){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update users set password = ? where username = ? and password = ?");
            
            pst.setString(1, newPassword);
            pst.setString(2, userName);
            pst.setString(3, oldPassword);
            
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
