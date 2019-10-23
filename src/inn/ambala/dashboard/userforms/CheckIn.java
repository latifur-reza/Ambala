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
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class CheckIn extends javax.swing.JPanel {

    private static DefaultTableModel vacaentRoomsModel = null;
    private static DefaultTableModel dataTableModel = null;
    /**
     * Creates new form Check
     */
    public CheckIn() {
        initComponents();
        initExtraComponents();
    }
    
    private void initExtraComponents(){
        comboBox();
        vacaentRoomsModel = (DefaultTableModel) vacaentRoomsTable.getModel();
        dataTableModel = (DefaultTableModel) dataTable.getModel();
        refreshTable();
        clearForm();
        dataTable.setBackground(Color.white);
        
    }
    
    public static void refreshTable(){
        new RoomDetailsCrud(vacaentRoomsModel).showCheckedOutRooms();
        showCurrentCheckIns();
        
        
    }
    
    private static void showCurrentCheckIns(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dataTableModel.setRowCount(0);
        ArrayList<UserInfoModel> allUserInfo = UserInfoCrud.getAllUserInfo();
        ArrayList<UsersRoomInfoModel> allUserRoomInfo = UserRoomInfoCrud.getAllUserRoomInfo();
        for(UsersRoomInfoModel urim : allUserRoomInfo){
            if(urim.getStatus().equals("CheckedIn")){
                for(UserInfoModel uim : allUserInfo){
                    if(urim.getMrNo().equals(uim.getMrNo())){
                        dataTableModel.addRow(new Object[]{urim.getMrNo(),urim.getRoomNo(),uim.getName(),sdf.format(urim.getArrivalDate()),urim.getStatus(),""});
                        
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
    
    private void checkInButtonPressed(){
        if(nameTF.getText().equals("")|| addressTF.getText().equals("")||
                nationalityTF.getText().equals("") || roomNoTF.getText().equals("") ||
                roomTarrifTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Fill Up The Form Carefully");
        }
        else{
            checkInData();
            refreshTable();
            AdvancePayment.refreshTable();
            RoomTransfer.refreshTable();
            CheckOut.refreshTable();
            Laundry.showCurrentCheckIns();
            Restaurant.showCurrentCheckIns();
            Others.showCurrentCheckIns();
            //JOptionPane.showMessageDialog(null, "Successfully Checked In");
        }
        
    }
    
    private void checkInData(){
        boolean flag = true;
        KeyGenerator key = new KeyGenerator();
        String mrNo = key.getKey();
        
        //UserInfo
        ////String mrNo;
        String name = nameTF.getText();;
        String address = addressTF.getText();
        String companyName = companyNameTF.getText();
        if(companyNameTF.getText().equals("")){
            companyName = "N/A";
        }
        String profession = professionTF.getText();
        if(professionTF.getText().equals("")){
            profession = "N/A";
        }
        String nationality = nationalityTF.getText();
        String passportNo = passportNoTF.getText();
        if(passportNoTF.getText().equals("")){
            passportNo = "N/A";
        }
        
        Date dateOfBirth = dateOfBirthDC.getDate();
        if(dateOfBirthDC.getDate() == null){
            dateOfBirth = new Date();
        }
        
        String email = emailTF.getText();
        if(emailTF.getText().equals("")){
            email = "N/A";
        }
        String phone = phoneTF.getText();
        if(phoneTF.getText().equals("")){
            phone = "N/A";
        }
        
        
        //UsersRoomInfo
        ////String mrNo;
        int roomNo = 0;
        try{
            if(!roomNoTF.getText().equals("")){
                roomNo = Integer.valueOf(roomNoTF.getText());
            }else{
                roomNo = 0;
            }
            //check room status;
            String status = RoomDetailsCrud.getRoomStatus(roomNo);
            if(!status.equals("CheckedOut"))
            {
                flag = false;
                JOptionPane.showMessageDialog(null, "Wrong Room Number");
            }
        }catch(Exception e){
            if(flag){
                JOptionPane.showMessageDialog(null, "Enter Digits Only");
            }
            flag = false;
        }
        String roomType = (String) roomTypeCB.getSelectedItem();
        Date arrivalDate = arrivalDateDC.getDate();
        if(arrivalDateDC.getDate() == null){
            arrivalDate = new Date();
        }
        Date departureDate = departureDateDC.getDate();
        if(departureDateDC.getDate() == null){
            departureDate = new Date();
        }
        int discountPercent = 0;
        try{
            if(!discountPerTF.getText().equals("")){
                discountPercent = Integer.valueOf(discountPerTF.getText());
            }
            else{
                discountPercent = 0;
            }
        }catch(Exception e){
            if(flag){
                JOptionPane.showMessageDialog(null, "Enter Digits Only");
            }
            flag = false;
        }
        int discountCash = 0;
        try{
            if(!discountTkTF.getText().equals("")){
                discountCash = Integer.valueOf(discountTkTF.getText());
            }
            else{
                discountCash = 0;
            }
        }catch(Exception e){
            if(flag){
                JOptionPane.showMessageDialog(null, "Enter Digits Only");
            }
            flag = false;
        }
        int roomTarrif = 0;
        try{
            if(!roomTarrifTF.getText().equals("")){
                roomTarrif = Integer.valueOf(roomTarrifTF.getText());
            }
            else{
                roomTarrif = 0;
            }
        }catch(Exception e){
            if(flag){
                JOptionPane.showMessageDialog(null, "Enter Digits Only");
            }
            flag = false;
        }
        int roomBill = 0;
        String status = "CheckedIn";

        //UsersOthersInfo
        
        ////String mrNo;
        ////int roomNo;
        String registrationMadeBy = reservationMadeByTF.getText();
        if(reservationMadeByTF.getText().equals("")){
            registrationMadeBy = "N/A";
        }
        String localCompany = localCompanyNameTF.getText();
        if(localCompanyNameTF.getText().equals("")){
            localCompany = "N/A";
        }
        String localCompanyAddress = localCompanyAddressTF.getText();
        if(localCompanyAddressTF.getText().equals("")){
            localCompanyAddress = "N/A";
        }
        String probableStay = probableStayTF.getText();
        if(probableStayTF.getText().equals("")){
            probableStay = "N/A";
        }
        int totalBillCollected = 0;
        String whereIssued = whereIssuedTF.getText();
        if(whereIssuedTF.getText().equals("")){
            whereIssued = "N/A";
        }
        int noOfGuest =1;
        try{
            if(!noOfGuestTF.getText().equals("")){
                noOfGuest = Integer.valueOf(noOfGuestTF.getText());
            }
            else{
                noOfGuest = 1;
            }
        }catch(Exception e){
            if(flag){
                JOptionPane.showMessageDialog(null, "Enter Digits Only");
            }
            flag = false;
        }
        
        
        UserInfoModel userInfoModel = new UserInfoModel(mrNo, name, address, companyName, profession, nationality, passportNo, dateOfBirth, email, phone);
        UserInfoCrud.addUserInfo(userInfoModel);
        
        UsersRoomInfoModel usersRoomInfoModel = new UsersRoomInfoModel(mrNo, roomNo, roomType, arrivalDate, departureDate, discountPercent, discountCash, roomTarrif, roomBill, status);
        UserRoomInfoCrud.addUserRoomInfo(usersRoomInfoModel);
        
        UserOthersInfoModel userOthersInfoModel = new UserOthersInfoModel(mrNo, roomNo, registrationMadeBy, localCompany, localCompanyAddress, probableStay, totalBillCollected, whereIssued, noOfGuest);
        UserOthersInfoCrud.addUserOthersInfo(userOthersInfoModel);
        
        RoomDetailsCrud.updateRoomStatus(roomNo, status);
        clearForm();
        flag = true;
        JOptionPane.showMessageDialog(null, "Checked In Successfully");
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
        roomNoTF.setText("");
        roomTypeCB.setSelectedIndex(0);
        //arrivalDateDC.setDateFormatString("");
        //departureDateDC.setDateFormatString("");
        discountPerTF.setText("0");
        discountTkTF.setText("0");
        roomTarrifTF.setText("");
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
        whereIssuedTF.setText("");
        noOfGuestTF.setText("1");
    }
    
    private void clearButtonPressed(){
        clearForm();
    }
    
    private void vacaentRoomsTableMouseClicked(){
        int rowNo = vacaentRoomsTable.getSelectedRow();
        roomNoTF.setText(vacaentRoomsTable.getModel().getValueAt(rowNo, 0).toString());
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
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guestRegistrationFormLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        reservationMadeByTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        roomNoTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        roomTypeCB = new javax.swing.JComboBox<>();
        localCompanyNameTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        localCompanyAddressTF = new javax.swing.JTextField();
        addressTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nationalityTF = new javax.swing.JTextField();
        professionTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
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
        jLabel20 = new javax.swing.JLabel();
        whereIssuedTF = new javax.swing.JTextField();
        dateOfBirthDC = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        departureDateDC = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        arrivalDateDC = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        vacaentRoomsTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        checkInButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        probableStayTF = new javax.swing.JTextField();

        guestRegistrationFormLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        guestRegistrationFormLabel.setText("Guest Registration Form");

        jLabel2.setText("Reservation Made by");

        reservationMadeByTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservationMadeByTFActionPerformed(evt);
            }
        });

        jLabel3.setText("Name");

        jLabel4.setText("Room No.");

        jLabel5.setText("Room Type");

        jLabel6.setText("Address");

        jLabel7.setText("Local Company Name");

        roomTypeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomTypeCBActionPerformed(evt);
            }
        });

        localCompanyNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localCompanyNameTFActionPerformed(evt);
            }
        });

        jLabel8.setText("Address");

        localCompanyAddressTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localCompanyAddressTFActionPerformed(evt);
            }
        });

        jLabel9.setText("Nationality");

        jLabel10.setText("Profession");

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

        jLabel17.setText("Phone");

        jLabel18.setText("Discount (%)");

        jLabel19.setText("Discount (Tk)");

        jLabel20.setText("Passport No 2");

        dateOfBirthDC.setDateFormatString("yyyy-MM-dd");

        jLabel21.setText("Date of Birth");

        jLabel22.setText("Departure Date");

        departureDateDC.setDateFormatString("yyyy-MM-dd");

        jLabel23.setText("Arrival Date");

        arrivalDateDC.setDateFormatString("yyyy-MM-dd");

        vacaentRoomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rooms"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vacaentRoomsTable.getTableHeader().setReorderingAllowed(false);
        vacaentRoomsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vacaentRoomsTableMouseClicked(evt);
            }
        });
        vacaentRoomsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vacaentRoomsTableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(vacaentRoomsTable);
        if (vacaentRoomsTable.getColumnModel().getColumnCount() > 0) {
            vacaentRoomsTable.getColumnModel().getColumn(0).setResizable(false);
        }

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MR No.", "Room No.", "Guest Name", "Arrival Date", "Room Status", "PABx Ext"
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

        checkInButton.setBackground(new java.awt.Color(58, 179, 73));
        checkInButton.setForeground(new java.awt.Color(255, 255, 255));
        checkInButton.setText("Check In");
        checkInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(252, 0, 0));
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(roomTarrifTF)
                                            .addComponent(roomNoTF)
                                            .addComponent(roomTypeCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(discountPerTF)
                                            .addComponent(discountTkTF)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel18)
                                                    .addComponent(jLabel19))
                                                .addGap(0, 33, Short.MAX_VALUE))
                                            .addComponent(probableStayTF))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel3)
                                                        .addComponent(nameTF)
                                                        .addComponent(jLabel6)
                                                        .addComponent(addressTF)
                                                        .addComponent(jLabel10)
                                                        .addComponent(companyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel11))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(localCompanyAddressTF)
                                                            .addComponent(jLabel9)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(reservationMadeByTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(localCompanyNameTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel8)
                                                                    .addComponent(jLabel7)
                                                                    .addComponent(jLabel2))
                                                                .addGap(0, 0, Short.MAX_VALUE))))))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel14)
                                                    .addComponent(professionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel15)
                                                    .addComponent(nationalityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(passportNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(whereIssuedTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel20)
                                                            .addComponent(jLabel23)
                                                            .addComponent(arrivalDateDC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(departureDateDC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(dateOfBirthDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel22)
                                                                    .addComponent(jLabel21))
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(phoneTF)
                                                    .addComponent(emailTF)
                                                    .addComponent(noOfGuestTF)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel17)
                                                            .addComponent(jLabel16))
                                                        .addGap(0, 0, Short.MAX_VALUE))))))
                                    .addComponent(jScrollPane3))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(checkInButton)
                                .addGap(18, 18, 18)
                                .addComponent(clearButton)
                                .addGap(223, 223, 223)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(guestRegistrationFormLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(guestRegistrationFormLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reservationMadeByTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(roomTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(localCompanyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(localCompanyAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(companyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roomTarrifTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(professionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nationalityTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(passportNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(noOfGuestTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(probableStayTF, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel20))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(discountPerTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(whereIssuedTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateOfBirthDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(discountTkTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(arrivalDateDC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(departureDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkInButton)
                            .addComponent(clearButton)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void localCompanyAddressTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localCompanyAddressTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_localCompanyAddressTFActionPerformed

    private void companyNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companyNameTFActionPerformed

    private void checkInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkInButtonActionPerformed
        checkInButtonPressed();
    }//GEN-LAST:event_checkInButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearButtonPressed();
        
    }//GEN-LAST:event_clearButtonActionPerformed

    private void vacaentRoomsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vacaentRoomsTableMouseClicked
        vacaentRoomsTableMouseClicked();
    }//GEN-LAST:event_vacaentRoomsTableMouseClicked

    private void roomTypeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomTypeCBActionPerformed
        roomTypeCBAction();
    }//GEN-LAST:event_roomTypeCBActionPerformed

    private void localCompanyNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localCompanyNameTFActionPerformed
        localCompanyNameAction();
    }//GEN-LAST:event_localCompanyNameTFActionPerformed

    private void vacaentRoomsTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vacaentRoomsTableKeyReleased
        vacaentRoomsTableMouseClicked();
    }//GEN-LAST:event_vacaentRoomsTableKeyReleased

    private void reservationMadeByTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationMadeByTFActionPerformed
        reservationAction();
    }//GEN-LAST:event_reservationMadeByTFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTF;
    private com.toedter.calendar.JDateChooser arrivalDateDC;
    private javax.swing.JButton checkInButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField companyNameTF;
    private javax.swing.JTable dataTable;
    private com.toedter.calendar.JDateChooser dateOfBirthDC;
    private com.toedter.calendar.JDateChooser departureDateDC;
    private javax.swing.JTextField discountPerTF;
    private javax.swing.JTextField discountTkTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JLabel guestRegistrationFormLabel;
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
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField localCompanyAddressTF;
    private javax.swing.JTextField localCompanyNameTF;
    private javax.swing.JTextField nameTF;
    private javax.swing.JTextField nationalityTF;
    private javax.swing.JTextField noOfGuestTF;
    private javax.swing.JTextField passportNoTF;
    private javax.swing.JTextField phoneTF;
    private javax.swing.JTextField probableStayTF;
    private javax.swing.JTextField professionTF;
    private javax.swing.JTextField reservationMadeByTF;
    private javax.swing.JTextField roomNoTF;
    private javax.swing.JTextField roomTarrifTF;
    private javax.swing.JComboBox<String> roomTypeCB;
    private javax.swing.JTable vacaentRoomsTable;
    private javax.swing.JTextField whereIssuedTF;
    // End of variables declaration//GEN-END:variables
}
