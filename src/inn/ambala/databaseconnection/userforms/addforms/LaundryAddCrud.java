/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.addforms;

import inn.ambala.databaseconnection.DatabaseConnection;
import inn.ambala.user.model.LaundryModel;
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
public class LaundryAddCrud {
    DefaultTableModel model = null;

    public LaundryAddCrud() {
    }
    
    public LaundryAddCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    public static int addLaundry(LaundryModel laundryModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            Date sqlDate = new Date(laundryModel.getDate().getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into laundrydetails(mrno,roomno,laundrycode,laundrytype,clothtype,price,quantity,total,date,status) values(?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, laundryModel.getMrNo());
            pst.setInt(2, laundryModel.getRoomNo());
            pst.setInt(3, laundryModel.getLaundryCode());
            pst.setString(4, laundryModel.getLaundryType());
            pst.setString(5, laundryModel.getClothType());
            pst.setInt(6, laundryModel.getPrice());
            pst.setInt(7, laundryModel.getQuantity());
            pst.setInt(8, laundryModel.getTotal());
            pst.setDate(9, sqlDate);
            pst.setString(10, laundryModel.getStatus());
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
    
    public void showAllLaundry(String mrNo, String status){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from laundrydetails where mrno = ? and status = ?");
            pst.setString(1, mrNo);
            pst.setString(2, status);
            rs = pst.executeQuery();
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("laundrycode"),rs.getString("laundrytype"),rs.getString("clothtype"),rs.getInt("price"),rs.getInt("quantity"),rs.getInt("total")});
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
    
    public void showAllLaundryForPrint(String mrNo, String status){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from laundrydetails where mrno = ? and status = ?");
            pst.setString(1, mrNo);
            pst.setString(2, status);
            rs = pst.executeQuery();
            model.setRowCount(0);
            model.addRow(new Object[]{"Code","Laundry Type","Cloth Type","Price","Quantity","Total"});
        
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("laundrycode"),rs.getString("laundrytype"),rs.getString("clothtype"),rs.getInt("price"),rs.getInt("quantity"),rs.getInt("total")});
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
    
    public void showAllLaundryByMr(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from laundrydetails where mrno = ?");
            pst.setString(1, mrNo);
            
            rs = pst.executeQuery();
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("laundrycode"),rs.getString("laundrytype"),rs.getString("clothtype"),rs.getInt("price"),rs.getInt("quantity"),rs.getInt("total")});
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
    
    public static int deleteLaundry(String mrNo,int laundryCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from laundrydetails where mrno=? and laundrycode = ?");
            
            pst.setString(1, mrNo);
            pst.setInt(2, laundryCode);
            
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
            pst = connection.prepareStatement("select * from laundrydetails where status = ?");
            pst.setString(1, "ordered");
            rs = pst.executeQuery();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("mrno"),rs.getInt("roomno"),rs.getInt("laundrycode"),rs.getString("laundrytype"),rs.getString("clothtype"),rs.getInt("quantity"),rs.getInt("total"),rs.getString("status")});
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
            
            pst = connection.prepareStatement("update laundrydetails set status=? where mrno = ?");
            
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
    
    public static int getSingleLaundryTotal(String mrNos){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int total = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select sum(total) from laundrydetails where mrno = ?");
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
            
            pst = connection.prepareStatement("delete from laundrydetails where mrno=?");
            
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
