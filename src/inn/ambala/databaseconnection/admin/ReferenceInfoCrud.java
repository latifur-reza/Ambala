/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.admin;

import inn.ambala.admin.model.ReferenceInfoModel;
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
public class ReferenceInfoCrud {
    DefaultTableModel model = null;

    public ReferenceInfoCrud() {
    }
    
    
    public ReferenceInfoCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    
    
    public static int addReference(ReferenceInfoModel referenceInfoModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into referenceinfo(referencecode,name,address,email,phone,mobile) values(?,?,?,?,?,?)");
            
            pst.setInt(1, referenceInfoModel.getReferenceCode());
            pst.setString(2, referenceInfoModel.getName());
            pst.setString(3, referenceInfoModel.getAddress());
            pst.setString(4, referenceInfoModel.getEmail());
            pst.setString(5, referenceInfoModel.getPhone());
            pst.setString(6, referenceInfoModel.getMobile());
            
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
    
    public void showAllReference(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from referenceinfo order by referencecode");
            
            rs = pst.executeQuery();
            
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("referencecode"),rs.getString("name"),rs.getString("address"),rs.getString("email"),rs.getString("phone"),rs.getString("mobile")});
                
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
    
    public static ArrayList<ReferenceInfoModel> getAllReference(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ReferenceInfoModel> allReference = new ArrayList<ReferenceInfoModel>();
        
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from referenceinfo order by referencecode");
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                int referenceCode = rs.getInt("referencecode");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String mobile = rs.getString("mobile");
                ReferenceInfoModel rim = new ReferenceInfoModel(0, referenceCode, name, address, email, phone, mobile);
                allReference.add(rim);
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
        return allReference;
    }
    
    public static int updateReference(ReferenceInfoModel referenceInfoModel,int referenceCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update referenceinfo set referencecode = ?, name = ?, address = ?, email = ?, phone = ?, mobile = ? where referencecode = ?");
            
            pst.setInt(1, referenceInfoModel.getReferenceCode());
            pst.setString(2, referenceInfoModel.getName());
            pst.setString(3, referenceInfoModel.getAddress());
            pst.setString(4, referenceInfoModel.getEmail());
            pst.setString(5, referenceInfoModel.getPhone());
            pst.setString(6, referenceInfoModel.getMobile());
            
            pst.setInt(7, referenceCode);
            
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
    
    public static int deleteReference(int referenceCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from referenceinfo where referencecode=?");
            
            pst.setInt(1, referenceCode);
            
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
