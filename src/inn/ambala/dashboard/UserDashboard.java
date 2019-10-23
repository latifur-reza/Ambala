/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard;

import inn.ambala.dashboard.*;
import inn.ambala.dashboard.adminforms.Settings;
import inn.ambala.dashboard.userforms.AdvanceBooking;
import inn.ambala.dashboard.userforms.AdvancePayment;
import inn.ambala.dashboard.userforms.CheckIn;
import inn.ambala.dashboard.userforms.CheckOut;
import inn.ambala.dashboard.userforms.Conference;
import inn.ambala.dashboard.userforms.Laundry;
import inn.ambala.dashboard.userforms.Others;
import inn.ambala.dashboard.userforms.Restaurant;
import inn.ambala.dashboard.userforms.RoomTransfer;
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
public class UserDashboard extends javax.swing.JFrame {

    private static String userName = "Admin101";
    
    Color mouseEntry = new Color(252, 176, 64);
    Color mouseExit = new Color(101, 45, 144);
    
    CheckIn checkIn = new CheckIn();
    CheckOut checkOut = new CheckOut();
    AdvanceBooking advanceBooking = new AdvanceBooking();
    RoomTransfer roomTransfer = new RoomTransfer();
    Laundry laundry = new Laundry();
    Restaurant restaurant = new Restaurant();
    Others others = new Others();
    Conference conference = new Conference();
    AdvancePayment advancePayment = new AdvancePayment();
    Settings settings = new Settings();
    

    GridBagLayout layout = new GridBagLayout();

    private static int selection = 0;
    
