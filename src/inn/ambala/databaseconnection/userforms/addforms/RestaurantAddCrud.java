/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.addforms;

import inn.ambala.databaseconnection.DatabaseConnection;
import inn.ambala.user.model.RestaurantModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class RestaurantAddCrud {
    DefaultTableModel model = null;

    public RestaurantAddCrud() {
    }
    
    public RestaurantAddCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    public static int addRestaurants(RestaurantModel restaurantModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            Date sqlDate = new Date(restaurantModel.getDate().getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into restaurantdetails(mrno,roomno,itemcode,itemname,price,quantity,total,date,status, foodtype) values(?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, restaurantModel.getMrNo());
            pst.setInt(2, restaurantModel.getRoomNo());
            pst.setInt(3, restaurantModel.getItemCode());
            pst.setString(4, restaurantModel.getItemName());
            pst.setInt(5, restaurantModel.getPrice());
            pst.setInt(6, restaurantModel.getQuantity());
            pst.setInt(7, restaurantModel.getTotal());
            pst.setDate(8, sqlDate);
            pst.setString(9, restaurantModel.getStatus());
            pst.setString(10, restaurantModel.getFoodType());
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
    
    public void showAllRestaurants(String mrNo, String status){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from restaurantdetails where mrno = ? and status = ?");
            pst.setString(1, mrNo);
            pst.setString(2, status);
            rs = pst.executeQuery();
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("itemcode"),rs.getString("itemname"),rs.getInt("price"),rs.getInt("quantity"),rs.getInt("total")});
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
    
    public void showAllRestaurantsForPrint(String mrNo, String status){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from restaurantdetails where mrno = ? and status = ?");
            pst.setString(1, mrNo);
            pst.setString(2, status);
            rs = pst.executeQuery();
            model.setRowCount(0);
            model.addRow(new Object[]{"Item Code","Item Name","Price","Quantity","Total"});
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("itemcode"),rs.getString("itemname"),rs.getInt("price"),rs.getInt("quantity"),rs.getInt("total")});
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
    
    public void showAllRestaurantsByMr(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from restaurantdetails where mrno = ?");
            pst.setString(1, mrNo);
            rs = pst.executeQuery();
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("itemcode"),rs.getString("itemname"),rs.getInt("price"),rs.getInt("quantity"),rs.getInt("total")});
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
    
    public static int deleteRestaurants(String mrNo,int itemCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from restaurantdetails where mrno=? and itemcode = ?");
            
            pst.setString(1, mrNo);
            pst.setInt(2, itemCode);
            
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
    
    public void showAllOrders(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from restaurantdetails where status = ?");
            pst.setString(1, "ordered");
            rs = pst.executeQuery();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("mrno"),rs.getInt("roomno"),rs.getInt("itemcode"),rs.getString("itemname"),rs.getString("foodtype"),rs.getInt("quantity"),rs.getInt("total"),rs.getString("status")});
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
    
    public static int deliveryOrders(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update restaurantdetails set status=? where mrno = ?");
            
            pst.setString(1, "deliveried");
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
    
    public static int getSingleRestaurantTotal(String mrNos){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int total = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select sum(total) from restaurantdetails where mrno = ?");
            pst.setString(1, mrNos);
            rs = pst.executeQuery();
            
            while(rs.next()){
                total = rs.getInt("sum(total)");
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
        return total;
    }
    
    public static int deleteUsingMR(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from restaurantdetails where mrno=?");
            
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
