/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.databaseconnection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author REZA
 */
public class DatabaseConnection {
    public static Connection connectDatabase(){
        Connection connection = null;
//        String url = "jdbc:mysql://192.168.0.100/ambala_inn";
//        String user = "user";
//        String password = "user";
        String url = "jdbc:mysql://localhost:3306/ambala_inn";
        String user = "root";
        String password = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url,user,password);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database Error!");
        }
        return connection;
    }
}
