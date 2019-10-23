/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.addforms;

import inn.ambala.databaseconnection.DatabaseConnection;
import inn.ambala.user.model.OthersModel;
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
public class OthersAddCrud {
    DefaultTableModel model = null;

    public OthersAddCrud() {
    }
    
    public OthersAddCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    public static int addOthers(OthersModel othersModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            Date sqlDate = new Date(othersModel.getDate().getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into othersdetails(othersno,mrno,roomno,description,amount,date) values(?,?,?,?,?,?)");
            
            pst.setString(1, othersModel.getOthersNo());
            pst.setString(2, othersModel.getMrNo());
            pst.setInt(3, othersModel.getRoomNo());
            pst.setString(4, othersModel.getDescription());
            pst.setInt(5, othersModel.getAmount());
            pst.setDate(6, sqlDate);
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
    
    public void showAllOthers(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from othersdetails where mrno = ?");
            pst.setString(1, mrNo);
            rs = pst.executeQuery();
            model.setRowCount(0);
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("othersno"),rs.getString("description"),rs.getInt("amount")});
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
    
    public void showAllOthersForPrint(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select * from othersdetails where mrno = ?");
            pst.setString(1, mrNo);
            rs = pst.executeQuery();
            model.setRowCount(0);
            model.addRow(new Object[]{"Others No","Description","Amount"});
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("othersno"),rs.getString("description"),rs.getInt("amount")});
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
    
    public static int deleteOthers(String othersNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from othersdetails where othersno=?");
            
            pst.setString(1, othersNo);
            
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
    
    public static int getSingleOthersTotal(String mrNos){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int total = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select sum(amount) from othersdetails where mrno = ?");
            pst.setString(1, mrNos);
            rs = pst.executeQuery();
            
            while(rs.next()){
                total = rs.getInt("sum(amount)");
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
            
            pst = connection.prepareStatement("delete from othersdetails where mrno=?");
            
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
