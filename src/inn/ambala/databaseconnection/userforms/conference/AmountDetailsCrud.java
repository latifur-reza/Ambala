/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection.userforms.conference;

import inn.ambala.dashboard.userforms.conference.model.AmountDetailsModel;
import inn.ambala.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author REZA
 */
public class AmountDetailsCrud {
    
    public static int addPaymentDetails(AmountDetailsModel amountDetailsModel){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("insert into conferenceamountdetails(voucharno,rent,foodcharges,projector,osp,videos,soundsystem,others,discount,total) values(?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, amountDetailsModel.getVoucharNo());
            pst.setInt(2, amountDetailsModel.getRent());
            pst.setInt(3, amountDetailsModel.getFoodCharges());
            pst.setInt(4, amountDetailsModel.getProjector());
            pst.setInt(5, amountDetailsModel.getOsp());
            pst.setInt(6, amountDetailsModel.getVideos());
            pst.setInt(7, amountDetailsModel.getSoundSystem());
            pst.setInt(8, amountDetailsModel.getOthers());
            pst.setInt(9, amountDetailsModel.getDiscount());
            pst.setInt(10, amountDetailsModel.getTotal());
            
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
    
    public static ArrayList<AmountDetailsModel> getAmountDetails(){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<AmountDetailsModel> allAmountDetails = new ArrayList<AmountDetailsModel>();
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("select * from conferenceamountdetails");
            
            rs = pst.executeQuery();
            while(rs.next()){
                String voucharNo = rs.getString("voucharno");
                int rent = rs.getInt("rent");
                int foodCharges = rs.getInt("foodcharges");
                int projector = rs.getInt("projector");
                int osp = rs.getInt("osp");
                int videos = rs.getInt("videos");
                int soundSystem = rs.getInt("soundsystem");
                int others = rs.getInt("others");
                int discount = rs.getInt("discount");
                int total = rs.getInt("total");
                
                AmountDetailsModel amountDetailsModel = new AmountDetailsModel(voucharNo, rent, foodCharges, projector, osp, videos, soundSystem, others, discount, total);
                allAmountDetails.add(amountDetailsModel);
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
        return allAmountDetails;
    }
    
    public static int deleteUsingMR(String mrNo){
        Connection connection = null;
        PreparedStatement pst = null;
        int result = 0;
        try{
            
            connection = DatabaseConnection.connectDatabase();
            
            pst = connection.prepareStatement("delete from conferenceamountdetails where voucharno=?");
            
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
