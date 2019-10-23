/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.print;

import inn.ambala.dashboard.userforms.model.UserInfoModel;
import inn.ambala.databaseconnection.userforms.addforms.LaundryAddCrud;
import inn.ambala.databaseconnection.userforms.forms.UserInfoCrud;
import inn.ambala.resources.Resources;
import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REZA
 */
public class LaundryPrint extends javax.swing.JFrame {

    private static DefaultTableModel model = null;
    private String mrNo;
    private int roomNo;
    private int total;
    private int pay;
    private int due;
    /**
     * Creates new form LaundryPrint
     */
    public LaundryPrint() {
        initComponents();
        initExtraComponents();
    }
    
    public LaundryPrint(String mrNo, int roomNo, int total, int pay, int due) {
        
        initComponents();
        initExtraComponents();
        this.mrNo = mrNo;
        this.roomNo = roomNo;
        this.total = total;
        this.pay = pay;
        this.due = due;
        model = (DefaultTableModel) dataTable.getModel();
        dataTable.setBackground(Color.white);
        setData();
        printButtonPressed();
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
        UserInfoModel uim = UserInfoCrud.getUserInfoByMr(mrNo);
        mrNoLabel.setText(mrNo);
        roomNoLabel.setText(roomNo + "");
        contactLabel.setText(uim.getPhone());
        nameLabel.setText(uim.getName());
        addressLabel.setText(uim.getAddress());
        totalLabel.setText(total+"");
        payLabel.setText(pay+"");
        dueLabel.setText(due+"");
        
        new LaundryAddCrud(model).showAllLaundryForPrint(mrNo, "ordered");
        
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        time.setText(sdf2.format(now));
        
    }
    
    private static void printButtonPressed(){
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
        mrNoLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        roomNoLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        contactLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        dataTable = new javax.swing.JTable();
        totalLabel = new javax.swing.JLabel();
        payLabel = new javax.swing.JLabel();
        dueLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel2.setText("ROAD #2, HOUSE #39, DHANMONDI R/A, DHAKA-1205, BANGLADESH");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Ambala Inn");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Laundry Receipt");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel3.setText("TELEPHONE : 8618901, 8619373, 8610502, 8615714 FAX #880-2-8614490 EMAIL : ambala@bangla.net");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Mr No :");

        mrNoLabel.setText("Mr Number");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Room No :");

        roomNoLabel.setText("000");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Name :");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Address :");

        addressLabel.setText("address");

        nameLabel.setText("name");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Contact :");

        contactLabel.setText("0123456789");

        jLabel21.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel39.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel43.setText("Copyright @ Ambala Inn | Developed by Mistri Solutions");

        time.setText("Time");

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Laundry Code", "Laundry Type", "Cloth Type", "Price", "Quantity", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        totalLabel.setText("total");

        payLabel.setText("pay");

        dueLabel.setText("due");

        jLabel6.setText("Total :");

        jLabel8.setText("Paid :");

        jLabel9.setText("Due :");

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(18, 18, 18)
                                    .addComponent(nameLabel))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addressLabel))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(mrNoLabel)
                                    .addGap(115, 115, 115)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(roomNoLabel)
                                    .addGap(124, 124, 124)
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(contactLabel))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                            .addGap(126, 126, 126)
                            .addComponent(jLabel3))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                            .addGap(319, 319, 319)
                            .addComponent(jLabel1))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dataTable, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(time)
                .addGap(119, 119, 119))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(210, 210, 210))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(275, 275, 275))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalLabel)
                            .addComponent(payLabel)
                            .addComponent(dueLabel))
                        .addGap(41, 41, 41))))
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
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(contactLabel))
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(mrNoLabel)
                        .addComponent(jLabel7)
                        .addComponent(roomNoLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(addressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLabel)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payLabel)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueLabel)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(time)
                    .addComponent(jLabel43))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        if (dataTable.getColumnModel().getColumnCount() > 0) {
            dataTable.getColumnModel().getColumn(0).setMinWidth(78);
            dataTable.getColumnModel().getColumn(0).setPreferredWidth(78);
            dataTable.getColumnModel().getColumn(0).setMaxWidth(78);
            dataTable.getColumnModel().getColumn(3).setMinWidth(40);
            dataTable.getColumnModel().getColumn(3).setPreferredWidth(40);
            dataTable.getColumnModel().getColumn(3).setMaxWidth(40);
            dataTable.getColumnModel().getColumn(4).setMinWidth(55);
            dataTable.getColumnModel().getColumn(4).setPreferredWidth(55);
            dataTable.getColumnModel().getColumn(4).setMaxWidth(55);
            dataTable.getColumnModel().getColumn(5).setMinWidth(60);
            dataTable.getColumnModel().getColumn(5).setPreferredWidth(60);
            dataTable.getColumnModel().getColumn(5).setMaxWidth(60);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(LaundryPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaundryPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaundryPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaundryPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaundryPrint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JLabel contactLabel;
    private static javax.swing.JPanel contentPanel;
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel dueLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel mrNoLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel payLabel;
    private javax.swing.JLabel roomNoLabel;
    private javax.swing.JLabel time;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
