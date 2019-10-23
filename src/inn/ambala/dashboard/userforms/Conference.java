/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms;

import inn.ambala.admin.model.CompanyInfoModel;
import inn.ambala.admin.model.CompanyInfoTransactionModel;
import inn.ambala.dashboard.userforms.conference.model.AmountDetailsModel;
import inn.ambala.dashboard.userforms.conference.model.BookingDetailsModel;
import inn.ambala.dashboard.userforms.conference.model.PaymentDetailsModel;
import inn.ambala.dashboard.userforms.print.ConferenceBookingPrint;
import inn.ambala.dashboard.userforms.print.ConferencePaymentPrint;
import inn.ambala.databaseconnection.admin.CompanyInfoCrud;
import inn.ambala.databaseconnection.admin.CompanyInfoTransactionCurd;
import inn.ambala.databaseconnection.userforms.conference.AmountDetailsCrud;
import inn.ambala.databaseconnection.userforms.conference.BookingDetailsCrud;
import inn.ambala.databaseconnection.userforms.conference.PaymentDetailsCrud;
import inn.ambala.keypicker.KeyGenerator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class Conference extends javax.swing.JPanel {

    private static DefaultTableModel model = null;
    /**
     * Creates new form Conference
     */
    public Conference() {
        initComponents();
        initExtraComponents();
        
    }
    
    private void initExtraComponents(){
        clearForm();
        model = (DefaultTableModel) dataTable.getModel();
        refreshTable();
    }
    
    private void refreshTable(){
        model.setRowCount(0);
        ArrayList<BookingDetailsModel> allBookings = BookingDetailsCrud.getBookingDetails();
        for(BookingDetailsModel bdm : allBookings){
            if(bdm.getStatus().equals("Booked")){
                model.addRow(new Object[]{bdm.getVoucherNo(),bdm.getCompany(),bdm.getFrom(),bdm.getTo(),bdm.getStatus()});
                
            }
        }
    }
    
    private void bookedButtonPressed(){
        if(companyTF.getText().equals("") || addressTF.getText().equals("") || areaTF.getText().equals("") || 
                fromDC.getDateFormatString().equals("")){
            JOptionPane.showConfirmDialog(null, "Fill the form Carefully");
        }
        else{
            try{
                Date arrivalDate = arrivalDateDC.getDate();
                if(arrivalDateDC.getDate() == null){
                    arrivalDate = new Date();
                }
                KeyGenerator key = new KeyGenerator();
                String voucharNo = key.getKey();
                String company = companyTF.getText();
                if(companyTF.getText().equals("")){
                    company = "N/A";
                }
                String address = addressTF.getText();
                String area = areaTF.getText();
                if(areaTF.getText().equals("")){
                    area = "N/A";
                }
                String areaCode = areaCodeTF.getText();
                if(areaCodeTF.getText().equals("")){
                    areaCode = "N/A";
                }
                String reservation = reservationTF.getText();
                if(reservationTF.getText().equals("")){
                    reservation = "N/A";
                }
                Date from = fromDC.getDate();
                if(fromDC.getDate() == null){
                    from = new Date();
                }
                Date to = toDC.getDate();
                if(toDC.getDate() == null){
                    to = new Date();
                }
                String reason = forTF.getText();
                if(forTF.getText().equals("")){
                    reason = "N/A";
                }
                String status = "Booked";
                BookingDetailsModel bookingDetailsModel = new BookingDetailsModel(voucharNo, arrivalDate, company, address, area, areaCode, reservation, from, to, reason, status);

                if(rentTF.getText().equals("")){
                    rentTF.setText("0");
                }
                if(foodChargesTF.getText().equals("")){
                    foodChargesTF.setText("0");
                }
                if(projectorTF.getText().equals("")){
                    projectorTF.setText("0");
                }
                if(ospTF.getText().equals("")){
                    ospTF.setText("0");
                }
                if(videosTF.getText().equals("")){
                    videosTF.setText("0");
                }
                if(soundSystemTF.getText().equals("")){
                    soundSystemTF.setText("0");
                }
                if(othersTF.getText().equals("")){
                    othersTF.setText("0");
                }
                if(serviceChargeTF.getText().equals("")){
                    serviceChargeTF.setText("0");
                }
                if(vatTF.getText().equals("")){
                    vatTF.setText("0");
                }
                if(discountTF.getText().equals("")){
                    discountTF.setText("0");
                }
                if(totalApTF.getText().equals("")){
                    totalApTF.setText("0");
                }
                if(advanceTF.getText().equals("")){
                    advanceTF.setText("0");
                }
                if(dueTF.getText().equals("")){
                    dueTF.setText("0");
                }
                
                int rent = Integer.valueOf(rentTF.getText());
                int foodCharges = Integer.valueOf(foodChargesTF.getText());
                int projector = Integer.valueOf(projectorTF.getText());
                int osp = Integer.valueOf(ospTF.getText());
                int videos = Integer.valueOf(videosTF.getText());
                int soundSystem = Integer.valueOf(soundSystemTF.getText());
                int others = Integer.valueOf(othersTF.getText());
                int serviceCharge = Integer.valueOf(serviceChargeTF.getText());
                int vat = Integer.valueOf(vatTF.getText());
                int discount = Integer.valueOf(discountTF.getText());
                int total = Integer.valueOf(totalTF.getText());
                AmountDetailsModel amountDetailsModel = new AmountDetailsModel(voucharNo, rent, foodCharges, projector, osp, videos, soundSystem, others, discount, total);

                int totalAp = Integer.valueOf(totalApTF.getText());
                int advance = Integer.valueOf(advanceTF.getText());
                int due = Integer.valueOf(dueTF.getText());
                Date today = new Date();
                PaymentDetailsModel paymentDetailsModel = new PaymentDetailsModel(voucharNo, total, advance, today, due);

                BookingDetailsCrud.addBookingDetails(bookingDetailsModel);
                AmountDetailsCrud.addPaymentDetails(amountDetailsModel);
                PaymentDetailsCrud.addPaymentDetails(paymentDetailsModel);

                clearForm();
                refreshTable();
                JOptionPane.showMessageDialog(null, "Booked Successfully");
                new ConferenceBookingPrint(bookingDetailsModel, amountDetailsModel, paymentDetailsModel, vat, serviceCharge).setVisible(true);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Wrong Entry");
            }
        }
    }
    
    private void payPrintReady(){
        if(voucherNoTF.getText().equals("") || companyTF.getText().equals("") || addressTF.getText().equals("") || areaTF.getText().equals("") || 
                fromDC.getDateFormatString().equals("")){
            JOptionPane.showConfirmDialog(null, "Fill the form Carefully");
        }
        else{
            try{
                Date arrivalDate = arrivalDateDC.getDate();
                if(arrivalDateDC.getDate() == null){
                    arrivalDate = new Date();
                }
                //KeyGenerator key = new KeyGenerator();
                String voucharNo = voucherNoTF.getText();
                String company = companyTF.getText();
                if(companyTF.getText().equals("")){
                    company = "N/A";
                }
                String address = addressTF.getText();
                String area = areaTF.getText();
                if(areaTF.getText().equals("")){
                    area = "N/A";
                }
                String areaCode = areaCodeTF.getText();
                if(areaCodeTF.getText().equals("")){
                    areaCode = "N/A";
                }
                String reservation = reservationTF.getText();
                if(reservationTF.getText().equals("")){
                    reservation = "N/A";
                }
                Date from = fromDC.getDate();
                if(fromDC.getDate() == null){
                    from = new Date();
                }
                Date to = toDC.getDate();
                if(toDC.getDate() == null){
                    to = new Date();
                }
                String reason = forTF.getText();
                if(forTF.getText().equals("")){
                    reason = "N/A";
                }
                String status = "Booked";
                BookingDetailsModel bookingDetailsModel = new BookingDetailsModel(voucharNo, arrivalDate, company, address, area, areaCode, reservation, from, to, reason, status);

                if(rentTF.getText().equals("")){
                    rentTF.setText("0");
                }
                if(foodChargesTF.getText().equals("")){
                    foodChargesTF.setText("0");
                }
                if(projectorTF.getText().equals("")){
                    projectorTF.setText("0");
                }
                if(ospTF.getText().equals("")){
                    ospTF.setText("0");
                }
                if(videosTF.getText().equals("")){
                    videosTF.setText("0");
                }
                if(soundSystemTF.getText().equals("")){
                    soundSystemTF.setText("0");
                }
                if(othersTF.getText().equals("")){
                    othersTF.setText("0");
                }
                if(serviceChargeTF.getText().equals("")){
                    serviceChargeTF.setText("0");
                }
                if(vatTF.getText().equals("")){
                    vatTF.setText("0");
                }
                if(discountTF.getText().equals("")){
                    discountTF.setText("0");
                }
                if(totalApTF.getText().equals("")){
                    totalApTF.setText("0");
                }
                if(advanceTF.getText().equals("")){
                    advanceTF.setText("0");
                }
                if(dueTF.getText().equals("")){
                    dueTF.setText("0");
                }
                
                int rent = Integer.valueOf(rentTF.getText());
                int foodCharges = Integer.valueOf(foodChargesTF.getText());
                int projector = Integer.valueOf(projectorTF.getText());
                int osp = Integer.valueOf(ospTF.getText());
                int videos = Integer.valueOf(videosTF.getText());
                int soundSystem = Integer.valueOf(soundSystemTF.getText());
                int others = Integer.valueOf(othersTF.getText());
                int serviceCharge = Integer.valueOf(serviceChargeTF.getText());
                int vat = Integer.valueOf(vatTF.getText());
                int discount = Integer.valueOf(discountTF.getText());
                int total = Integer.valueOf(totalTF.getText());
                AmountDetailsModel amountDetailsModel = new AmountDetailsModel(voucharNo, rent, foodCharges, projector, osp, videos, soundSystem, others, discount, total);

                int totalAp = Integer.valueOf(totalApTF.getText());
                int advance = Integer.valueOf(advanceTF.getText());
                int due = Integer.valueOf(dueTF.getText());
                Date today = new Date();
                PaymentDetailsModel paymentDetailsModel = new PaymentDetailsModel(voucharNo, total, advance, today, due);
                int finalPay = Integer.valueOf(paymentTF.getText());
                clearForm();
                refreshTable();
                
                new ConferencePaymentPrint(bookingDetailsModel, amountDetailsModel, paymentDetailsModel, vat, serviceCharge, finalPay).setVisible(true);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Wrong Entry");
            }
        }
    }
    
    private void clearForm(){
        voucherNoTF.setText("");
        companyTF.setText("");
        addressTF.setText("");
        areaTF.setText("");
        areaCodeTF.setText("");
        reservationTF.setText("");
        forTF.setText("");
        
        rentTF.setText("0");
        foodChargesTF.setText("0");
        projectorTF.setText("0");
        ospTF.setText("0");
        videosTF.setText("0");
        soundSystemTF.setText("0");
        othersTF.setText("0");
        serviceChargeTF.setText("0");
        vatTF.setText("0");
        discountTF.setText("0");
        totalTF.setText("0");
        
        totalApTF.setText("0");
        advanceTF.setText("0");
        dueTF.setText("0");
        
        totalPaTF.setText("0");
        duePTF.setText("0");
        paymentTF.setText("0");
    }
    
    private void totalTFAction(){
        try{
            if(rentTF.getText().equals("")){
                rentTF.setText("0");
            }
            if(foodChargesTF.getText().equals("")){
                foodChargesTF.setText("0");
            }
            if(projectorTF.getText().equals("")){
                projectorTF.setText("0");
            }
            if(ospTF.getText().equals("")){
                ospTF.setText("0");
            }
            if(videosTF.getText().equals("")){
                videosTF.setText("0");
            }
            if(soundSystemTF.getText().equals("")){
                soundSystemTF.setText("0");
            }
            if(othersTF.getText().equals("")){
                othersTF.setText("0");
            }
            if(discountTF.getText().equals("")){
                discountTF.setText("0");
            }
            
            int rent = Integer.valueOf(rentTF.getText());
            int foodCharges = Integer.valueOf(foodChargesTF.getText());
            int projector = Integer.valueOf(projectorTF.getText());
            int osp = Integer.valueOf(ospTF.getText());
            int videos = Integer.valueOf(videosTF.getText());
            int soundSystem = Integer.valueOf(soundSystemTF.getText());
            int others = Integer.valueOf(othersTF.getText());
            int total = rent + foodCharges + projector + osp + videos + soundSystem + others;

            int serviceChargeCalc = (total * 10)/100;
            int vatCalc = (total * 15)/100;
            serviceChargeTF.setText(serviceChargeCalc + "");
            vatTF.setText(vatCalc + "");
            int discount = Integer.valueOf(discountTF.getText());
            totalTF.setText(total+serviceChargeCalc+vatCalc-discount + "");
            totalApTF.setText(total+serviceChargeCalc+vatCalc-discount + "");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Wrong Entry");
        }
    }
    
    private void advanceTFAction(){
        try{
            totalTFAction();
            int totalAp = Integer.valueOf(totalApTF.getText());
            int advance = Integer.valueOf(advanceTF.getText());

            dueTF.setText(totalAp - advance + "");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Wrong Entry");
        }
    }

    private void voucharNoTFAction(){
        String voucharNo = voucherNoTF.getText();
        ArrayList<AmountDetailsModel> allAmounts = AmountDetailsCrud.getAmountDetails();
        ArrayList<BookingDetailsModel> allBookings = BookingDetailsCrud.getBookingDetails();
        ArrayList<PaymentDetailsModel> allPayments = PaymentDetailsCrud.getPaymentDetails();
        
        for(BookingDetailsModel bdm : allBookings){
            if(bdm.getVoucherNo().equals(voucharNo)){
                arrivalDateDC.setDate(bdm.getArrivalDate());
                fromDC.setDate(bdm.getFrom());
                toDC.setDate(bdm.getTo());
                companyTF.setText(bdm.getCompany());
                addressTF.setText(bdm.getAddress());
                areaTF.setText(bdm.getArea());
                areaCodeTF.setText(bdm.getAreaCode());
                reservationTF.setText(bdm.getReservation());
                forTF.setText(bdm.getReason());
                
            }
        }
        
        
        for(AmountDetailsModel adm : allAmounts){
            if(adm.getVoucharNo().equals(voucharNo)){
                rentTF.setText(adm.getRent() + "");
                foodChargesTF.setText(adm.getFoodCharges()+ "");
                projectorTF.setText(adm.getProjector()+ "");
                ospTF.setText(adm.getOsp()+ "");
                videosTF.setText(adm.getVideos() + "");
                soundSystemTF.setText(adm.getSoundSystem()+ "");
                othersTF.setText(adm.getOthers()+ "");
                discountTF.setText(adm.getDiscount()+ "");
                totalTF.setText(adm.getTotal() + "");
                totalTFAction();
                totalPaTF.setText(adm.getTotal() + "");
            }
        }
        
        for(PaymentDetailsModel pdm : allPayments){
            if(pdm.getVoucharNo().equals(voucharNo)){
                advanceTF.setText("" + pdm.getPayment());
                duePTF.setText(pdm.getDue() + "");
            }
        }
        
    }
    
    private void paymentTFAction(){
        payButtonPressed();
    }
    
    private void payButtonPressed(){
        if(voucherNoTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter Correct Vouchar No");
        }
        else if(companyTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter Company Name");
        }
        else{
            try{
                KeyGenerator key = new KeyGenerator();
                String id = key.getKey();
                int companyCode = -1;
                ArrayList<CompanyInfoModel> allCompany = new ArrayList<>();
                for(CompanyInfoModel cim : allCompany){
                    if(cim.getCompanyName().equals(companyTF.getText())){
                        companyCode = cim.getCompanyCode();
                    }
                }
                Date date = new Date();
                String status = "Complete Conference";
                String reason = "Conference Due";
                if(totalPaTF.getText().equals("")){
                    totalPaTF.setText("0");
                }
                if(duePTF.getText().equals("")){
                    duePTF.setText("0");
                }
                if(paymentTF.getText().equals("")){
                    paymentTF.setText("0");
                }
                
                int total = Integer.valueOf(totalPaTF.getText());
                int due = Integer.valueOf(duePTF.getText());
                int payment = Integer.valueOf(paymentTF.getText());
                String companyName = companyTF.getText();
                int amount = (-1) * due;
                
                if(due == payment){
                    due = due - payment;
                    payment = total - due;
                    Date today = new Date();
                    String voucharNo = voucherNoTF.getText();
                    PaymentDetailsModel pdm = new PaymentDetailsModel(voucharNo, total, payment, today, due);
                    PaymentDetailsCrud.updatePaymentDetails(pdm);
                    
                    JOptionPane.showMessageDialog(null, "Successfully Paid");
                    payPrintReady();
                }
                
                else if(due <= payment){
                    payment = total;
                    due = 0;
                    Date today = new Date();
                    String voucharNo = voucherNoTF.getText();
                    PaymentDetailsModel pdm = new PaymentDetailsModel(voucharNo, total, payment, today, due);
                    PaymentDetailsCrud.updatePaymentDetails(pdm);
                    
                    if(due != payment){
                        int exchange = Integer.valueOf(duePTF.getText()) - Integer.valueOf(paymentTF.getText());
                        JOptionPane.showMessageDialog(null, "Exchange Amount : " + exchange);
                    }
                    JOptionPane.showMessageDialog(null, "Successfully Paid");
                    payPrintReady();
                }
                else{
                    int result = CompanyInfoCrud.updateCompanyBalance(companyName, amount);
                    if(result>0){
                        CompanyInfoTransactionModel citm = new CompanyInfoTransactionModel(id, companyCode, companyName, amount, status, reason, voucherNoTF.getText(), date);
                        CompanyInfoTransactionCurd.addTransaction(citm);

                        due = due - payment;
                        payment = total - due;
                        Date today = new Date();
                        String voucharNo = voucherNoTF.getText();
                        PaymentDetailsModel pdm = new PaymentDetailsModel(voucharNo, total, payment, today, due);
                        PaymentDetailsCrud.updatePaymentDetails(pdm);

                        JOptionPane.showMessageDialog(null, "Successfully Paid");
                        payPrintReady();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Enter Correct Company Name!");
                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Wrong Entry");
            }

        }
        
    }
    
    private void tableMouseClick(){
        int rowNo = dataTable.getSelectedRow();
        voucherNoTF.setText(dataTable.getModel().getValueAt(rowNo, 0).toString());
        voucharNoTFAction();
        totalApTF.setText("0");
        //advanceTF.setText("0");
        dueTF.setText("0");
    }
    
    private void cancelButtonPressed(){
        int rowNo = dataTable.getSelectedRow();
        if(rowNo>=0){
            voucherNoTF.setText(dataTable.getModel().getValueAt(rowNo, 0).toString());     
            String voucharNo = voucherNoTF.getText();
            String status = "Cancel";
            BookingDetailsCrud.updateBookingDetails(voucharNo, status);
            voucherNoTF.setText("");
            refreshTable();
        }else{
            JOptionPane.showMessageDialog(null, "Select Any Row");
        }
    }
    
    private void completeButtonPressed(){
        int rowNo = dataTable.getSelectedRow();
        if(rowNo>=0){
            int due = -1;
            try{
                due = Integer.valueOf(duePTF.getText());
            }catch(Exception e){
                due = -1;
            }
            if(due == 0){
                voucherNoTF.setText(dataTable.getModel().getValueAt(rowNo, 0).toString());     
                String voucharNo = voucherNoTF.getText();
                String status = "Complete";
                BookingDetailsCrud.updateBookingDetails(voucharNo, status);
                voucherNoTF.setText("");
                clearForm();
                refreshTable();
            }else{
                JOptionPane.showMessageDialog(null, "Please Pay All The Bills");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Select Any Row");
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        forTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addressTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        toDC = new com.toedter.calendar.JDateChooser();
        areaCodeTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        companyTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        arrivalDateDC = new com.toedter.calendar.JDateChooser();
        reservationTF = new javax.swing.JTextField();
        areaTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        voucherNoTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fromDC = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        discountTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        soundSystemTF = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        othersTF = new javax.swing.JTextField();
        totalTF = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        ospTF = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        foodChargesTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        vatTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        videosTF = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        projectorTF = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rentTF = new javax.swing.JTextField();
        serviceChargeTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        dueTF = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        advanceTF = new javax.swing.JTextField();
        totalApTF = new javax.swing.JTextField();
        bookedButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        paymentTF = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        duePTF = new javax.swing.JTextField();
        totalPaTF = new javax.swing.JTextField();
        payButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        completeButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Conference Room Booking");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Booking Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jPanel1.setName(""); // NOI18N

        jLabel11.setText("For");

        jLabel5.setText("Address");

        jLabel10.setText("To");

        forTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forTFActionPerformed(evt);
            }
        });

        jLabel6.setText("Area");

        jLabel3.setText("Voucher No.");

        toDC.setDateFormatString("yyyy-MM-dd HH:mm ");

        jLabel8.setText("Reservation");

        jLabel7.setText("Area Code");

        jLabel2.setText("Arrival Date");

        arrivalDateDC.setDateFormatString("yyyy-MM-dd");

        jLabel9.setText("From");

        voucherNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voucherNoTFActionPerformed(evt);
            }
        });

        jLabel4.setText("Company");

        fromDC.setDateFormatString("yyyy-MM-dd HH:mm ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fromDC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(forTF)
                            .addComponent(reservationTF)
                            .addComponent(areaCodeTF)
                            .addComponent(areaTF)
                            .addComponent(addressTF)
                            .addComponent(voucherNoTF)
                            .addComponent(companyTF)
                            .addComponent(toDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(arrivalDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(arrivalDateDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(voucherNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(companyTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(areaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(areaCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(reservationTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Amount Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        discountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountTFActionPerformed(evt);
            }
        });

        jLabel14.setText(" Projector");

        jLabel15.setText("OSP");

        soundSystemTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundSystemTFActionPerformed(evt);
            }
        });

        jLabel19.setText("Service Charge");

        othersTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                othersTFActionPerformed(evt);
            }
        });

        totalTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTFActionPerformed(evt);
            }
        });

        jLabel18.setText("Others");

        ospTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ospTFActionPerformed(evt);
            }
        });

        jLabel21.setText("Discount");

        foodChargesTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodChargesTFActionPerformed(evt);
            }
        });

        jLabel13.setText("Food Charges");

        jLabel12.setText("Rent");

        videosTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                videosTFActionPerformed(evt);
            }
        });

        jLabel20.setText("Vat");

        jLabel16.setText("Videos");

        jLabel22.setText("Total");

        projectorTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectorTFActionPerformed(evt);
            }
        });

        jLabel17.setText("Sound System");

        rentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(foodChargesTF)
                        .addComponent(rentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ospTF)
                        .addComponent(projectorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(soundSystemTF)
                        .addComponent(videosTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(serviceChargeTF)
                        .addComponent(othersTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(discountTF)
                        .addComponent(vatTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(totalTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foodChargesTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ospTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(videosTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soundSystemTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(othersTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceChargeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vatTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addContainerGap())
        );

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vouchar No.", "Company", "Time From", "Time To", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTableMouseClicked(evt);
            }
        });
        dataTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dataTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(dataTable);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Advance Payment", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jLabel23.setText("Total");

        jLabel24.setText("Advance");

        dueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueTFActionPerformed(evt);
            }
        });

        jLabel25.setText("Due");

        advanceTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advanceTFActionPerformed(evt);
            }
        });

        totalApTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalApTFActionPerformed(evt);
            }
        });

        bookedButton.setText("Booked");
        bookedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookedButton, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(advanceTF)
                        .addComponent(totalApTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalApTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(advanceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bookedButton))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Payment", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jLabel26.setText("Total");

        jLabel27.setText("Due");

        paymentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentTFActionPerformed(evt);
            }
        });

        jLabel28.setText("Payment");

        duePTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duePTFActionPerformed(evt);
            }
        });

        payButton.setText("Pay");
        payButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(payButton, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(duePTF)
                        .addComponent(totalPaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paymentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalPaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(duePTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payButton)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        cancelButton.setBackground(new java.awt.Color(236, 0, 138));
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        completeButton.setBackground(new java.awt.Color(58, 179, 73));
        completeButton.setForeground(new java.awt.Color(255, 255, 255));
        completeButton.setText("Complete");
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(241, 241, 241))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(completeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void forTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_forTFActionPerformed

    private void soundSystemTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundSystemTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_soundSystemTFActionPerformed

    private void bookedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookedButtonActionPerformed
        bookedButtonPressed();
    }//GEN-LAST:event_bookedButtonActionPerformed

    private void payButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payButtonActionPerformed
        payButtonPressed();
    }//GEN-LAST:event_payButtonActionPerformed

    private void totalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_totalTFActionPerformed

    private void discountTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_discountTFActionPerformed

    private void totalApTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalApTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_totalApTFActionPerformed

    private void advanceTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advanceTFActionPerformed
        advanceTFAction();
    }//GEN-LAST:event_advanceTFActionPerformed

    private void dueTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueTFActionPerformed
        advanceTFAction();
        bookedButtonPressed();
    }//GEN-LAST:event_dueTFActionPerformed

    private void voucherNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voucherNoTFActionPerformed
        voucharNoTFAction();
    }//GEN-LAST:event_voucherNoTFActionPerformed

    private void duePTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duePTFActionPerformed
        //duePTFAction();
    }//GEN-LAST:event_duePTFActionPerformed

    private void paymentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentTFActionPerformed
        paymentTFAction();
    }//GEN-LAST:event_paymentTFActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancelButtonPressed();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void dataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMouseClicked
        tableMouseClick();
    }//GEN-LAST:event_dataTableMouseClicked

    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        completeButtonPressed();
    }//GEN-LAST:event_completeButtonActionPerformed

    private void dataTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataTableKeyReleased
        tableMouseClick();
    }//GEN-LAST:event_dataTableKeyReleased

    private void rentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_rentTFActionPerformed

    private void foodChargesTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodChargesTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_foodChargesTFActionPerformed

    private void projectorTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectorTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_projectorTFActionPerformed

    private void ospTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ospTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_ospTFActionPerformed

    private void videosTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videosTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_videosTFActionPerformed

    private void othersTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_othersTFActionPerformed
        totalTFAction();
    }//GEN-LAST:event_othersTFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTF;
    private javax.swing.JTextField advanceTF;
    private javax.swing.JTextField areaCodeTF;
    private javax.swing.JTextField areaTF;
    private com.toedter.calendar.JDateChooser arrivalDateDC;
    private javax.swing.JButton bookedButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField companyTF;
    private javax.swing.JButton completeButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JTextField discountTF;
    private javax.swing.JTextField duePTF;
    private javax.swing.JTextField dueTF;
    private javax.swing.JTextField foodChargesTF;
    private javax.swing.JTextField forTF;
    private com.toedter.calendar.JDateChooser fromDC;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField ospTF;
    private javax.swing.JTextField othersTF;
    private javax.swing.JButton payButton;
    private javax.swing.JTextField paymentTF;
    private javax.swing.JTextField projectorTF;
    private javax.swing.JTextField rentTF;
    private javax.swing.JTextField reservationTF;
    private javax.swing.JTextField serviceChargeTF;
    private javax.swing.JTextField soundSystemTF;
    private com.toedter.calendar.JDateChooser toDC;
    private javax.swing.JTextField totalApTF;
    private javax.swing.JTextField totalPaTF;
    private javax.swing.JTextField totalTF;
    private javax.swing.JTextField vatTF;
    private javax.swing.JTextField videosTF;
    private javax.swing.JTextField voucherNoTF;
    // End of variables declaration//GEN-END:variables
}
