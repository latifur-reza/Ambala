/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms;

import inn.ambala.admin.model.CompanyInfoModel;
import inn.ambala.admin.model.CompanyInfoTransactionModel;
import inn.ambala.dashboard.userforms.checkout.details.LaundryShow;
import inn.ambala.dashboard.userforms.checkout.details.OthersShow;
import inn.ambala.dashboard.userforms.checkout.details.RestaurantShow;
import inn.ambala.dashboard.userforms.checkout.model.PayAbout;
import inn.ambala.dashboard.userforms.checkout.model.Payment;
import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.dashboard.userforms.model.UserOthersInfoModel;
import inn.ambala.dashboard.userforms.model.UsersRoomInfoModel;
import inn.ambala.dashboard.userforms.print.CheckOutPrint;
import inn.ambala.databaseconnection.admin.CompanyInfoCrud;
import inn.ambala.databaseconnection.admin.CompanyInfoTransactionCurd;
import inn.ambala.databaseconnection.admin.RoomDetailsCrud;
import inn.ambala.databaseconnection.userforms.addforms.LaundryAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.OthersAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.RestaurantAddCrud;
import inn.ambala.databaseconnection.userforms.addforms.ServicesMiniCrud;
import inn.ambala.databaseconnection.userforms.checkout.PayAboutCrud;
import inn.ambala.databaseconnection.userforms.checkout.PaymentCrud;
import inn.ambala.databaseconnection.userforms.forms.UserInfoCrud;
import inn.ambala.databaseconnection.userforms.forms.UserOthersInfoCrud;
import inn.ambala.databaseconnection.userforms.forms.UserRoomInfoCrud;
import inn.ambala.keypicker.KeyGenerator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class CheckOut extends javax.swing.JPanel {

    private static DefaultTableModel dataTableModel = null;
    UsersRoomInfoModel usersRoomInfoModel;
    /**
     * Creates new form CheckOut
     */
    public CheckOut() {
        initComponents();
        initExtraComponents();
    }
    
    private void initExtraComponents(){
        comboBoxPaymentMethods();
        comboBoxPaymentCurrency();
        dataTableModel = (DefaultTableModel) dataTable.getModel();
        refreshTable();
        clearForm();
    }
    
    public static void refreshTable(){
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
    
    private void comboBoxPaymentMethods() {
        ArrayList<String> paymentMethods = new ArrayList<String>();
        paymentMethods.add("Cash");
        paymentMethods.add("Amex");
        paymentMethods.add("Credit Card");
        paymentMethods.add("Master Card");
        paymentMethods.add("Visa Card");
        paymentMethods.add("Others");
        
        paymentMethodCB.removeAllItems();
        for (String types : paymentMethods) {
            paymentMethodCB.addItem(types);
        }
        paymentMethodCB.setSelectedIndex(0);
    }
    
    
    private void comboBoxPaymentCurrency() {
        ArrayList<String> paymentCurrency = new ArrayList<String>();
        paymentCurrency.add("Taka");
        /*
        paymentCurrency.add("Rupee");
        paymentCurrency.add("US Doller");
        paymentCurrency.add("Euro");
        paymentCurrency.add("Pound");
        */
        paymentCurrencyCB.removeAllItems();
        for (String types : paymentCurrency) {
            paymentCurrencyCB.addItem(types);
        }
        paymentCurrencyCB.setSelectedIndex(0);
    }
    
    private void dataTableMouseClickAction(){
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        departureDateTF.setText(sdf.format(today));
        int rowNo = dataTable.getSelectedRow();
        mrNoTF.setText(dataTable.getModel().getValueAt(rowNo, 0).toString());
        roomNoTF.setText(dataTable.getModel().getValueAt(rowNo, 1).toString());
        arrivalDateTF.setText(dataTable.getModel().getValueAt(rowNo, 3).toString());
        checkOutCalculate();
    }
    
    private void checkOutCalculate(){
        boolean flag = true;
        String mrNo = mrNoTF.getText();
        if(mrNoTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter MR Number");
        }
        else if(mrNoTF.getText().length()!=8){
            JOptionPane.showMessageDialog(null, "Wrong MR Number");
        }else{
            try{
                usersRoomInfoModel = UserRoomInfoCrud.getSingleUserRoomInfo(mrNo);
                int laundryBill = LaundryAddCrud.getSingleLaundryTotal(mrNo);
                int restaurantBill = RestaurantAddCrud.getSingleRestaurantTotal(mrNo);
                int othersBill = OthersAddCrud.getSingleOthersTotal(mrNo);
                int paidBill = ServicesMiniCrud.getSingleServiceTotal(mrNo);
                if(!usersRoomInfoModel.getMrNo().equals("")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date arrivalDate = usersRoomInfoModel.getArrivalDate();
                    String arrivalStr = sdf.format(arrivalDate);
                    Date today = new Date();
                    String todayStr = sdf.format(today);
                    long days = 1;
                    try{
                        Date arrival = sdf.parse(arrivalStr);
                        Date now = sdf.parse(todayStr);
                        long difference = now.getTime() - arrival.getTime();
                        days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                        if(days==0){
                            days = 1;
                        }
                    }catch(Exception e){

                    }
                    roomTypeTF.setText(usersRoomInfoModel.getRoomType());
                    roomRentTF.setText(days*usersRoomInfoModel.getRoomTarrif() + "");
                    restaurantTF.setText(restaurantBill+"");
                    laundryTF.setText(laundryBill+"");
                    othersTF.setText(othersBill+"");
                    //lateTF.setText(usersRoomInfoModel.getRoomTarrif() + "");
                    discountCashTF.setText(usersRoomInfoModel.getDiscountCash() + "");
                    discountPercentTF.setText(usersRoomInfoModel.getDiscountPercent() + "");
                    advancePaidAmountTF.setText(paidBill +"");

                    int roomTarrif = usersRoomInfoModel.getRoomTarrif();
                    if(roomRentTF.getText().equals("")){
                        roomRentTF.setText("0");
                    }
                    if(restaurantTF.getText().equals("")){
                        restaurantTF.setText("0");
                    }
                    if(laundryTF.getText().equals("")){
                        laundryTF.setText("0");
                    }
                    if(othersTF.getText().equals("")){
                        othersTF.setText("0");
                    }
                    if(lateTF.getText().equals("")){
                        lateTF.setText("0");
                    }
                    if(discountCashTF.getText().equals("")){
                        discountCashTF.setText("0");
                    }
                    if(discountPercentTF.getText().equals("")){
                        discountPercentTF.setText("0");
                    }
                    
                    int total = Integer.valueOf(roomRentTF.getText()) + Integer.valueOf(restaurantTF.getText());
                    total = total + Integer.valueOf(laundryTF.getText()) + Integer.valueOf(othersTF.getText());
                    total = total + Integer.valueOf(lateTF.getText()) - Integer.valueOf(discountCashTF.getText());
                    total = total - (roomTarrif*Integer.valueOf(discountPercentTF.getText()))/100;
                    totalTF.setText(total+"");
                    int serviceCharges = (int) (total*.1);
                    serviceChargeTF.setText( serviceCharges + "");
                    int vats = (int) (total*.15);
                    vatTF.setText(vats + "");
                    grandTotalTF.setText(total+serviceCharges+vats + "");

                    int less = Integer.valueOf(grandTotalTF.getText()) % 10;
                    lessAmountTF.setText(less +"");
                    int cc = 0;
                    UserOthersInfoModel uoim = UserOthersInfoCrud.getSingleUserOthersInfo(mrNo);
                    CompanyInfoModel cim = CompanyInfoCrud.getAllCompanyInfoByName(uoim.getLocalCompany());
                    try{
                        cc = cim.getCompanyCode();
                    }catch(Exception e){
                        cc = 0;
                    }
                    companyCodeTF.setText(cc+"");
                    lessAmountAction();
                    payAction();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Wrong Entry");
            }
                
        }
    }
    
    private void checkOutCalculateAction(){
        //boolean flag = true;
        String mrNo = mrNoTF.getText();
        if(mrNoTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter MR Number");
        }
        else if(mrNoTF.getText().length()!=8){
            JOptionPane.showMessageDialog(null, "Wrong MR Number");
        }else{
            try{
                usersRoomInfoModel = UserRoomInfoCrud.getSingleUserRoomInfo(mrNo);
                //int laundryBill = LaundryAddCrud.getSingleLaundryTotal(mrNo);
                //int restaurantBill = RestaurantAddCrud.getSingleRestaurantTotal(mrNo);
                //int othersBill = OthersAddCrud.getSingleOthersTotal(mrNo);
                //int paidBill = ServicesMiniCrud.getSingleServiceTotal(mrNo);
                if(!usersRoomInfoModel.getMrNo().equals("")){
                    /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date arrivalDate = usersRoomInfoModel.getArrivalDate();
                    String arrivalStr = sdf.format(arrivalDate);
                    Date today = new Date();
                    String todayStr = sdf.format(today);
                    long days = 1;
                    try{
                        Date arrival = sdf.parse(arrivalStr);
                        Date now = sdf.parse(todayStr);
                        long difference = now.getTime() - arrival.getTime();
                        days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                        if(days==0){
                            days = 1;
                        }
                    }catch(Exception e){

                    }*/
                    //roomTypeTF.setText(usersRoomInfoModel.getRoomType());
                    //roomRentTF.setText(days*usersRoomInfoModel.getRoomTarrif() + "");
                    //restaurantTF.setText(restaurantBill+"");
                    //laundryTF.setText(laundryBill+"");
                    //othersTF.setText(othersBill+"");
                    //lateTF.setText(usersRoomInfoModel.getRoomTarrif() + "");
                    //discountCashTF.setText(usersRoomInfoModel.getDiscountCash() + "");
                    //discountPercentTF.setText(usersRoomInfoModel.getDiscountPercent() + "");
                    //advancePaidAmountTF.setText(paidBill +"");

                    int roomTarrif = usersRoomInfoModel.getRoomTarrif();
                    if(roomRentTF.getText().equals("")){
                        roomRentTF.setText("0");
                    }
                    if(restaurantTF.getText().equals("")){
                        restaurantTF.setText("0");
                    }
                    if(laundryTF.getText().equals("")){
                        laundryTF.setText("0");
                    }
                    if(othersTF.getText().equals("")){
                        othersTF.setText("0");
                    }
                    if(lateTF.getText().equals("")){
                        lateTF.setText("0");
                    }
                    if(discountCashTF.getText().equals("")){
                        discountCashTF.setText("0");
                    }
                    if(discountPercentTF.getText().equals("")){
                        discountPercentTF.setText("0");
                    }
                    
                    int total = Integer.valueOf(roomRentTF.getText()) + Integer.valueOf(restaurantTF.getText());
                    total = total + Integer.valueOf(laundryTF.getText()) + Integer.valueOf(othersTF.getText());
                    total = total + Integer.valueOf(lateTF.getText()) - Integer.valueOf(discountCashTF.getText());
                    total = total - (roomTarrif*Integer.valueOf(discountPercentTF.getText()))/100;
                    totalTF.setText(total+"");
                    int serviceCharges = (int) (total*.1);
                    serviceChargeTF.setText( serviceCharges + "");
                    int vats = (int) (total*.15);
                    vatTF.setText(vats + "");
                    grandTotalTF.setText(total+serviceCharges+vats + "");

                    int less = Integer.valueOf(grandTotalTF.getText()) % 10;
                    lessAmountTF.setText(less +"");
                    int cc = 0;
                    UserOthersInfoModel uoim = UserOthersInfoCrud.getSingleUserOthersInfo(mrNo);
                    CompanyInfoModel cim = CompanyInfoCrud.getAllCompanyInfoByName(uoim.getLocalCompany());
                    try{
                        cc = cim.getCompanyCode();
                    }catch(Exception e){
                        cc = 0;
                    }
                    companyCodeTF.setText(cc+"");
                    lessAmountAction();
                    payAction();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Wrong Entry");
            }
                
        }
            
    }
    
    private void lateCheckOutAction(){
        String mrNo = mrNoTF.getText();
        if(mrNoTF.getText().equals("")){
            lateCheckOutCheckB.setSelected(false);
            JOptionPane.showMessageDialog(null, "Enter MR Number");
        }
        else if(mrNoTF.getText().length()!=8){
            lateCheckOutCheckB.setSelected(false);
            JOptionPane.showMessageDialog(null, "Wrong MR Number");
        }
        else{
            usersRoomInfoModel = UserRoomInfoCrud.getSingleUserRoomInfo(mrNo);

            if(lateCheckOutCheckB.isSelected() ){
                lateTF.setText(usersRoomInfoModel.getRoomTarrif() + "");
            }
            else{
                lateTF.setText("0");
            }
            checkOutCalculateAction();
            
        }
        
    }
    
    private void lessAmountAction(){
        try{
            int less;
            try{
                if(!lessAmountTF.getText().equals("")){
                    less = Integer.valueOf(lessAmountTF.getText());
                }
                else{
                    less = 0;
                }
            }catch(Exception e){
                less = 0;
            }
            int grandTotal = Integer.valueOf(grandTotalTF.getText());
            int netPay = grandTotal - less;
            netPayableTF.setText(netPay +"");
            int advance = Integer.valueOf(advancePaidAmountTF.getText());
            dueTF.setText(netPay - advance +"");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Wrong Entry");
        }
    }
    
    private void payAction(){
        try{
            int pay;
            if(payTF.getText().equals("")){
                pay = 0;
            }
            else{
                pay = Integer.valueOf(payTF.getText());
            }
            int due = Integer.valueOf(dueTF.getText());

            checkOutDueTF.setText(due-pay +"");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Wrong Entry");
        }
    }
    
    private void clearForm(){
        roomRentTF.setText("0");
        restaurantTF.setText("0");
        laundryTF.setText("0");
        othersTF.setText("0");
        lateTF.setText("0");
        discountCashTF.setText("0");
        discountPercentTF.setText("0");
        totalTF.setText("0");
        serviceChargeTF.setText("0");
        vatTF.setText("0");
        grandTotalTF.setText("0");
        lessAmountTF.setText("0");
        netPayableTF.setText("0");
        advancePaidAmountTF.setText("0");
        dueTF.setText("0");
        payTF.setText("0");
        checkOutDueTF.setText("0");
        roomNoTF.setText("");
        mrNoTF.setText("");
        companyCodeTF.setText("");
        roomTypeTF.setText("");
        cardNoTF.setText("");
        paymentMethodCB.setSelectedIndex(0);
        paymentCurrencyCB.setSelectedIndex(0);
        lateCheckOutCheckB.setSelected(false);
        arrivalDateTF.setText("");
        departureDateTF.setText("");
    }
    
    private void checkOutButtonPressed(){
        try{
            String mrNo = mrNoTF.getText();
            int grandTotal = Integer.valueOf(grandTotalTF.getText());
            int lessAmount = Integer.valueOf(lessAmountTF.getText());
            int netPayable = Integer.valueOf(netPayableTF.getText());
            int advance = Integer.valueOf(advancePaidAmountTF.getText());
            int pay = Integer.valueOf(payTF.getText());
            int finalDue = Integer.valueOf(checkOutDueTF.getText());
            String paymentMethod =(String) paymentMethodCB.getSelectedItem();
            String cardNo = cardNoTF.getText();
            Date departureDate = new Date();


            //String mrNo;
            int roomRent = Integer.valueOf(roomRentTF.getText());
            int restaurant = Integer.valueOf(restaurantTF.getText());
            int laundry = Integer.valueOf(laundryTF.getText());
            int others = Integer.valueOf(othersTF.getText());
            int late = Integer.valueOf(lateTF.getText());
            int discountTK = Integer.valueOf(discountCashTF.getText());
            int discountPercent = Integer.valueOf(discountPercentTF.getText());
            int total = Integer.valueOf(totalTF.getText());
            int serviceCharge = Integer.valueOf(serviceChargeTF.getText());
            int vat = Integer.valueOf(vatTF.getText());



            int roomNo = Integer.valueOf(roomNoTF.getText());
            String status = "CheckedOut";
            int companyCode;
            if(companyCodeTF.getText().equals("")){
                companyCode = -1;
            }else{
                companyCode = Integer.valueOf(companyCodeTF.getText());
            }

            Payment payment = new Payment(mrNo, grandTotal, lessAmount, netPayable, advance, pay, finalDue, paymentMethod, cardNo, departureDate);
            PayAbout payAbout = new PayAbout(mrNo, roomRent, restaurant, laundry, others, late, discountTK, discountPercent, total, serviceCharge, vat);

            KeyGenerator key = new KeyGenerator();
            String id = key.getKey();
            //companycode
            String companyName = "None";
            ArrayList<CompanyInfoModel> allCompany = new ArrayList<>();
            for(CompanyInfoModel cim : allCompany){
                if(cim.getCompanyCode() == companyCode){
                    companyName = cim.getCompanyName();
                }
            }
            int amount = (-1)*finalDue;
            //String status;
            String reason = "Room CheckOut Due";
            //String mrNo;
            Date date = new Date();


            UserInfoModel uim = UserInfoCrud.getUserInfoByMr(mrNo);
            CompanyInfoModel cim = CompanyInfoCrud.getAllCompanyInfoByCC(companyCode);
            ArrayList<UserOthersInfoModel> allUserOthersInfo = UserOthersInfoCrud.getAllUserOthersInfo();

            String checkCompany = "no";
            for(UserOthersInfoModel uoim : allUserOthersInfo){
                if(uoim.getMrNo().equals(mrNo)){
                    checkCompany = uoim.getLocalCompany();
                }
            }
            
            if(finalDue <= 0){

                PaymentCrud.addPayment(payment);
                PayAboutCrud.addPayAbout(payAbout);
                RoomDetailsCrud.updateRoomStatus(roomNo, status);
                UserRoomInfoCrud.updateUserRoomInfoStatus(mrNo, status);

                clearForm();
                refreshTable();
                CheckIn.refreshTable();
                AdvancePayment.refreshTable();
                RoomTransfer.refreshTable();
                Laundry.showCurrentCheckIns();
                Restaurant.showCurrentCheckIns();
                Others.showCurrentCheckIns();

                JOptionPane.showMessageDialog(null, "Checked Out Successfully");
                new CheckOutPrint(Integer.valueOf(dueTF.getText()), companyCode, payAbout, payment, usersRoomInfoModel).setVisible(true);
            }
            else if(companyCode == -1){
                JOptionPane.showMessageDialog(null, "Enter Company Code");
            }
            
            else if(companyCode == 0){
                JOptionPane.showMessageDialog(null, "No Local Company Added. Add A Local Company By Admin");
            }
            
//            else if(!checkCompany.equals(cim.getCompanyName())){
//                JOptionPane.showMessageDialog(null, "Enter Correct Company Code while Checked In");
//            }

            else{
                int result = CompanyInfoCrud.updateCompanyBalanceByCode(companyCode, amount);
                if(result>0){
                    CompanyInfoTransactionModel citm = new CompanyInfoTransactionModel(id, companyCode, companyName, amount, status, reason, mrNo, date);
                    CompanyInfoTransactionCurd.addTransaction(citm);
                    PaymentCrud.addPayment(payment);
                    PayAboutCrud.addPayAbout(payAbout);
                    RoomDetailsCrud.updateRoomStatus(roomNo, status);
                    UserRoomInfoCrud.updateUserRoomInfoStatus(mrNo, status);

                    clearForm();
                    refreshTable();
                    CheckIn.refreshTable();
                    AdvancePayment.refreshTable();
                    RoomTransfer.refreshTable();
                    Laundry.showCurrentCheckIns();
                    Restaurant.showCurrentCheckIns();
                    Others.showCurrentCheckIns();
                    
                    JOptionPane.showMessageDialog(null, "Due Added With Your Local Company Successfully.");
                    JOptionPane.showMessageDialog(null, "Checked Out Successfully.");
                    new CheckOutPrint(Integer.valueOf(dueTF.getText()), companyCode, payAbout, payment, usersRoomInfoModel).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Company ! Enter a valid Company.");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Wrong Entry");
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

        checkOutLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        paymentCurrencyCB = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        companyCodeTF = new javax.swing.JTextField();
        cardNoTF = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        paymentMethodCB = new javax.swing.JComboBox<>();
        roomNoTF = new javax.swing.JTextField();
        arrivalDateTF = new javax.swing.JTextField();
        mrNoTF = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        departureDateTF = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        roomTypeTF = new javax.swing.JTextField();
        lateCheckOutCheckB = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        discountPercentTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        laundryTF = new javax.swing.JTextField();
        discountCashTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lateTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        othersTF = new javax.swing.JTextField();
        restaurantTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        totalTF = new javax.swing.JTextField();
        roomRentTF = new javax.swing.JTextField();
        serviceChargeTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        vatTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        checkOutButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        netPayableTF = new javax.swing.JTextField();
        dueTF = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        lessAmountTF = new javax.swing.JTextField();
        grandTotalTF = new javax.swing.JTextField();
        advancePaidAmountTF = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        payTF = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        checkOutDueTF = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();

        checkOutLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        checkOutLabel.setText("Check Out");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Guest Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel11.setText("Room No :");

        paymentCurrencyCB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel19.setText("Departure Date :");

        jLabel21.setText("Company Code :");

        jLabel18.setText("Arrival Date :");

        jLabel23.setText("Card No :");

        jLabel17.setText("MR NO :");

        paymentMethodCB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        mrNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mrNoTFActionPerformed(evt);
            }
        });

        jLabel24.setText("Payment Currency:");

        jLabel20.setText("Room Type :");

        jLabel22.setText("Payment Method:");

        lateCheckOutCheckB.setText("Late Check Out");
        lateCheckOutCheckB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lateCheckOutCheckBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(paymentMethodCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cardNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mrNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(arrivalDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(departureDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(companyCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(paymentCurrencyCB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lateCheckOutCheckB)
                            .addComponent(roomTypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mrNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrivalDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departureDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomTypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lateCheckOutCheckB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentMethodCB, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentCurrencyCB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Payment", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        discountPercentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountPercentTFActionPerformed(evt);
            }
        });

        jLabel1.setText("Room Rent(Tk.)");

        discountCashTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountCashTFActionPerformed(evt);
            }
        });

        jLabel15.setText("VAT(15%)");

        jLabel7.setText("Discount(Tk.)");

        lateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lateTFActionPerformed(evt);
            }
        });

        jLabel16.setText("Service Charge(10%)");

        jLabel2.setText("Restaurant(Tk.)");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel6.setText("Discount(%)");

        jLabel5.setText("Total(Tk.)");

        jSeparator1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel8.setText("Late(Tk.)");

        jLabel3.setText("Laundry(Tk.)");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jLabel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel3KeyPressed(evt);
            }
        });

        jLabel4.setText("Others(Tk.)");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomRentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(laundryTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(othersTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(discountCashTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(discountPercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(totalTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(serviceChargeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(vatTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(restaurantTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomRentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(restaurantTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laundryTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(othersTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountCashTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountPercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceChargeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vatTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        checkOutButton.setText("Check Out");
        checkOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutButtonActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Payment", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel26.setText("Due(Tk.)");

        jLabel27.setText("Advance Amount(Tk.)");

        jLabel30.setText("Grand Total(Tk.)");

        jLabel31.setText("Net Payable(Tk.)");

        jLabel33.setText("Less Amount(Tk.)");

        lessAmountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessAmountTFActionPerformed(evt);
            }
        });

        payTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payTFActionPerformed(evt);
            }
        });
        payTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                payTFKeyReleased(evt);
            }
        });

        jLabel28.setText("Pay(Tk.)");

        jLabel29.setText("Final Due(Tk.)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(grandTotalTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lessAmountTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(netPayableTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(advancePaidAmountTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkOutDueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(payTF, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grandTotalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lessAmountTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(4, 4, 4)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(netPayableTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(advancePaidAmountTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkOutDueTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTableMouseClicked(evt);
            }
        });
        dataTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dataTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dataTableKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(dataTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkOutLabel)
                .addGap(330, 330, 330))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(checkOutLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkOutButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutButtonActionPerformed
        checkOutButtonPressed();
    }//GEN-LAST:event_checkOutButtonActionPerformed

    private void dataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMouseClicked
        dataTableMouseClickAction();
    }//GEN-LAST:event_dataTableMouseClicked

    private void dataTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataTableKeyPressed
        
    }//GEN-LAST:event_dataTableKeyPressed

    private void dataTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataTableKeyReleased
        dataTableMouseClickAction();
    }//GEN-LAST:event_dataTableKeyReleased

    private void mrNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mrNoTFActionPerformed
        checkOutCalculate();
    }//GEN-LAST:event_mrNoTFActionPerformed

    private void lateCheckOutCheckBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lateCheckOutCheckBActionPerformed
        lateCheckOutAction();
    }//GEN-LAST:event_lateCheckOutCheckBActionPerformed

    private void lessAmountTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessAmountTFActionPerformed
        lessAmountAction();
    }//GEN-LAST:event_lessAmountTFActionPerformed

    private void payTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payTFActionPerformed
        payAction();
    }//GEN-LAST:event_payTFActionPerformed

    private void payTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_payTFKeyReleased
        payAction();
    }//GEN-LAST:event_payTFKeyReleased

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
        if(mrNoTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter MR Number");
        }
        else{
            String mrNo = mrNoTF.getText();
            new RestaurantShow(mrNo).setVisible(true);
        }
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3KeyPressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        if(mrNoTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter MR Number");
        }
        else{
            String mrNo = mrNoTF.getText();
            new LaundryShow(mrNo).setVisible(true);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        if(mrNoTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter MR Number");
        }
        else{
            String mrNo = mrNoTF.getText();
            new OthersShow(mrNo).setVisible(true);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void lateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lateTFActionPerformed
        checkOutCalculateAction();
    }//GEN-LAST:event_lateTFActionPerformed

    private void discountCashTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountCashTFActionPerformed
        checkOutCalculateAction();
    }//GEN-LAST:event_discountCashTFActionPerformed

    private void discountPercentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountPercentTFActionPerformed
        checkOutCalculateAction();
    }//GEN-LAST:event_discountPercentTFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField advancePaidAmountTF;
    private javax.swing.JTextField arrivalDateTF;
    private javax.swing.JTextField cardNoTF;
    private javax.swing.JButton checkOutButton;
    private javax.swing.JTextField checkOutDueTF;
    private javax.swing.JLabel checkOutLabel;
    private javax.swing.JTextField companyCodeTF;
    private javax.swing.JTable dataTable;
    private javax.swing.JTextField departureDateTF;
    private javax.swing.JTextField discountCashTF;
    private javax.swing.JTextField discountPercentTF;
    private javax.swing.JTextField dueTF;
    private javax.swing.JTextField grandTotalTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JCheckBox lateCheckOutCheckB;
    private javax.swing.JTextField lateTF;
    private javax.swing.JTextField laundryTF;
    private javax.swing.JTextField lessAmountTF;
    private javax.swing.JTextField mrNoTF;
    private javax.swing.JTextField netPayableTF;
    private javax.swing.JTextField othersTF;
    private javax.swing.JTextField payTF;
    private javax.swing.JComboBox<String> paymentCurrencyCB;
    private javax.swing.JComboBox<String> paymentMethodCB;
    private javax.swing.JTextField restaurantTF;
    private javax.swing.JTextField roomNoTF;
    private javax.swing.JTextField roomRentTF;
    private javax.swing.JTextField roomTypeTF;
    private javax.swing.JTextField serviceChargeTF;
    private javax.swing.JTextField totalTF;
    private javax.swing.JTextField vatTF;
    // End of variables declaration//GEN-END:variables
}
