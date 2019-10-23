/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.conference.model;

import java.util.Date;

/**
 *
 * @author REZA
 */
public class PaymentDetailsModel {
    String voucharNo;
    int total;
    int payment;
    Date paymentDate;
    int due;

    public PaymentDetailsModel() {
    }

    public PaymentDetailsModel(String voucharNo, int total, int payment, Date paymentDate, int due) {
        this.voucharNo = voucharNo;
        this.total = total;
        this.payment = payment;
        this.paymentDate = paymentDate;
        this.due = due;
    }

    public String getVoucharNo() {
        return voucharNo;
    }

    public void setVoucharNo(String voucharNo) {
        this.voucharNo = voucharNo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }
    
}