    /**
     * Creates new form AdminDashboard
     */
    public UserDashboard() {

        initComponents();
        initExtraComponents();
    }
    
    
    public UserDashboard(String userName) {
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
        showCheckInForm();
        checkInPanel.setBackground(mouseEntry);
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

    
    private void showCheckInForm() {
        selection = 1;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.setLayout(layout);
        dynamicPanel.add(checkIn, c);
        checkIn.setVisible(true);
        checkOut.setVisible(false);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        others.setVisible(false);
        conference.setVisible(false);
        advancePayment.setVisible(false);
        settings.setVisible(false);
    }
    
    private void showCheckOutForm() {
        selection = 2;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.setLayout(layout);
        dynamicPanel.add(checkOut, c);
        checkIn.setVisible(false);
        checkOut.setVisible(true);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        others.setVisible(false);
        conference.setVisible(false);
        advancePayment.setVisible(false);
        settings.setVisible(false);
    }
    
    
    
    private void showAdvanceBookingForm() {
        selection = 3;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.setLayout(layout);
        dynamicPanel.add(advanceBooking, c);
        checkIn.setVisible(false);
        checkOut.setVisible(false);
        advanceBooking.setVisible(true);
        roomTransfer.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        others.setVisible(false);
        conference.setVisible(false);
        advancePayment.setVisible(false);
        settings.setVisible(false);
    }

    private void showRoomTransferForm() {
        selection = 4;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(roomTransfer, c);
        checkIn.setVisible(false);
        checkOut.setVisible(false);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(true);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        others.setVisible(false);
        conference.setVisible(false);
        advancePayment.setVisible(false);
        settings.setVisible(false);
    }

    private void showLaundryForm() {
        selection = 5;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(laundry, c);
        checkIn.setVisible(false);
        checkOut.setVisible(false);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(false);
        laundry.setVisible(true);
        restaurant.setVisible(false);
        others.setVisible(false);
        conference.setVisible(false);
        advancePayment.setVisible(false);
        settings.setVisible(false);
    }

    private void showRestaurantForm() {
        selection = 6;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(restaurant, c);
        checkIn.setVisible(false);
        checkOut.setVisible(false);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(true);
        others.setVisible(false);
        conference.setVisible(false);
        advancePayment.setVisible(false);
        settings.setVisible(false);
    }

    private void showOthersForm() {
        selection = 7;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(others, c);
        checkIn.setVisible(false);
        checkOut.setVisible(false);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        others.setVisible(true);
        conference.setVisible(false);
        advancePayment.setVisible(false);
        settings.setVisible(false);
    }

    private void showConferenceForm() {
        selection = 8;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(conference, c);
        checkIn.setVisible(false);
        checkOut.setVisible(false);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        others.setVisible(false);
        conference.setVisible(true);
        advancePayment.setVisible(false);
        settings.setVisible(false);
    }

    private void showAdvancePaymentForm() {
        selection = 9;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(advancePayment, c);
        checkIn.setVisible(false);
        checkOut.setVisible(false);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        others.setVisible(false);
        conference.setVisible(false);
        advancePayment.setVisible(true);
        settings.setVisible(false);
    }

    private void showSettingsForm() {
        selection = 10;
        dynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        dynamicPanel.add(settings, c);
        checkIn.setVisible(false);
        checkOut.setVisible(false);
        advanceBooking.setVisible(false);
        roomTransfer.setVisible(false);
        laundry.setVisible(false);
        restaurant.setVisible(false);
        others.setVisible(false);
        conference.setVisible(false);
        advancePayment.setVisible(false);
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
        checkInPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        checkOutPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        advanceBookingPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        roomTransferPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        laundryPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        restourantPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        othersPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        conferencePanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        advancePaymentPanel = new javax.swing.JPanel();
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        checkInPanel.setBackground(new java.awt.Color(101, 45, 144));
        checkInPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkInPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkInPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkInPanelMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Check In");

        javax.swing.GroupLayout checkInPanelLayout = new javax.swing.GroupLayout(checkInPanel);
        checkInPanel.setLayout(checkInPanelLayout);
        checkInPanelLayout.setHorizontalGroup(
            checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkInPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        checkInPanelLayout.setVerticalGroup(
            checkInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        checkOutPanel.setBackground(new java.awt.Color(101, 45, 144));
        checkOutPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkOutPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkOutPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkOutPanelMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Check Out");

        javax.swing.GroupLayout checkOutPanelLayout = new javax.swing.GroupLayout(checkOutPanel);
        checkOutPanel.setLayout(checkOutPanelLayout);
        checkOutPanelLayout.setHorizontalGroup(
            checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkOutPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        checkOutPanelLayout.setVerticalGroup(
            checkOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        advanceBookingPanel.setBackground(new java.awt.Color(101, 45, 144));
        advanceBookingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                advanceBookingPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                advanceBookingPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                advanceBookingPanelMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Advance Booking");

        javax.swing.GroupLayout advanceBookingPanelLayout = new javax.swing.GroupLayout(advanceBookingPanel);
        advanceBookingPanel.setLayout(advanceBookingPanelLayout);
        advanceBookingPanelLayout.setHorizontalGroup(
            advanceBookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advanceBookingPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        advanceBookingPanelLayout.setVerticalGroup(
            advanceBookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        roomTransferPanel.setBackground(new java.awt.Color(101, 45, 144));
        roomTransferPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomTransferPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                roomTransferPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                roomTransferPanelMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Room Transfer");

        javax.swing.GroupLayout roomTransferPanelLayout = new javax.swing.GroupLayout(roomTransferPanel);
        roomTransferPanel.setLayout(roomTransferPanelLayout);
        roomTransferPanelLayout.setHorizontalGroup(
            roomTransferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomTransferPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roomTransferPanelLayout.setVerticalGroup(
            roomTransferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        othersPanel.setBackground(new java.awt.Color(101, 45, 144));
        othersPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                othersPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                othersPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                othersPanelMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Others");

        javax.swing.GroupLayout othersPanelLayout = new javax.swing.GroupLayout(othersPanel);
        othersPanel.setLayout(othersPanelLayout);
        othersPanelLayout.setHorizontalGroup(
            othersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(othersPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        othersPanelLayout.setVerticalGroup(
            othersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        conferencePanel.setBackground(new java.awt.Color(101, 45, 144));
        conferencePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conferencePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                conferencePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                conferencePanelMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Conference");

        javax.swing.GroupLayout conferencePanelLayout = new javax.swing.GroupLayout(conferencePanel);
        conferencePanel.setLayout(conferencePanelLayout);
        conferencePanelLayout.setHorizontalGroup(
            conferencePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conferencePanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        conferencePanelLayout.setVerticalGroup(
            conferencePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        advancePaymentPanel.setBackground(new java.awt.Color(101, 45, 144));
        advancePaymentPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                advancePaymentPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                advancePaymentPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                advancePaymentPanelMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Advance Payment");

        javax.swing.GroupLayout advancePaymentPanelLayout = new javax.swing.GroupLayout(advancePaymentPanel);
        advancePaymentPanel.setLayout(advancePaymentPanelLayout);
        advancePaymentPanelLayout.setHorizontalGroup(
            advancePaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancePaymentPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        advancePaymentPanelLayout.setVerticalGroup(
            advancePaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
            .addComponent(checkInPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(checkOutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(advanceBookingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roomTransferPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(laundryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(restourantPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(othersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(conferencePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(advancePaymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkInPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkOutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(advanceBookingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomTransferPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(laundryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restourantPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(othersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conferencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(advancePaymentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void checkInPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkInPanelMouseEntered
        // TODO add your handling code here:
        checkInPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_checkInPanelMouseEntered

    private void checkInPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkInPanelMouseExited
        if(selection != 1){
            checkInPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_checkInPanelMouseExited

    private void checkOutPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkOutPanelMouseEntered
        // TODO add your handling code here:
        checkOutPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_checkOutPanelMouseEntered

    private void checkOutPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkOutPanelMouseExited
        if(selection != 2){
            checkOutPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_checkOutPanelMouseExited

    private void advanceBookingPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advanceBookingPanelMouseEntered
        // TODO add your handling code here:
        advanceBookingPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_advanceBookingPanelMouseEntered

    private void roomTransferPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomTransferPanelMouseEntered
        // TODO add your handling code here:
        roomTransferPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_roomTransferPanelMouseEntered

    private void laundryPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laundryPanelMouseEntered
        // TODO add your handling code here:
        laundryPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_laundryPanelMouseEntered

    private void restourantPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restourantPanelMouseEntered
        // TODO add your handling code here:
        restourantPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_restourantPanelMouseEntered

    private void othersPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_othersPanelMouseEntered
        // TODO add your handling code here:
        othersPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_othersPanelMouseEntered

    private void conferencePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conferencePanelMouseEntered
        // TODO add your handling code here:
        conferencePanel.setBackground(mouseEntry);
    }//GEN-LAST:event_conferencePanelMouseEntered

    private void advancePaymentPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advancePaymentPanelMouseEntered
        advancePaymentPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_advancePaymentPanelMouseEntered

    private void settingsPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseEntered
        // TODO add your handling code here:
        settingsPanel.setBackground(mouseEntry);
    }//GEN-LAST:event_settingsPanelMouseEntered

    private void advanceBookingPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advanceBookingPanelMouseExited
        if(selection != 3){
            advanceBookingPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_advanceBookingPanelMouseExited

    private void roomTransferPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomTransferPanelMouseExited
        if(selection != 4){
            roomTransferPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_roomTransferPanelMouseExited

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

    private void othersPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_othersPanelMouseExited
        if(selection != 7){
            othersPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_othersPanelMouseExited

    private void conferencePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conferencePanelMouseExited
        if(selection != 8){
            conferencePanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_conferencePanelMouseExited

    private void advancePaymentPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advancePaymentPanelMouseExited
        if(selection != 9){
            advancePaymentPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_advancePaymentPanelMouseExited

    private void settingsPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseExited
        if(selection != 10){
            settingsPanel.setBackground(mouseExit);
        }
    }//GEN-LAST:event_settingsPanelMouseExited

    private void advanceBookingPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advanceBookingPanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseEntry);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showAdvanceBookingForm();
    }//GEN-LAST:event_advanceBookingPanelMouseClicked

    private void roomTransferPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomTransferPanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseEntry);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showRoomTransferForm();
    }//GEN-LAST:event_roomTransferPanelMouseClicked

    private void laundryPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laundryPanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseEntry);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showLaundryForm();
    }//GEN-LAST:event_laundryPanelMouseClicked

    private void restourantPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restourantPanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseEntry);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showRestaurantForm();
    }//GEN-LAST:event_restourantPanelMouseClicked

    private void othersPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_othersPanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseEntry);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        showOthersForm();
    }//GEN-LAST:event_othersPanelMouseClicked

    private void advancePaymentPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advancePaymentPanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseEntry);
        settingsPanel.setBackground(mouseExit);
        
        showAdvancePaymentForm();
        
    }//GEN-LAST:event_advancePaymentPanelMouseClicked

    private void checkInPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkInPanelMouseClicked
        checkInPanel.setBackground(mouseEntry);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        
        showCheckInForm();
    }//GEN-LAST:event_checkInPanelMouseClicked

    private void checkOutPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkOutPanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseEntry);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        
        showCheckOutForm();
    }//GEN-LAST:event_checkOutPanelMouseClicked

    private void conferencePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conferencePanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseEntry);
        advancePaymentPanel.setBackground(mouseExit);
        settingsPanel.setBackground(mouseExit);
        
        showConferenceForm();
    }//GEN-LAST:event_conferencePanelMouseClicked

    private void settingsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseClicked
        checkInPanel.setBackground(mouseExit);
        checkOutPanel.setBackground(mouseExit);
        advanceBookingPanel.setBackground(mouseExit);
        roomTransferPanel.setBackground(mouseExit);
        laundryPanel.setBackground(mouseExit);
        restourantPanel.setBackground(mouseExit);
        othersPanel.setBackground(mouseExit);
        conferencePanel.setBackground(mouseExit);
        advancePaymentPanel.setBackground(mouseExit);
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
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminLogo;
    private javax.swing.JPanel advanceBookingPanel;
    private javax.swing.JPanel advancePaymentPanel;
    private javax.swing.JLabel calenderDate;
    private javax.swing.JLabel calenderTime;
    private javax.swing.JPanel checkInPanel;
    private javax.swing.JPanel checkOutPanel;
    private javax.swing.JLabel clockLabel;
    private javax.swing.JPanel conferencePanel;
    private javax.swing.JPanel dynamicPanel;
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
    private javax.swing.JPanel othersPanel;
    private javax.swing.JPanel restourantPanel;
    private javax.swing.JPanel roomTransferPanel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
