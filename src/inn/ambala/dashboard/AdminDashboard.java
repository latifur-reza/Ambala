/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard;

import inn.ambala.dashboard.adminforms.CompanyInfo;
import inn.ambala.dashboard.adminforms.Home;
import inn.ambala.dashboard.adminforms.Laundry;
import inn.ambala.dashboard.adminforms.RefInfo;
import inn.ambala.dashboard.adminforms.Report;
import inn.ambala.dashboard.adminforms.Restaurant;
import inn.ambala.dashboard.adminforms.RoomDetails;
import inn.ambala.dashboard.adminforms.RoomRent;
import inn.ambala.dashboard.adminforms.Settings;
import inn.ambala.dashboard.adminforms.UserAccess;
import inn.ambala.login.Login;
import inn.ambala.resources.Resources;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;

/**
 *
 * @author REZA
 */
public class AdminDashboard extends javax.swing.JFrame {

    private static String userName = "Admin101";
    
    Color mouseEntry = new Color(252, 176, 64);
    Color mouseExit = new Color(101, 45, 144);

    Home home = new Home();
    Report report  = new Report();
    RoomDetails roomDetails = new RoomDetails();
    CompanyInfo companyInfo = new CompanyInfo();
    Laundry laundry = new Laundry();
    Restaurant restaurant = new Restaurant();
    RefInfo refInfo = new RefInfo();
    RoomRent roomRent = new RoomRent();
    UserAccess userAccess = new UserAccess();
    Settings settings = new Settings();

    GridBagLayout layout = new GridBagLayout();

    private static int selection = 0;
    
    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboard() {

        initComponents();
        initExtraComponents();
    }
    
    
    public AdminDashboard(String userName) {
        this.userName = userName;
        initComponents();
        initExtraComponents();
    }
    
    

    private void initExtraComponents() {
        ImageIcon key = new ImageIcon(Resources.getPath() + "icon-01.jpg");
        Image image = key.getImage();
        setIconImage(image);
        setTitle("Ambala Inn");
        setFrameSize();
        setClockIcon();
        setLogo();
        adminLogo();
        usernameLabel.setText(userName);
        clock.start();
        showHomeForm();
        homePanel.setBackground(mouseEntry);
    }

    Thread clock = new Thread() {
        @Override
        public void run() {
            while (true) {
                Calendar calendar = new GregorianCalendar();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                month += 1;
                int year = calendar.get(Calendar.YEAR);

                int second = calendar.get(Calendar.SECOND);
                int minute = calendar.get(Calendar.MINUTE);
                int hour = calendar.get(Calendar.HOUR);
                int ampm = calendar.get(Calendar.AM_PM);
                if (ampm == 1) {
                    hour += 12;
                }

                String hourStr = String.format("%02d", hour);
                String minuteStr = String.format("%02d", minute);
                String dayStr = String.format("%02d", day);
                String monthStr = String.format("%02d", month);

                calenderDate.setText(dayStr + "/" + monthStr + "/" + year);
                calenderTime.setText(hourStr + ":" + minuteStr);
            }
        }

    };

