/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.admin;

import inn.ambala.admin.model.CompanyInfoModel;
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
public class CompanyInfoCrud {
    DefaultTableModel model = null;

    public CompanyInfoCrud() {
    }
    
    
    public CompanyInfoCrud(DefaultTableModel model) {
        this.model = model;
    }
    
    
    
    public static int addCompanyInfo(CompanyInfoModel companyInfoModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into companyinfo(companycode,companyname,contactperson,phone,email,address,mobile,balance) values(?,?,?,?,?,?,?,?)");
            
            pst.setInt(1, companyInfoModel.getCompanyCode());
            pst.setString(2, companyInfoModel.getCompanyName());
            pst.setString(3, companyInfoModel.getContactPerson());
            pst.setString(4, companyInfoModel.getPhone());
            pst.setString(5, companyInfoModel.getEmail());
            pst.setString(6, companyInfoModel.getAddress());
            pst.setString(7, companyInfoModel.getMobile());
            pst.setInt(8, companyInfoModel.getBalance());
            
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
    
    public void showAllCompanyInfo(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from companyinfo order by companycode");
            
            rs = pst.executeQuery();
            
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("companycode"),rs.getString("companyname"),rs.getString("contactperson"),rs.getString("phone"),rs.getString("email"),rs.getString("address"),rs.getString("mobile"),rs.getInt("balance")});
                
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
    
    
    public static ArrayList<CompanyInfoModel> getAllCompanyInfo(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<CompanyInfoModel> allCompany = new ArrayList<CompanyInfoModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from companyinfo order by companycode");
            
            rs = pst.executeQuery();
            while(rs.next()){
                int companyId = rs.getInt("companyid");
                int companyCode = rs.getInt("companycode");
                String companyName = rs.getString("companyname");
                String contactPerson = rs.getString("contactperson");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String mobile = rs.getString("mobile");
                int balance = rs.getInt("balance");
                
                CompanyInfoModel newCompanyInfoModel = new CompanyInfoModel(companyId, companyCode, companyName, contactPerson, phone, email, address, mobile, balance);
                allCompany.add(newCompanyInfoModel);
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
        return allCompany;
    }
    
    public static CompanyInfoModel getAllCompanyInfoByCC(int code){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        CompanyInfoModel cim = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from companyinfo where companycode = ?");
            pst.setInt(1, code);
            rs = pst.executeQuery();
            while(rs.next()){
                int companyId = rs.getInt("companyid");
                int companyCode = rs.getInt("companycode");
                String companyName = rs.getString("companyname");
                String contactPerson = rs.getString("contactperson");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String mobile = rs.getString("mobile");
                int balance = rs.getInt("balance");
                
                cim = new CompanyInfoModel(companyId, companyCode, companyName, contactPerson, phone, email, address, mobile, balance);
                
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
        return cim;
    }
    
    public static CompanyInfoModel getAllCompanyInfoByName(String comName){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        CompanyInfoModel cim = null;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from companyinfo where companyname = ?");
            pst.setString(1, comName);
            rs = pst.executeQuery();
            while(rs.next()){
                int companyId = rs.getInt("companyid");
                int companyCode = rs.getInt("companycode");
                String companyName = rs.getString("companyname");
                String contactPerson = rs.getString("contactperson");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String mobile = rs.getString("mobile");
                int balance = rs.getInt("balance");
                
                cim = new CompanyInfoModel(companyId, companyCode, companyName, contactPerson, phone, email, address, mobile, balance);
                
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
        return cim;
    }
    
    public static int updateCompanyInfo(CompanyInfoModel companyInfoModel,int companyCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update companyinfo set companycode = ?, companyname = ?, contactperson = ?, phone = ?, email = ?, address = ?, mobile = ?, balance = ? where companycode = ?");
            
            pst.setInt(1, companyInfoModel.getCompanyCode());
            pst.setString(2, companyInfoModel.getCompanyName());
            pst.setString(3, companyInfoModel.getContactPerson());
            pst.setString(4, companyInfoModel.getPhone());
            pst.setString(5, companyInfoModel.getEmail());
            pst.setString(6, companyInfoModel.getAddress());
            pst.setString(7, companyInfoModel.getMobile());
            pst.setInt(8, companyInfoModel.getBalance());
            pst.setInt(9, companyCode);
            
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
    
    public static int updateCompanyBalance(String companyName,int cash){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update companyinfo set balance = balance + ? where companyname = ?");
            
            pst.setInt(1, cash);
            pst.setString(2, companyName);
            
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
    
    public static int updateCompanyBalanceByCode(int companyCode,int cash){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("update companyinfo set balance = balance + ? where companycode = ?");
            
            pst.setInt(1, cash);
            pst.setInt(2, companyCode);
            
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
    
    public static int deleteCompanyInfo(int companyCode){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from companyinfo where companycode=?");
            
            pst.setInt(1, companyCode);
            
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
