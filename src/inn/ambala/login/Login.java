/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.login;

import com.placeholder.PlaceHolder;
import inn.ambala.admin.model.UserModel;
import inn.ambala.dashboard.AdminDashboard;
import inn.ambala.dashboard.DeleteDashboard;
import inn.ambala.dashboard.UserDashboard;
import inn.ambala.databaseconnection.users.UserAccessCrud;
import inn.ambala.resources.Resources;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author REZA
 */
public class Login extends javax.swing.JFrame {

    PlaceHolder placeHolder;
    
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        initExtraComponents();
        
    }
    
    private void initExtraComponents(){
        ImageIcon key = new ImageIcon(Resources.getPath() + "icon-01.jpg");
        Image image = key.getImage();
        setIconImage(image);
        setTitle("Ambala Inn");
        setLocationRelativeTo(null);
        setResizable(false);
        setKeyIcon();
        setUserIcon();
        setPasswordIcon();
        setNewUserIcon();
        setLogo();
        
        placeHolder = new PlaceHolder(loginUserNameTF, "Username");
        placeHolder = new PlaceHolder(loginPasswordPF, "Password");
    }
    
    
    
    private void setLogo(){
        ImageIcon key = new ImageIcon(Resources.getPath() +"logo.png");
        Image image = key.getImage();
        Image newImage = image.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newKey = new ImageIcon(newImage);
        logo.setIcon(newKey);
    }
    
    
    private void setKeyIcon(){
        ImageIcon key = new ImageIcon(Resources.getPath() +"key-01.png");
        Image image = key.getImage();
        Image newImage = image.getScaledInstance(key01Label.getWidth(), key01Label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newKey = new ImageIcon(newImage);
        key01Label.setIcon(newKey);
    }
    
    
    private void setUserIcon(){
        ImageIcon key = new ImageIcon(Resources.getPath() +"username-01.png");
        Image image = key.getImage();
        Image newImage = image.getScaledInstance(usernameLabel.getWidth(), usernameLabel.getHeight()-10, Image.SCALE_SMOOTH);
        ImageIcon newKey = new ImageIcon(newImage);
        usernameLabel.setIcon(newKey);
    }
    
    
    private void setPasswordIcon(){
        ImageIcon key = new ImageIcon(Resources.getPath() +"password-01.png");
        Image image = key.getImage();
        Image newImage = image.getScaledInstance(passwordLabel.getWidth(), passwordLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newKey = new ImageIcon(newImage);
        passwordLabel.setIcon(newKey);
    }
    
    
    private void setNewUserIcon(){
        ImageIcon key = new ImageIcon(Resources.getPath() +"new_user-01.png");
        Image image = key.getImage();
        Image newImage = image.getScaledInstance(newUserLabel.getWidth(), newUserLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newKey = new ImageIcon(newImage);
        newUserLabel.setIcon(newKey);
        
    }
    
    private void signInButtonPressed(){
        
        String userName = loginUserNameTF.getText();
        String password = loginPasswordPF.getText();
        
        if(userName.equals("ambala") && password.equals("ambala1950")){
            new DeleteDashboard("Deletor").setVisible(true);
            dispose();
        }
        else{
            String permission = UserAccessCrud.checkUserPermission(userName, password);

            if(permission.equals("admin")){
                new AdminDashboard(userName).setVisible(true);
                dispose();
            }
            else if(permission.equals("granted")){
                new UserDashboard(userName).setVisible(true);
                dispose();
            }

            else if(permission.equals("unregister")){
                JOptionPane.showMessageDialog(null, "Wrong Username or Password!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Please Register First");
            }
        }
        //new AdminDashboard().setVisible(true);
        //dispose();
    }
    
    private void signUpButtonPressed(){
        if (userNameTF.getText().equals("") || emailTF.getText().equals("") || departmentTF.getText().equals("") || passwordPF.getText().equals("") || confirmPasswordPF.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Fill up this form carefully");
        }
        else if(userNameTF.getText().length()<3){
            JOptionPane.showMessageDialog(null, "Username is too short");
        }
        
        else if(passwordPF.getText().length()<5){
            JOptionPane.showMessageDialog(null, "Password is too short");
        }
        else if(!passwordPF.getText().equals(confirmPasswordPF.getText())){
            JOptionPane.showMessageDialog(null, "Enter Correct Password");
        }
        else {
            int userId = 0; // database auto increment will handle it
            
            String userName = userNameTF.getText();
            String email = emailTF.getText();
            String department = departmentTF.getText();
            String password = passwordPF.getText();
            String permission = "denied";
            
            UserAccessCrud.addUser(new UserModel(userId, userName, password, email, department, permission));
            
            userNameTF.setText("");
            emailTF.setText("");
            departmentTF.setText("");
            passwordPF.setText("");
            confirmPasswordPF.setText("");
            JOptionPane.showMessageDialog(null, "Successful! Please Contact with Admin for Permission");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        newUserLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userNameTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        departmentTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        passwordPF = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        confirmPasswordPF = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        signUpButton = new javax.swing.JButton();
        copyrightLabel = new javax.swing.JLabel();
        key01Label = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        loginUserNameTF = new javax.swing.JTextField();
        loginPasswordPF = new javax.swing.JPasswordField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        signInButton = new javax.swing.JButton();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(101, 45, 144));

        jPanel3.setBackground(new java.awt.Color(227, 226, 231));

        jLabel4.setText("Username");

        userNameTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        userNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameTFActionPerformed(evt);
            }
        });

        jLabel5.setText("Email");

        emailTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        emailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTFActionPerformed(evt);
            }
        });

        jLabel6.setText("Department");

        departmentTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        departmentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentTFActionPerformed(evt);
            }
        });

        jLabel7.setText("Password");

        passwordPF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        passwordPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordPFActionPerformed(evt);
            }
        });

        jLabel8.setText("Confirm Password");

        confirmPasswordPF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        confirmPasswordPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordPFActionPerformed(evt);
            }
        });

        jLabel9.setText("To create a new user click sign up button");

        signUpButton.setBackground(new java.awt.Color(251, 176, 64));
        signUpButton.setText("Sign Up");
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(emailTF)
                    .addComponent(departmentTF)
                    .addComponent(passwordPF)
                    .addComponent(confirmPasswordPF, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(userNameTF, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 36, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(signUpButton)
                .addGap(99, 99, 99))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(newUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(newUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(departmentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordPF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPasswordPF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(signUpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(22, 22, 22))
        );

        copyrightLabel.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        copyrightLabel.setForeground(new java.awt.Color(255, 255, 255));
        copyrightLabel.setText("Copyright 2018 Ambala Inn | Developed by Mistri Solutions");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(copyrightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(key01Label, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(key01Label, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(copyrightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginUserNameTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        loginUserNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUserNameTFActionPerformed(evt);
            }
        });
        jPanel1.add(loginUserNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 120, 30));

        loginPasswordPF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        loginPasswordPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginPasswordPFActionPerformed(evt);
            }
        });
        jPanel1.add(loginPasswordPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 100, 30));

        usernameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(usernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 30, 29));

        passwordLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 30, 29));

        signInButton.setBackground(new java.awt.Color(27, 116, 189));
        signInButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        signInButton.setForeground(new java.awt.Color(255, 255, 255));
        signInButton.setText("Sign In");
        signInButton.setBorder(null);
        signInButton.setBorderPainted(false);
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });
        jPanel1.add(signInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 70, 30));
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 117, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginPasswordPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPasswordPFActionPerformed
        signInButtonPressed();
    }//GEN-LAST:event_loginPasswordPFActionPerformed

    private void userNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameTFActionPerformed
        signUpButtonPressed();
    }//GEN-LAST:event_userNameTFActionPerformed

    private void emailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTFActionPerformed
        signUpButtonPressed();
    }//GEN-LAST:event_emailTFActionPerformed

    private void departmentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentTFActionPerformed
        signUpButtonPressed();
    }//GEN-LAST:event_departmentTFActionPerformed

    private void passwordPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordPFActionPerformed
        signUpButtonPressed();
    }//GEN-LAST:event_passwordPFActionPerformed

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        signInButtonPressed();
    }//GEN-LAST:event_signInButtonActionPerformed

    private void loginUserNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginUserNameTFActionPerformed
        signInButtonPressed();
    }//GEN-LAST:event_loginUserNameTFActionPerformed

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        signUpButtonPressed();
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void confirmPasswordPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordPFActionPerformed
        signUpButtonPressed();
    }//GEN-LAST:event_confirmPasswordPFActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception{
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmPasswordPF;
    private javax.swing.JLabel copyrightLabel;
    private javax.swing.JTextField departmentTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel key01Label;
    private javax.swing.JPasswordField loginPasswordPF;
    private javax.swing.JTextField loginUserNameTF;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel newUserLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordPF;
    private javax.swing.JButton signInButton;
    private javax.swing.JButton signUpButton;
    private javax.swing.JTextField userNameTF;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
