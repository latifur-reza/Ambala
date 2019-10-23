/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.admin;

import inn.ambala.admin.model.CompanyInfoModel;
import inn.ambala.admin.model.LaundryModel;
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
public class LaundryCrud {
    DefaultTableModel model = null;

    public LaundryCrud() {
    }
    
    
    public LaundryCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    
    
    public static int addLaundry(LaundryModel laundryModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into laundry(laundrycode,laundrytype,clothtype,price) values(?,?,?,?)");
            
            pst.setInt(1, laundryModel.getLaundryCode());
            pst.setString(2, laundryModel.getLaundryType());
            pst.setString(3, laundryModel.getClothType());
            pst.setInt(4, laundryModel.getPrice());
            
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
    
    public void showAllLaundry(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from laundry order by laundrycode");
            
            rs = pst.executeQuery();
            
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("laundrycode"),rs.getString("laundrytype"),rs.getString("clothtype"),rs.getInt("price")});
                
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
    
    public static int updateLaundry(LaundryModel laundryModel,int laundryCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update laundry set laundrycode = ?, laundrytype = ?, clothtype = ?, price = ? where laundrycode = ?");
            
            pst.setInt(1, laundryModel.getLaundryCode());
            pst.setString(2, laundryModel.getLaundryType());
            pst.setString(3, laundryModel.getClothType());
            pst.setInt(4, laundryModel.getPrice());
            
            pst.setInt(5, laundryCode);
            
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
    
    public static int deleteLaundry(int laundryCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from laundry where laundrycode = ?");
            
            pst.setInt(1, laundryCode);
            
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
    
    public static LaundryModel getSingleItem(int laundryCode){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        LaundryModel laundryModel = new LaundryModel();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from laundry where laundrycode = ?");
            pst.setInt(1, laundryCode);
            rs = pst.executeQuery();
            
            while(rs.next()){
                laundryModel.setLaundryCode(rs.getInt("laundrycode"));
                laundryModel.setLaundryType(rs.getString("laundrytype"));
                laundryModel.setClothType(rs.getString("clothtype"));
                laundryModel.setPrice(rs.getInt("price"));
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
        return laundryModel;
    }
}
