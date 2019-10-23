/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.print;

import inn.ambala.dashboard.userforms.checkout.model.PayAbout;
import inn.ambala.dashboard.userforms.checkout.model.Payment;
import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.dashboard.userforms.model.UsersRoomInfoModel;
import inn.ambala.databaseconnection.userforms.addforms.ServicesMiniCrud;
import inn.ambala.databaseconnection.userforms.forms.UserInfoCrud;
import inn.ambala.resources.Resources;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author REZA
 */
public class CheckOutPrint extends javax.swing.JFrame {

    PayAbout payAbout;
    Payment payment;
    UsersRoomInfoModel urim;
    int companyCode;
    int dueAm;
    /**
     * Creates new form CheckOutPrint
     */
    public CheckOutPrint() {
        initComponents();
    }
    
    public CheckOutPrint(int dueAm,int companyCode, PayAbout payAbout, Payment payment, UsersRoomInfoModel urim) {
        this.payAbout = payAbout;
        this.payment = payment;
        this.urim = urim;
        this.companyCode = companyCode;
        this.dueAm = dueAm;
        initComponents();
        initExtraComponents();
        setData();
    }
    
    private void initExtraComponents(){
        ImageIcon key = new ImageIcon(Resources.getPath() + "icon-01.jpg");
        Image image = key.getImage();
        setIconImage(image);
        setTitle("Ambala Inn");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String arrivalDate = sdf.format(urim.getArrivalDate());
        String departureDate = sdf.format(payment.getDepartureDate());
        mrNo.setText(payment.getMrNo());
        roomNo.setText(urim.getRoomNo()+"");
        roomType.setText(urim.getRoomType());
        comCode.setText(companyCode+"");
        UserInfoModel uim = UserInfoCrud.getUserInfoByMr(payAbout.getMrNo());
        name.setText(uim.getName());
        address.setText(uim.getAddress());
        arrival.setText(arrivalDate);
        departure.setText(departureDate);
        rent.setText(payAbout.getRoomRent()+"");
        restaurant.setText(payAbout.getRestaurant()+"");
        laundry.setText(payAbout.getLaundry()+"");
        others.setText(payAbout.getOthers()+"");
        lateCheckOut.setText(payAbout.getLate()+"");
        discountCash.setText(payAbout.getDiscountTK()+"");
        discountPercent.setText(payAbout.getDiscountPercent()+"");
        total.setText(payAbout.getTotal()+"");
        serviceCharge.setText(payAbout.getServiceCharge()+"");
        vat.setText(payAbout.getVat()+"");
        grandTotal.setText(payment.getGrandTotal()+"");
        lessAmount.setText(payment.getLessAmount()+"");
        netPayable.setText(payment.getNetPayable()+"");
        advancePayment.setText(ServicesMiniCrud.getSingleServiceTotal(payment.getMrNo())+"");
        due.setText(dueAm+"");
        pay.setText(payment.getPay()+"");
        checkOutDue.setText(payment.getFinalDue()+"");
        paymentMethod.setText(payment.getPaymentMethod());
        cardNo.setText(payment.getCardNo());
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        time.setText(sdf2.format(now));
    }
    