    private void adminLogo() {
        ImageIcon key = new ImageIcon(Resources.getPath() + "admin-01.png");
        Image image = key.getImage();
        Image newImage = image.getScaledInstance(adminLogo.getWidth(), adminLogo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newKey = new ImageIcon(newImage);
        adminLogo.setIcon(newKey);
    }

    private void setLogo() {
        ImageIcon key = new ImageIcon(Resources.getPath() + "logo.png");
        Image image = key.getImage();
        Image newImage = image.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newKey = new ImageIcon(newImage);
        logo.setIcon(newKey);
    }

    private void setClockIcon() {
        ImageIcon key = new ImageIcon(Resources.getPath() + "clock-01.png");
        Image image = key.getImage();
        Image newImage = image.getScaledInstance(clockLabel.getWidth(), clockLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newKey = new ImageIcon(newImage);
        clockLabel.setIcon(newKey);
    }

    private void setFrameSize() {
        setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = (int) toolkit.getScreenSize().getWidth();
        int height = (int) toolkit.getScreenSize().getHeight();
        setSize(width, height - 40);
    }

    
    private void showHomeForm() {
        selection = 1;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.setLayout(layout);
        dynamicPanel.add(home, c);
        home.setVisible(true);
        report.setVisible(false);
        roomDetails.setVisible(false);
        companyInfo.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        refInfo.setVisible(false);
        roomRent.setVisible(false);
        userAccess.setVisible(false);
        settings.setVisible(false);
    }
    
    private void showReportForm() {
        selection = 2;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.setLayout(layout);
        dynamicPanel.add(report, c);
        home.setVisible(false);
        report.setVisible(true);
        roomDetails.setVisible(false);
        companyInfo.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        refInfo.setVisible(false);
        roomRent.setVisible(false);
        userAccess.setVisible(false);
        settings.setVisible(false);
    }
    
    
    
    private void showRoomDetailsForm() {
        selection = 3;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.setLayout(layout);
        dynamicPanel.add(roomDetails, c);
        home.setVisible(false);
        report.setVisible(false);
        roomDetails.setVisible(true);
        companyInfo.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        refInfo.setVisible(false);
        roomRent.setVisible(false);
        userAccess.setVisible(false);
        settings.setVisible(false);
    }

    private void showCompanyInfoForm() {
        selection = 4;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(companyInfo, c);
        home.setVisible(false);
        report.setVisible(false);
        roomDetails.setVisible(false);
        companyInfo.setVisible(true);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        refInfo.setVisible(false);
        roomRent.setVisible(false);
        userAccess.setVisible(false);
        settings.setVisible(false);
    }

    private void showLaundryForm() {
        selection = 5;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(laundry, c);
        home.setVisible(false);
        report.setVisible(false);
        roomDetails.setVisible(false);
        companyInfo.setVisible(false);
        laundry.setVisible(true);
        restaurant.setVisible(false);
        refInfo.setVisible(false);
        roomRent.setVisible(false);
        userAccess.setVisible(false);
        settings.setVisible(false);
    }

    private void showRestaurantForm() {
        selection = 6;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(restaurant, c);
        home.setVisible(false);
        report.setVisible(false);
        roomDetails.setVisible(false);
        companyInfo.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(true);
        refInfo.setVisible(false);
        roomRent.setVisible(false);
        userAccess.setVisible(false);
        settings.setVisible(false);
    }

    private void showReferceInfoForm() {
        selection = 7;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(refInfo, c);
        home.setVisible(false);
        report.setVisible(false);
        roomDetails.setVisible(false);
        companyInfo.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        refInfo.setVisible(true);
        roomRent.setVisible(false);
        userAccess.setVisible(false);
        settings.setVisible(false);
    }

    private void showRoomRentForm() {
        selection = 8;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(roomRent, c);
        home.setVisible(false);
        report.setVisible(false);
        roomDetails.setVisible(false);
        companyInfo.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        refInfo.setVisible(false);
        roomRent.setVisible(true);
        userAccess.setVisible(false);
        settings.setVisible(false);
    }

    private void showUserAccessForm() {
        selection = 9;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(userAccess, c);
        home.setVisible(false);
        report.setVisible(false);
        roomDetails.setVisible(false);
        companyInfo.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        refInfo.setVisible(false);
        roomRent.setVisible(false);
        userAccess.setVisible(true);
        settings.setVisible(false);
    }

    private void showSettingsForm() {
        selection = 10;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(settings, c);
        home.setVisible(false);
        report.setVisible(false);
        roomDetails.setVisible(false);
        companyInfo.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        refInfo.setVisible(false);
        roomRent.setVisible(false);
        userAccess.setVisible(false);
        settings.setVisible(true);
    }

    private void logout() {
        dispose();
        new Login().setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        adminLogo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        clockLabel = new javax.swing.JLabel();
        calenderDate = new javax.swing.JLabel();
        calenderTime = new javax.swing.JLabel();
        homePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        reportPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        roomDetailsPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        companyInfoPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        laundryPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        restourantPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        refInfoPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        roomRentPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        userAccessPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        dynamicPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(230, 231, 233));

        logoutBtn.setBackground(new java.awt.Color(26, 116, 188));
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("Logout");
        logoutBtn.setBorder(null);
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(26, 116, 188));
        usernameLabel.setText("Admin101");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addComponent(adminLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernameLabel)
                .addGap(18, 18, 18)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(adminLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(101, 45, 144));

        jPanel4.setBackground(new java.awt.Color(230, 231, 233));

        calenderDate.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        calenderDate.setForeground(new java.awt.Color(27, 117, 188));
        calenderDate.setText("22/3/2018");

        calenderTime.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        calenderTime.setForeground(new java.awt.Color(27, 117, 188));
        calenderTime.setText("20:59");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(calenderTime))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(calenderDate)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(calenderTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calenderDate))))
        );

        homePanel.setBackground(new java.awt.Color(101, 45, 144));
        homePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homePanelMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Home");

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        reportPanel.setBackground(new java.awt.Color(101, 45, 144));
        reportPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportPanelMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Report");

        javax.swing.GroupLayout reportPanelLayout = new javax.swing.GroupLayout(reportPanel);
        reportPanel.setLayout(reportPanelLayout);
        reportPanelLayout.setHorizontalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        reportPanelLayout.setVerticalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        roomDetailsPanel.setBackground(new java.awt.Color(101, 45, 144));
        roomDetailsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomDetailsPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                roomDetailsPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                roomDetailsPanelMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Room Details");

        javax.swing.GroupLayout roomDetailsPanelLayout = new javax.swing.GroupLayout(roomDetailsPanel);
        roomDetailsPanel.setLayout(roomDetailsPanelLayout);
        roomDetailsPanelLayout.setHorizontalGroup(
            roomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomDetailsPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roomDetailsPanelLayout.setVerticalGroup(
            roomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        companyInfoPanel.setBackground(new java.awt.Color(101, 45, 144));
        companyInfoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                companyInfoPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                companyInfoPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                companyInfoPanelMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Company Info");

        javax.swing.GroupLayout companyInfoPanelLayout = new javax.swing.GroupLayout(companyInfoPanel);
        companyInfoPanel.setLayout(companyInfoPanelLayout);
        companyInfoPanelLayout.setHorizontalGroup(
            companyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyInfoPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        companyInfoPanelLayout.setVerticalGroup(
            companyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        laundryPanel.setBackground(new java.awt.Color(101, 45, 144));
        laundryPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laundryPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laundryPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laundryPanelMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Laundry");

        javax.swing.GroupLayout laundryPanelLayout = new javax.swing.GroupLayout(laundryPanel);
        laundryPanel.setLayout(laundryPanelLayout);
        laundryPanelLayout.setHorizontalGroup(
            laundryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laundryPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        laundryPanelLayout.setVerticalGroup(
            laundryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        restourantPanel.setBackground(new java.awt.Color(101, 45, 144));
        restourantPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                restourantPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                restourantPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                restourantPanelMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Restaurant");

        javax.swing.GroupLayout restourantPanelLayout = new javax.swing.GroupLayout(restourantPanel);
        restourantPanel.setLayout(restourantPanelLayout);
        restourantPanelLayout.setHorizontalGroup(
            restourantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restourantPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        restourantPanelLayout.setVerticalGroup(
            restourantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        refInfoPanel.setBackground(new java.awt.Color(101, 45, 144));
        refInfoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refInfoPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refInfoPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refInfoPanelMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ref-Info");

        javax.swing.GroupLayout refInfoPanelLayout = new javax.swing.GroupLayout(refInfoPanel);
        refInfoPanel.setLayout(refInfoPanelLayout);
        refInfoPanelLayout.setHorizontalGroup(
            refInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(refInfoPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        refInfoPanelLayout.setVerticalGroup(
            refInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        roomRentPanel.setBackground(new java.awt.Color(101, 45, 144));
        roomRentPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomRentPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                roomRentPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                roomRentPanelMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Room Rent");

        javax.swing.GroupLayout roomRentPanelLayout = new javax.swing.GroupLayout(roomRentPanel);
        roomRentPanel.setLayout(roomRentPanelLayout);
        roomRentPanelLayout.setHorizontalGroup(
            roomRentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomRentPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roomRentPanelLayout.setVerticalGroup(
            roomRentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        userAccessPanel.setBackground(new java.awt.Color(101, 45, 144));
        userAccessPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userAccessPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userAccessPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userAccessPanelMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("User Access");

        javax.swing.GroupLayout userAccessPanelLayout = new javax.swing.GroupLayout(userAccessPanel);
        userAccessPanel.setLayout(userAccessPanelLayout);
        userAccessPanelLayout.setHorizontalGroup(
            userAccessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userAccessPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userAccessPanelLayout.setVerticalGroup(
            userAccessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        settingsPanel.setBackground(new java.awt.Color(101, 45, 144));
        settingsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsPanelMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Settings");

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(homePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roomDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(companyInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(laundryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(restourantPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(refInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roomRentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(userAccessPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(laundryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restourantPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomRentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userAccessPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout dynamicPanelLayout = new javax.swing.GroupLayout(dynamicPanel);
        dynamicPanel.setLayout(dynamicPanelLayout);
        dynamicPanelLayout.setHorizontalGroup(
            dynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dynamicPanelLayout.setVerticalGroup(
            dynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(dynamicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dynamicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void homePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homePanelMouseEntered
        // TODO add your handling code here:
        homePanel.setBackground(mouseEntry);
    }//GEN-LAST:event_homePanelMouseEntered

    private void homePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homePanelMouseExited
        if(selection != 1){
            homePanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_homePanelMouseExited

    private void reportPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPanelMouseEntered
        // TODO add your handling code here:
        reportPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_reportPanelMouseEntered

    private void reportPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPanelMouseExited
        if(selection != 2){
            reportPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_reportPanelMouseExited

    private void roomDetailsPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomDetailsPanelMouseEntered
        // TODO add your handling code here:
        roomDetailsPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_roomDetailsPanelMouseEntered

    private void companyInfoPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_companyInfoPanelMouseEntered
        // TODO add your handling code here:
        companyInfoPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_companyInfoPanelMouseEntered

    private void laundryPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laundryPanelMouseEntered
        // TODO add your handling code here:
        laundryPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_laundryPanelMouseEntered

    private void restourantPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restourantPanelMouseEntered
        // TODO add your handling code here:
        restourantPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_restourantPanelMouseEntered

    private void refInfoPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refInfoPanelMouseEntered
        // TODO add your handling code here:
        refInfoPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_refInfoPanelMouseEntered

    private void roomRentPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomRentPanelMouseEntered
        // TODO add your handling code here:
        roomRentPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_roomRentPanelMouseEntered

    private void userAccessPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userAccessPanelMouseEntered
        userAccessPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_userAccessPanelMouseEntered

    private void settingsPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseEntered
        // TODO add your handling code here:
        settingsPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_settingsPanelMouseEntered

    private void roomDetailsPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomDetailsPanelMouseExited
        if(selection != 3){
            roomDetailsPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_roomDetailsPanelMouseExited

    private void companyInfoPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_companyInfoPanelMouseExited
        if(selection != 4){
            companyInfoPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_companyInfoPanelMouseExited

    private void laundryPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laundryPanelMouseExited
        if(selection != 5){
            laundryPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_laundryPanelMouseExited

    private void restourantPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restourantPanelMouseExited
        if(selection != 6){
            restourantPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_restourantPanelMouseExited

    private void refInfoPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refInfoPanelMouseExited
        if(selection != 7){
            refInfoPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_refInfoPanelMouseExited

    private void roomRentPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomRentPanelMouseExited
        if(selection != 8){
            roomRentPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_roomRentPanelMouseExited

    private void userAccessPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userAccessPanelMouseExited
        if(selection != 9){
            userAccessPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_userAccessPanelMouseExited

    private void settingsPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseExited
        if(selection != 10){
            settingsPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_settingsPanelMouseExited

    private void roomDetailsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomDetailsPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseEntry);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showRoomDetailsForm();
    }//GEN-LAST:event_roomDetailsPanelMouseClicked

    private void companyInfoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_companyInfoPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseEntry);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showCompanyInfoForm();
    }//GEN-LAST:event_companyInfoPanelMouseClicked

    private void laundryPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laundryPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseEntry);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showLaundryForm();
    }//GEN-LAST:event_laundryPanelMouseClicked

    private void restourantPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restourantPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseEntry);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showRestaurantForm();
    }//GEN-LAST:event_restourantPanelMouseClicked

    private void refInfoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refInfoPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseEntry);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showReferceInfoForm();
    }//GEN-LAST:event_refInfoPanelMouseClicked

    private void userAccessPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userAccessPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseEntry);
        settingsPanel.setBackground(mouseExit);
        
        showUserAccessForm();
        
    }//GEN-LAST:event_userAccessPanelMouseClicked

    private void homePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homePanelMouseClicked
        homePanel.setBackground(mouseEntry);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        
        showHomeForm();
    }//GEN-LAST:event_homePanelMouseClicked

    private void reportPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseEntry);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        
        showReportForm();
    }//GEN-LAST:event_reportPanelMouseClicked

    private void roomRentPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomRentPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseEntry);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        
        showRoomRentForm();
    }//GEN-LAST:event_roomRentPanelMouseClicked

    private void settingsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseClicked
        homePanel.setBackground(mouseExit);
        reportPanel.setBackground(mouseExit);
        roomDetailsPanel.setBackground(mouseExit);
        companyInfoPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        refInfoPanel.setBackground(mouseExit);
        roomRentPanel.setBackground(mouseExit);
        userAccessPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseEntry);
        
        showSettingsForm();
    }//GEN-LAST:event_settingsPanelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminLogo;
    private javax.swing.JLabel calenderDate;
    private javax.swing.JLabel calenderTime;
    private javax.swing.JLabel clockLabel;
    private javax.swing.JPanel companyInfoPanel;
    private javax.swing.JPanel dynamicPanel;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel laundryPanel;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel refInfoPanel;
    private javax.swing.JPanel reportPanel;
    private javax.swing.JPanel restourantPanel;
    private javax.swing.JPanel roomDetailsPanel;
    private javax.swing.JPanel roomRentPanel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JPanel userAccessPanel;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
