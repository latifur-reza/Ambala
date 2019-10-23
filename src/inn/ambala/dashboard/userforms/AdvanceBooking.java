/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms;

import inn.ambala.admin.model.CompanyInfoModel;
import inn.ambala.admin.model.ReferenceInfoModel;
import inn.ambala.admin.model.RoomRentModel;
import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.dashboard.userforms.model.UserOthersInfoModel;
import inn.ambala.dashboard.userforms.model.UsersRoomInfoModel;
import inn.ambala.databaseconnection.admin.CompanyInfoCrud;
import inn.ambala.databaseconnection.admin.ReferenceInfoCrud;
import inn.ambala.databaseconnection.admin.RoomDetailsCrud;
import inn.ambala.databaseconnection.admin.RoomRentCrud;
import inn.ambala.databaseconnection.userforms.forms.UserInfoCrud;
import inn.ambala.databaseconnection.userforms.forms.UserOthersInfoCrud;
import inn.ambala.databaseconnection.userforms.forms.UserRoomInfoCrud;
import inn.ambala.keypicker.KeyGenerator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class AdvanceBooking extends javax.swing.JPanel {

    private static DefaultTableModel dataTableModel = null;
    /**
     * Creates new form AdvanceBooking
     */
    public AdvanceBooking() {
        initComponents();
        initExtraComponents();
    }
    
    private void initExtraComponents(){
        
        comboBox();
        comboBoxPickUp();
        dataTableModel = (DefaultTableModel) dataTable.getModel();
        refreshTable();
        clearForm();
        clock.start();
    }
    
    private void refreshTable(){
        showCurrentBookings();
        
    }
    
    private void showCurrentBookings(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dataTableModel.setRowCount(0);
        ArrayList<UserInfoModel> allUserInfo = UserInfoCrud.getAllUserInfo();
        ArrayList<UsersRoomInfoModel> allUserRoomInfo = UserRoomInfoCrud.getAllUserRoomInfo();
        ArrayList<UserOthersInfoModel> allUserOthersInfo = UserOthersInfoCrud.getAllUserOthersInfo();
        for(UsersRoomInfoModel urim : allUserRoomInfo){
            if(urim.getStatus().equals("Booked")){
                for(UserInfoModel uim : allUserInfo){
                    if(urim.getMrNo().equals(uim.getMrNo())){
                        for(UserOthersInfoModel uoim : allUserOthersInfo){
                            if(urim.getMrNo().equals(uoim.getMrNo())){
                                dataTableModel.addRow(new Object[]{urim.getMrNo(),uim.getName(),sdf.format(urim.getArrivalDate()),uoim.getProbableStay(),uoim.getWhereIssued(),""});
                                
                            }
                        }
                        
                    }
                }
            }
        }
    }
    
    private void comboBox() {
        ArrayList<String> roomTypes = new ArrayList<String>();
        roomTypes.add("Single");
        roomTypes.add("Double");
        roomTypes.add("Double+Extra Bed");

        roomTypeCB.removeAllItems();
        for (String types : roomTypes) {
            roomTypeCB.addItem(types);
        }
        roomTypeCB.setSelectedIndex(0);
    }
    
    private void comboBoxPickUp() {
        ArrayList<String> pickups = new ArrayList<String>();
        pickups.add("None");
        pickups.add("Airport");
        pickups.add("Bus Stop");

        pickUpCB.removeAllItems();
        for (String types : pickups) {
            pickUpCB.addItem(types);
        }
        pickUpCB.setSelectedIndex(0);
    }
    
    private void clearForm(){
        nameTF.setText("");;
        addressTF.setText("");
        companyNameTF.setText("");
        professionTF.setText("");
        nationalityTF.setText("");
        passportNoTF.setText("");
        //dateOfBirthDC.setDateFormatString("");
        emailTF.setText("");
        phoneTF.setText("");
        
        
        //UsersRoomInfo
        ////String mrNo;
        roomNoTF.setText("0");
        roomTypeCB.setSelectedIndex(0);
        //arrivalDateDC.setDateFormatString("");
        //departureDateDC.setDateFormatString("");
        discountPerTF.setText("0");
        discountTkTF.setText("0");
        roomTarrifTF.setText("0");
        int roomBill = 0;
        String status = "CheckedIn";

        
        //UsersOthersInfo
        
        ////String mrNo;
        ////int roomNo;
        reservationMadeByTF.setText("");
        localCompanyNameTF.setText("");
        localCompanyAddressTF.setText("");
        probableStayTF.setText("");
        int totalBillCollected = 0;
        pickUpCB.setSelectedIndex(0);
        flightNoTF.setText("");
        noOfGuestTF.setText("1");
    }
    
    private void roomTypeCBAction(){
        String roomType = (String) roomTypeCB.getSelectedItem();
        ArrayList<RoomRentModel> allRoomRent = RoomRentCrud.showAllRoomRents();
        for(RoomRentModel rrm : allRoomRent){
            if(rrm.getRoomType().equals(roomType)){
                roomTarrifTF.setText(rrm.getRoomRent()+"");
            }
        }
        if(roomType.equals("Single")){
            noOfGuestTF.setText("1");
        }else if(roomType.equals("Double")){
            noOfGuestTF.setText("2");
        }else if(roomType.equals("Double+Extra Bed")){
            noOfGuestTF.setText("3");
        }else{
            noOfGuestTF.setText("1");
        }
    }
    
    private void localCompanyNameAction(){
        boolean flag = true;
        int code = 0;
        try{
            code = Integer.valueOf(localCompanyNameTF.getText());
        }catch(Exception e){
            flag = false;
            JOptionPane.showMessageDialog(null, "Enter Digits Only");
        }
        if(flag){
            ArrayList<CompanyInfoModel> allCompany = CompanyInfoCrud.getAllCompanyInfo();

            for(CompanyInfoModel cim : allCompany){
                if(code == cim.getCompanyCode()){
                    localCompanyNameTF.setText(cim.getCompanyName());
                    localCompanyAddressTF.setText(cim.getAddress());
                    addressTF.setText(cim.getAddress());
                }
            }
        }
    }
    
    private void reservationAction(){
        boolean flag = true;
        int code = 0;
        try{
            code = Integer.valueOf(reservationMadeByTF.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Enter Digits Only");
            flag = false;
        }
        
        
        if(flag){
            ArrayList<ReferenceInfoModel> allReference = ReferenceInfoCrud.getAllReference();
            for(ReferenceInfoModel rim : allReference){

                if(code == rim.getReferenceCode()){
                    reservationMadeByTF.setText(rim.getName());
                }
            }
        }
    }
    
    private void bookedButtonPressed(){
        if(nameTF.getText().equals("")|| addressTF.getText().equals("")||
                nationalityTF.getText().equals("") || roomNoTF.getText().equals("") ||
                roomTarrifTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Fill Up The Form Carefully");
        }
        else{
            bookingData();
            refreshTable();
            AdvancePayment.refreshTable();
            RoomTransfer.refreshTable();
            JOptionPane.showMessageDialog(null, "Successfully Booked");
        }
        
    }
    
    private void bookingData(){
        try{
            KeyGenerator key = new KeyGenerator();
            String mrNo = key.getKey();

            //UserInfo
            ////String mrNo;
            String name = nameTF.getText();
            String address = addressTF.getText();
            if(addressTF.getText().equals("")){
                address = "N/A";
            }
            
            String companyName = companyNameTF.getText();
            if(companyNameTF.getText().equals("")){
                companyName = "N/A";
            }
            String profession = professionTF.getText();
            if(professionTF.getText().equals("")){
                profession = "N/A";
            }
            String nationality = nationalityTF.getText();
            if(nationalityTF.getText().equals("")){
                nationality = "N/A";
            }
            String passportNo = passportNoTF.getText();
            if(passportNoTF.getText().equals("")){
                passportNo = "N/A";
            }
            Date dateOfBirth = new Date();
            String email = emailTF.getText();
            if(emailTF.getText().equals("")){
                email = "N/A";
            }
            String phone = phoneTF.getText();
            if(phoneTF.getText().equals("")){
                phone = "N/A";
            }

            UserInfoModel userInfoModel = new UserInfoModel(mrNo, name, address, companyName, profession, nationality, passportNo, dateOfBirth, email, phone);
            UserInfoCrud.addUserInfo(userInfoModel);

            //UsersRoomInfo
            ////String mrNo;
            if(roomNoTF.getText().equals("")){
                roomNoTF.setText("0");
            }
            int roomNo = Integer.valueOf(roomNoTF.getText());
            String roomType = (String) roomTypeCB.getSelectedItem();
            Date arrivalDate = arrivalDateDC.getDate();
            if(arrivalDateDC.getDate() == null){
                arrivalDate = new Date();
            }
            Date departureDate = departureDateDC.getDate();
            if(departureDateDC.getDate() == null){
                departureDate = new Date();
            }
            if(roomTarrifTF.getText().equals("")){
                roomTarrifTF.setText("0");
            }
            if(discountTkTF.getText().equals("")){
                discountTkTF.setText("0");
            }
            if(discountPerTF.getText().equals("")){
                discountPerTF.setText("0");
            }
            int discountPercent = Integer.valueOf(discountPerTF.getText());
            int discountCash = Integer.valueOf(discountTkTF.getText());
            int roomTarrif = Integer.valueOf(roomTarrifTF.getText());
            int roomBill = 0;
            String status = "Booked";

            UsersRoomInfoModel usersRoomInfoModel = new UsersRoomInfoModel(mrNo, roomNo, roomType, arrivalDate, departureDate, discountPercent, discountCash, roomTarrif, roomBill, status);
            UserRoomInfoCrud.addUserRoomInfo(usersRoomInfoModel);

            //UsersOthersInfo

            ////String mrNo;
            ////int roomNo;
            
            if(reservationMadeByTF.getText().equals("")){
                reservationMadeByTF.setText("N/A");
            }
            if(localCompanyNameTF.getText().equals("")){
                localCompanyNameTF.setText("N/A");
            }
            if(localCompanyAddressTF.getText().equals("")){
                localCompanyAddressTF.setText("N/A");
            }
            if(flightNoTF.getText().equals("")){
                flightNoTF.setText("N/A");
            }
            if(noOfGuestTF.getText().equals("")){
                noOfGuestTF.setText("1");
            }
            
            String registrationMadeBy = reservationMadeByTF.getText();
            String localCompany = localCompanyNameTF.getText();
            String localCompanyAddress = localCompanyAddressTF.getText();
            String probableStay = flightNoTF.getText();
            int totalBillCollected = 0;
            String whereIssued = (String) pickUpCB.getSelectedItem();
            int noOfGuest = Integer.valueOf(noOfGuestTF.getText());

            UserOthersInfoModel userOthersInfoModel = new UserOthersInfoModel(mrNo, roomNo, registrationMadeBy, localCompany, localCompanyAddress, probableStay, totalBillCollected, whereIssued, noOfGuest);
            UserOthersInfoCrud.addUserOthersInfo(userOthersInfoModel);

            clearForm();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Wrong Entry");
        }
    }
    
    
    private void cancelButtonPressed(){
        
        int rowNo = dataTable.getSelectedRow();
        if(rowNo>=0){
            String mrNo = dataTable.getModel().getValueAt(rowNo, 0).toString();
            String status = "Booking Cancelled";
            UserRoomInfoCrud.updateUserRoomInfoStatus(mrNo, status);
            refreshTable();
            JOptionPane.showMessageDialog(null, "Booking Cancelled");
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Select Any Row for cancel");
        }
        
    }
    
    private void checkInButtonPressed(){
        
        int rowNo = dataTable.getSelectedRow();
        if(rowNo>=0){
            String mrNo = dataTable.getModel().getValueAt(rowNo, 0).toString();
            String status = "Checked In";
            UserRoomInfoCrud.updateUserRoomInfoStatus(mrNo, status);
            refreshTable();
            JOptionPane.showMessageDialog(null, "Fill the check in form");
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Select Any Row for cancel");
        }
        
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Thread clock = new Thread() {
        @Override
        public void run() {
            while (true) {
                Date currentTime = new Date();
                int rows = dataTable.getRowCount();
                for(int i = 0 ; i<rows ; i++){
                    try {
                        Thread.sleep(500);
                        Date arrival = sdf.parse(dataTable.getModel().getValueAt(i, 2).toString());
                        long diff = arrival.getTime() - currentTime.getTime();
                        long diffSec = diff / 1000 % 60;
                        long diffMinutes = diff / (60 * 1000) % 60;
                        long diffHours = diff / (60 * 60 * 1000);
                        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
                        String name = dataTable.getModel().getValueAt(i, 1).toString();
                        if(diffDays == 0 && diffHours == 0 && (diffSec == 0 || diffSec == 1)){
                            if(diffMinutes == 30){
                                JOptionPane.showMessageDialog(null, name + " is Arriving.");
                            }else if(diffMinutes == 20){
                                JOptionPane.showMessageDialog(null, name + " is Arriving.");
                            }else if(diffMinutes == 15){
                                JOptionPane.showMessageDialog(null, name + " is Arriving.");
                            }else if(diffMinutes == 10){
                                JOptionPane.showMessageDialog(null, name + " is Arriving.");
                            }
                        }
                        
                    } catch (ParseException ex) {
                        Logger.getLogger(AdvanceBooking.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AdvanceBooking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
    };
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        companyNameTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        roomTarrifTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        passportNoTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        noOfGuestTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        phoneTF = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        discountPerTF = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        discountTkTF = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        guestRegistrationFormLabel = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        departureDateDC = new com.toedter.calendar.JDateChooser();
        reservationMadeByTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        roomNoTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        roomTypeCB = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        addressTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nationalityTF = new javax.swing.JTextField();
        professionTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        probableStayTF = new javax.swing.JTextField();
        pickUpCB = new javax.swing.JComboBox<>();
        flightNoTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        arrivalDateDC = new com.toedter.calendar.JDateChooser();
        bookedButton = new javax.swing.JButton();
        checkInButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        localCompanyNameTF = new javax.swing.JTextField();
        localCompanyAddressTF = new javax.swing.JTextField();

        companyNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyNameTFActionPerformed(evt);
            }
        });

        jLabel11.setText("Company Name");

        jLabel12.setText("Room Tarrif");

        jLabel13.setText("Probable Stay");

        jLabel14.setText("Passport No.");

        jLabel15.setText("No. of Guest");

        jLabel16.setText("Email");

        emailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTFActionPerformed(evt);
            }
        });

        jLabel17.setText("Phone");

        jLabel18.setText("Discount (%)");

        jLabel19.setText("Discount (Tk)");

        jLabel21.setText("PickUp");

        jLabel22.setText("Flight No.");

        guestRegistrationFormLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        guestRegistrationFormLabel.setText("Advance Booking");

        jLabel23.setText("Departure Date");

        jLabel2.setText("Reservation Made by");

        departureDateDC.setDateFormatString("yyyy-MM-dd");

        reservationMadeByTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservationMadeByTFActionPerformed(evt);
            }
        });

        jLabel3.setText("Name");

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking No.", "Guest Name", "Arrival Date", "Flight No", "PickUp From", "PABx Ext"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(dataTable);

        jLabel4.setText("Room No.");

        jLabel5.setText("Room Type");

        jLabel6.setText("Address");

        jLabel7.setText("Local Company Name");

        roomTypeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomTypeCBActionPerformed(evt);
            }
        });

        jLabel8.setText("Address");

        jLabel9.setText("Nationality");

        nationalityTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nationalityTFActionPerformed(evt);
            }
        });

        jLabel10.setText("Profession");

        jLabel1.setText("Arrival Date");

        arrivalDateDC.setDateFormatString("yyyy-MM-dd HH:mm");

        bookedButton.setBackground(new java.awt.Color(58, 179, 73));
        bookedButton.setForeground(new java.awt.Color(255, 255, 255));
        bookedButton.setText("Booked");
        bookedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookedButtonActionPerformed(evt);
            }
        });

        checkInButton.setBackground(new java.awt.Color(251, 176, 63));
        checkInButton.setForeground(new java.awt.Color(255, 255, 255));
        checkInButton.setText("Check In");
        checkInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(252, 0, 0));
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        localCompanyNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localCompanyNameTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reservationMadeByTF, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel3)
                        .addGap(250, 250, 250)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roomTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(roomTarrifTF, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(companyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel11)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(117, 117, 117))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(localCompanyAddressTF)
                                    .addComponent(localCompanyNameTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(probableStayTF, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(professionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(nationalityTF)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(arrivalDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(flightNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneTF))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(discountPerTF, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(passportNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel14))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel23)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noOfGuestTF, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(200, 200, 200)
                                .addComponent(jLabel22))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(discountTkTF, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(departureDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(pickUpCB, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(emailTF)))
                    .addComponent(jScrollPane3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(guestRegistrationFormLabel)
                        .addGap(200, 200, 200))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bookedButton)
                        .addGap(18, 18, 18)
                        .addComponent(checkInButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton)
                        .addGap(175, 175, 175))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(guestRegistrationFormLabel)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reservationMadeByTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roomTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel12)
                        .addGap(6, 6, 6)
                        .addComponent(roomTarrifTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(localCompanyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(localCompanyAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel10))
                    .addComponent(jLabel9))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(probableStayTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(professionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nationalityTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(discountPerTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(passportNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noOfGuestTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jLabel21))
                    .addComponent(jLabel16))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(discountTkTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departureDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pickUpCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel22)
                    .addComponent(jLabel17))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(arrivalDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(flightNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookedButton)
                    .addComponent(checkInButton)
                    .addComponent(cancelButton))
                .addGap(0, 8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void companyNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companyNameTFActionPerformed

    private void bookedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookedButtonActionPerformed
        bookedButtonPressed();
    }//GEN-LAST:event_bookedButtonActionPerformed

    private void checkInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkInButtonActionPerformed
        checkInButtonPressed();
    }//GEN-LAST:event_checkInButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancelButtonPressed();
        
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void emailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTFActionPerformed

    private void nationalityTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nationalityTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nationalityTFActionPerformed

    private void localCompanyNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localCompanyNameTFActionPerformed
        localCompanyNameAction();
    }//GEN-LAST:event_localCompanyNameTFActionPerformed

    private void roomTypeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomTypeCBActionPerformed
        roomTypeCBAction();
    }//GEN-LAST:event_roomTypeCBActionPerformed

    private void reservationMadeByTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationMadeByTFActionPerformed
        reservationAction();
    }//GEN-LAST:event_reservationMadeByTFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTF;
    private com.toedter.calendar.JDateChooser arrivalDateDC;
    private javax.swing.JButton bookedButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton checkInButton;
    private javax.swing.JTextField companyNameTF;
    private javax.swing.JTable dataTable;
    private com.toedter.calendar.JDateChooser departureDateDC;
    private javax.swing.JTextField discountPerTF;
    private javax.swing.JTextField discountTkTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField flightNoTF;
    private javax.swing.JLabel guestRegistrationFormLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField localCompanyAddressTF;
    private javax.swing.JTextField localCompanyNameTF;
    private javax.swing.JTextField nameTF;
    private javax.swing.JTextField nationalityTF;
    private javax.swing.JTextField noOfGuestTF;
    private javax.swing.JTextField passportNoTF;
    private javax.swing.JTextField phoneTF;
    private javax.swing.JComboBox<String> pickUpCB;
    private javax.swing.JTextField probableStayTF;
    private javax.swing.JTextField professionTF;
    private javax.swing.JTextField reservationMadeByTF;
    private javax.swing.JTextField roomNoTF;
    private javax.swing.JTextField roomTarrifTF;
    private javax.swing.JComboBox<String> roomTypeCB;
    // End of variables declaration//GEN-END:variables
}