    private void printButtonPressed(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");
        
        job.setPrintable(new Printable() {
            int lastPage = 0;
            double yOffset;
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                int result = NO_SUCH_PAGE;
                
                PageFormat pf = job.defaultPage();
                Paper paper = new Paper();
                double margin = 100;
                paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
                    - margin * 2);
                pf.setPaper(paper);
                
                double height = pageFormat.getImageableHeight();
                contentPanel.setSize(contentPanel.getPreferredSize());
                
                if (lastPage != pageIndex) {
                    lastPage = pageIndex;
                    yOffset = height * pageIndex;
                    if (yOffset > contentPanel.getHeight()) {
                        yOffset = -1;
                    }
                }

                if (yOffset >= 0) {
                    Graphics2D g2d = (Graphics2D) graphics;
                    g2d.translate((int) pageFormat.getImageableX(),
                                    (int) pageFormat.getImageableY());
                    g2d.translate(0, -yOffset);
                    g2d.scale(0.80, 0.70);
                    
                    contentPanel.printAll(g2d);
                    
                    result = PAGE_EXISTS;
                }
                return result;
            }
        });
        
        boolean ok = job.printDialog();
        if(ok){
            try{
                job.print();
            }catch(PrinterException e){
                JOptionPane.showMessageDialog(null, "Printer Error!");
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

        contentPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        mrNo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        roomNo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        roomType = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comCode = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        departure = new javax.swing.JLabel();
        arrival = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        serviceCharge = new javax.swing.JLabel();
        rent = new javax.swing.JLabel();
        restaurant = new javax.swing.JLabel();
        others = new javax.swing.JLabel();
        laundry = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        discountPercent = new javax.swing.JLabel();
        discountCash = new javax.swing.JLabel();
        lateCheckOut = new javax.swing.JLabel();
        pay = new javax.swing.JLabel();
        due = new javax.swing.JLabel();
        advancePayment = new javax.swing.JLabel();
        checkOutDue = new javax.swing.JLabel();
        netPayable = new javax.swing.JLabel();
        lessAmount = new javax.swing.JLabel();
        grandTotal = new javax.swing.JLabel();
        vat = new javax.swing.JLabel();
        cardNo = new javax.swing.JLabel();
        paymentMethod = new javax.swing.JLabel();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel2.setText("ROAD #2, HOUSE #39, DHANMONDI R/A, DHAKA-1205, BANGLADESH");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Ambala Inn");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Check Out Receipt");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel3.setText("TELEPHONE : 8618901, 8619373, 8610502, 8615714 FAX #880-2-8614490 EMAIL : ambala@bangla.net");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Mr No :");

        mrNo.setText("Mr Number");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Room No :");

        roomNo.setText("000");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Room Type :");

        roomType.setText("Type");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Company Code :");

        comCode.setText("code");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Name :");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Address :");

        address.setText("address");

        name.setText("name");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Arrival Date :");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Departure Date :");

        departure.setText("departure");

        arrival.setText("arrival");

        jLabel21.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel22.setText("Room Rent :");

        jLabel23.setText("Restaurant :");

        jLabel24.setText("Others :");

        jLabel25.setText("Laundry :");

        jLabel26.setText("Total :");

        jLabel27.setText("Discount (%) :");

        jLabel28.setText("Discount (Cash) :");

        jLabel29.setText("Late Check Out :");

        jLabel30.setText("Pay :");

        jLabel31.setText("Due :");

        jLabel32.setText("Advance Payment :");

        jLabel33.setText("Net Payable :");

        jLabel34.setText("Less Amount :");

        jLabel35.setText("Grand Total :");

        jLabel36.setText("VAT (15%) :");

        jLabel37.setText("Service Charge(5%) :");

        jLabel38.setText("Check Out Due :");

        jLabel39.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel40.setText("Payment Method :");

        jLabel41.setText("Card No :");

        jLabel42.setText("Thank You for being with us");

        jLabel43.setText("Copyright @ Ambala Inn | Developed by Mistri Solutions");

        time.setText("Time");

        serviceCharge.setText("jLabel45");

        rent.setText("jLabel46");

        restaurant.setText("jLabel47");

        others.setText("jLabel47");

        laundry.setText("jLabel46");

        total.setText("jLabel47");

        discountPercent.setText("jLabel46");

        discountCash.setText("jLabel47");

        lateCheckOut.setText("jLabel46");

        pay.setText("jLabel46");

        due.setText("jLabel47");

        advancePayment.setText("jLabel46");

        checkOutDue.setText("jLabel47");

        netPayable.setText("jLabel47");

        lessAmount.setText("jLabel46");

        grandTotal.setText("jLabel47");

        vat.setText("jLabel46");

        cardNo.setText("jLabel45");

        paymentMethod.setText("jLabel46");

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(210, 210, 210))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mrNo)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomNo)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomType))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(name))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(address)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(comCode))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arrival)
                            .addComponent(departure))))
                .addGap(56, 56, 56))
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42)
                .addGap(308, 308, 308))
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel3))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel1))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jLabel4))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(paymentMethod)
                        .addGap(156, 156, 156)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cardNo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26))
                .addGap(10, 10, 10)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restaurant)
                    .addComponent(rent)
                    .addComponent(others)
                    .addComponent(laundry)
                    .addComponent(discountCash)
                    .addComponent(lateCheckOut)
                    .addComponent(total)
                    .addComponent(discountPercent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel36)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel31)
                            .addComponent(jLabel30)
                            .addComponent(jLabel38)))
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serviceCharge)
                    .addComponent(grandTotal)
                    .addComponent(vat)
                    .addComponent(netPayable)
                    .addComponent(lessAmount)
                    .addComponent(due)
                    .addComponent(advancePayment)
                    .addComponent(checkOutDue)
                    .addComponent(pay))
                .addGap(194, 194, 194))
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(time)
                .addGap(187, 187, 187))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(mrNo)
                    .addComponent(jLabel7)
                    .addComponent(roomNo)
                    .addComponent(jLabel9)
                    .addComponent(roomType)
                    .addComponent(jLabel11)
                    .addComponent(comCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(name)
                    .addComponent(jLabel17)
                    .addComponent(arrival))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(address)
                    .addComponent(jLabel18)
                    .addComponent(departure))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(rent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(restaurant))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(laundry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(others))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(lateCheckOut))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(discountCash))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(discountPercent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(total))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(checkOutDue)))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(serviceCharge))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(vat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(grandTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(lessAmount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(netPayable))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(advancePayment))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(due))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(pay))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(cardNo)
                    .addComponent(paymentMethod))
                .addGap(18, 18, 18)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(time))
                .addContainerGap())
        );

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(printButton)
                .addGap(351, 351, 351))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        printButtonPressed();
    }//GEN-LAST:event_printButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CheckOutPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOutPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOutPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOutPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckOutPrint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JLabel advancePayment;
    private javax.swing.JLabel arrival;
    private javax.swing.JLabel cardNo;
    private javax.swing.JLabel checkOutDue;
    private javax.swing.JLabel comCode;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel departure;
    private javax.swing.JLabel discountCash;
    private javax.swing.JLabel discountPercent;
    private javax.swing.JLabel due;
    private javax.swing.JLabel grandTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lateCheckOut;
    private javax.swing.JLabel laundry;
    private javax.swing.JLabel lessAmount;
    private javax.swing.JLabel mrNo;
    private javax.swing.JLabel name;
    private javax.swing.JLabel netPayable;
    private javax.swing.JLabel others;
    private javax.swing.JLabel pay;
    private javax.swing.JLabel paymentMethod;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel rent;
    private javax.swing.JLabel restaurant;
    private javax.swing.JLabel roomNo;
    private javax.swing.JLabel roomType;
    private javax.swing.JLabel serviceCharge;
    private javax.swing.JLabel time;
    private javax.swing.JLabel total;
    private javax.swing.JLabel vat;
    // End of variables declaration//GEN-END:variables
}
