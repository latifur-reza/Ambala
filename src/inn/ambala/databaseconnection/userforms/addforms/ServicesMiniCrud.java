/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.addforms;

import inn.ambala.databaseconnection.DatabaseConnection;
import inn.ambala.user.model.ServicesMiniModel;
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
public class ServicesMiniCrud {
    DefaultTableModel model = null;

    public ServicesMiniCrud() {
    }
    
    public ServicesMiniCrud(DefaultTableModel model) {
        this.model = model;
    }
    public static int addServicesMiNi(ServicesMiniModel servicesMiniModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            Date sqlDate = new Date(servicesMiniModel.getDate().getTime());
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into servicesmini(mrno,totalbill,paybill,date,status,service) values(?,?,?,?,?,?)");
            
            pst.setString(1, servicesMiniModel.getMrNo());
            pst.setInt(2, servicesMiniModel.getTotalBill());
            pst.setInt(3, servicesMiniModel.getPayBill());
            pst.setDate(4, sqlDate);
            pst.setString(5, servicesMiniModel.getStatus());
            pst.setString(6, servicesMiniModel.getService());
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
    
    public static int getSingleServiceTotal(String mrNos){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int total = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            pst = connection.prepareStatement("select sum(paybill) from servicesmini where mrno = ?");
            pst.setString(1, mrNos);
            rs = pst.executeQuery();
            
            while(rs.next()){
                total = rs.getInt("sum(paybill)");
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
            
            pst = connection.prepareStatement("delete from servicesmini where mrno=?");
            
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
