/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.admin;

import inn.ambala.admin.model.RestaurantModel;
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
public class RestaurantCrud {
    DefaultTableModel model = null;

    public RestaurantCrud() {
    }
    
    
    public RestaurantCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    
    
    public static int addItem(RestaurantModel restaurantModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into restaurant(itemcode,itemname,foodtype,itemprice) values(?,?,?,?)");
            
            pst.setInt(1, restaurantModel.getItemCode());
            pst.setString(2, restaurantModel.getItemName());
            pst.setString(3, restaurantModel.getFoodType());
            pst.setInt(4, restaurantModel.getItemPrice());
            
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
    
    public void showAllItems(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from restaurant order by itemcode");
            rs = pst.executeQuery();
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("itemcode"),rs.getString("itemname"),rs.getString("foodtype"),rs.getInt("itemprice")}); 
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
    
    public static int updateItem(RestaurantModel restaurantModel,int itemCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update restaurant set itemcode = ?, itemname = ?, foodtype = ?, itemprice = ? where itemcode = ?");
            
            pst.setInt(1, restaurantModel.getItemCode());
            pst.setString(2, restaurantModel.getItemName());
            pst.setString(3, restaurantModel.getFoodType());
            pst.setInt(4, restaurantModel.getItemPrice());
            
            pst.setInt(5, itemCode);
            
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
    
    public static int deleteItem(int itemCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from restaurant where itemcode=?");
            
            pst.setInt(1, itemCode);
            
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
    
    public static RestaurantModel getSingleItem(int itemCode){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        RestaurantModel restaurantModel = new RestaurantModel();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from restaurant where itemcode = ?");
            pst.setInt(1, itemCode);
            rs = pst.executeQuery();
            
            while(rs.next()){
                restaurantModel.setItemCode(rs.getInt("itemcode"));
                restaurantModel.setItemName(rs.getString("itemname"));
                restaurantModel.setFoodType(rs.getString("foodtype"));
                restaurantModel.setItemPrice(rs.getInt("itemprice"));
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
        return restaurantModel;
    }
}
