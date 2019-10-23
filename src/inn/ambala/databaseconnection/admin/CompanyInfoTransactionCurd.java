/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.admin;

import inn.ambala.admin.model.CompanyInfoTransactionModel;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author REZA
 */
public class CompanyInfoTransactionCurd {
    
    public static int addTransaction(CompanyInfoTransactionModel citm){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            Timestamp today = new Timestamp(citm.getDate().getTime());
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into companyinfotransaction(id,companycode,companyname,amount,status,reason,mrno,date) values(?,?,?,?,?,?,?,?)");
            
            pst.setString(1, citm.getId());
            pst.setInt(2, citm.getCompanyCode());
            pst.setString(3, citm.getCompanyName());
            pst.setInt(4, citm.getAmount());
            pst.setString(5, citm.getStatus());
            pst.setString(6, citm.getReason());
            pst.setString(7, citm.getMrNo());
            pst.setTimestamp(8, today);
            
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
    
    public static ArrayList<CompanyInfoTransactionModel> getAllTransaction(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<CompanyInfoTransactionModel> allTransaction = new ArrayList<CompanyInfoTransactionModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from companyinfotransaction");
            
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                int companyCode = rs.getInt("companycode");
                String companyName = rs.getString("companyname");
                int amount = rs.getInt("amount");
                String status = rs.getString("status");
                String reason = rs.getString("reason");
                String mrNo = rs.getString("mrno");
                Timestamp date = rs.getTimestamp("date");
                
                CompanyInfoTransactionModel newCitm = new CompanyInfoTransactionModel(id, companyCode, companyName, amount, status, reason, mrNo, date);
                allTransaction.add(newCitm);
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
        return allTransaction;
    }
    
    public static int deleteUsingMR(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from companyinfotransaction where mrno=?");
            
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
