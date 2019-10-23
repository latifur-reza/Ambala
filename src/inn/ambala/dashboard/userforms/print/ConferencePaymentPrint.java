/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.print;

import inn.ambala.dashboard.userforms.conference.model.AmountDetailsModel;
import inn.ambala.dashboard.userforms.conference.model.BookingDetailsModel;
import inn.ambala.dashboard.userforms.conference.model.PaymentDetailsModel;
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
public class ConferencePaymentPrint extends javax.swing.JFrame {

    BookingDetailsModel bdm;
    AmountDetailsModel adm;
    PaymentDetailsModel pdm;
    int vat;
    int service;
    int finalPay;
    /**
     * Creates new form ConferenceBookingPrint
     */
    public ConferencePaymentPrint() {
        initComponents();
    }
    
    public ConferencePaymentPrint(BookingDetailsModel bdm,AmountDetailsModel adm,PaymentDetailsModel pdm, int vat, int service, int finalPay) {
        this.bdm = bdm;
        this.adm = adm;
        this.pdm = pdm;
        this.vat = vat;
        this.service = service;
        this.finalPay = finalPay;
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
        voucharNoLabel.setText(bdm.getVoucherNo());
        name.setText(bdm.getCompany());
        address.setText(bdm.getAddress());
        reasonLabel.setText(bdm.getReason());
        areaCodeLabel.setText(bdm.getAreaCode());
        try{
            arrivalLabel.setText(String.valueOf(bdm.getArrivalDate()));
        }catch(Exception e){
            
        }
        areaLabel.setText(bdm.getArea());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        fromLabel.setText(sdf.format(bdm.getFrom()));
        toLabel.setText(sdf.format(bdm.getTo()));
        
        rent.setText(adm.getRent() + "");
        foodCharge.setText(adm.getFoodCharges() + "");
        projector.setText(adm.getProjector() + "");
        osp.setText(adm.getOsp() + "");
        video.setText(adm.getVideos() + "");
        soundSystem.setText(adm.getSoundSystem() + "");
        others.setText(adm.getOthers() + "");
        
        vatLabel.setText(vat + "");
        serviceChargeLabel.setText(service + "");
        discountLabel.setText(adm.getDiscount() + "");
        total.setText(adm.getTotal() + "");
        advance.setText(pdm.getPayment() + "");
        due.setText(pdm.getDue() + "");
        payLabel.setText(finalPay + "");
        
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

        printButton = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        voucharNoLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        areaCodeLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        arrivalLabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        areaLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        fromLabel = new javax.swing.JLabel();
        toLabel = new javax.swing.JLabel();
        reasonLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        rent = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        foodCharge = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        projector = new javax.swing.JLabel();
        osp = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        video = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        soundSystem = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        others = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        vatLabel = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        serviceChargeLabel = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        discountLabel = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        advance = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        due = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        payLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Ambala Inn");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel2.setText("ROAD #2, HOUSE #39, DHANMONDI R/A, DHAKA-1205, BANGLADESH");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel3.setText("TELEPHONE : 8618901, 8619373, 8610502, 8615714 FAX #880-2-8614490 EMAIL : ambala@bangla.net");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Conference Payment");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Voucher No :");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Company Name :");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Address :");

        address.setText("address");

        name.setText("name");

        voucharNoLabel.setText("voucharNo");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Area Code :");

        areaCodeLabel.setText("code");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Arrival Date :");

        arrivalLabel.setText("arrival");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Area :");

        areaLabel.setText("area");

        jLabel21.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("To :");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("From :");

        fromLabel.setText("from");

        toLabel.setText("to");

        reasonLabel.setText("reason");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Reason :");

        jLabel22.setText("Rent :");

        rent.setText("jLabel46");

        jLabel23.setText("Food Charge :");

        foodCharge.setText("jLabel47");

        jLabel25.setText("Projector :");

        projector.setText("jLabel46");

        osp.setText("jLabel47");

        jLabel24.setText("OSP :");

        jLabel29.setText("Video :");

        video.setText("jLabel46");

        jLabel28.setText("Sound System :");

        soundSystem.setText("jLabel47");

        jLabel27.setText("Others :");

        others.setText("jLabel46");

        jLabel30.setText("Vat :");

        vatLabel.setText("jLabel46");

        jLabel31.setText("Service Charge :");

        serviceChargeLabel.setText("jLabel47");

        jLabel32.setText("Discount :");

        discountLabel.setText("jLabel46");

        jLabel33.setText("Total Bill :");

        total.setText("jLabel47");

        advance.setText("jLabel46");

        jLabel34.setText("Advance :");

        jLabel35.setText("Due :");

        due.setText("jLabel47");

        jLabel39.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel42.setText("Thank You for being with us");

        jLabel43.setText("Copyright @ Ambala Inn | Developed by Mistri Solutions");

        time.setText("Time");

        jLabel36.setText("Pay :");

        payLabel.setText("jLabel47");

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24)
                    .addComponent(jLabel27))
                .addGap(10, 10, 10)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(foodCharge)
                    .addComponent(rent)
                    .addComponent(osp)
                    .addComponent(projector)
                    .addComponent(soundSystem)
                    .addComponent(video)
                    .addComponent(others))
                .addGap(152, 152, 152)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35)
                    .addComponent(jLabel34)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addGap(10, 10, 10)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serviceChargeLabel)
                    .addComponent(vatLabel)
                    .addComponent(total)
                    .addComponent(discountLabel)
                    .addComponent(due)
                    .addComponent(advance)
                    .addComponent(payLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(200, 200, 200))
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jLabel3))
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(309, 309, 309)
                                .addComponent(jLabel1)))
                        .addContainerGap())
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(voucharNoLabel))
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(name))
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(address))
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reasonLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toLabel)
                            .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fromLabel)
                                .addComponent(areaLabel))
                            .addComponent(arrivalLabel)
                            .addComponent(areaCodeLabel))
                        .addGap(92, 92, 92))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(time)
                        .addGap(99, 99, 99))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(295, 295, 295))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(297, 297, 297))))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(voucharNoLabel)
                    .addComponent(jLabel11)
                    .addComponent(areaCodeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(name)
                    .addComponent(jLabel17)
                    .addComponent(arrivalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(address)
                    .addComponent(jLabel18)
                    .addComponent(areaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(fromLabel)
                    .addComponent(jLabel15)
                    .addComponent(reasonLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(toLabel))
                .addGap(13, 13, 13)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(rent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(foodCharge))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(projector))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(osp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(video))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(soundSystem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(others)
                            .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel36)
                                .addComponent(payLabel))))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(vatLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(serviceChargeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(discountLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(total))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(advance))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(due))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addGap(11, 11, 11)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(time)))
        );

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
                .addGap(355, 355, 355))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(printButton))
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
            java.util.logging.Logger.getLogger(ConferencePaymentPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConferencePaymentPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConferencePaymentPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConferencePaymentPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConferencePaymentPrint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JLabel advance;
    private javax.swing.JLabel areaCodeLabel;
    private javax.swing.JLabel areaLabel;
    private javax.swing.JLabel arrivalLabel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel discountLabel;
    private javax.swing.JLabel due;
    private javax.swing.JLabel foodCharge;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel name;
    private javax.swing.JLabel osp;
    private javax.swing.JLabel others;
    private javax.swing.JLabel payLabel;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel projector;
    private javax.swing.JLabel reasonLabel;
    private javax.swing.JLabel rent;
    private javax.swing.JLabel serviceChargeLabel;
    private javax.swing.JLabel soundSystem;
    private javax.swing.JLabel time;
    private javax.swing.JLabel toLabel;
    private javax.swing.JLabel total;
    private javax.swing.JLabel vatLabel;
    private javax.swing.JLabel video;
    private javax.swing.JLabel voucharNoLabel;
    // End of variables declaration//GEN-END:variables
}
