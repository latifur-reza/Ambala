/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.checkout.model;

import java.util.Date;

/**
 *
 * @author REZA
 */
public class Payment {
    String mrNo;
    int grandTotal;
    int lessAmount;
    int netPayable;
    int advance;
    int pay;
    int finalDue;
    String paymentMethod;
    String cardNo;
    Date departureDate;

    public Payment() {
    }

    public Payment(String mrNo, int grandTotal, int lessAmount, int netPayable, int advance, int pay, int finalDue, String paymentMethod, String cardNo, Date departureDate) {
        this.mrNo = mrNo;
        this.grandTotal = grandTotal;
        this.lessAmount = lessAmount;
        this.netPayable = netPayable;
        this.advance = advance;
        this.pay = pay;
        this.finalDue = finalDue;
        this.paymentMethod = paymentMethod;
        this.cardNo = cardNo;
        this.departureDate = departureDate;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getLessAmount() {
        return lessAmount;
    }

    public void setLessAmount(int lessAmount) {
        this.lessAmount = lessAmount;
    }

    public int getNetPayable() {
        return netPayable;
    }

    public void setNetPayable(int netPayable) {
        this.netPayable = netPayable;
    }

    public int getAdvance() {
        return advance;
    }

    public void setAdvance(int advance) {
        this.advance = advance;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getFinalDue() {
        return finalDue;
    }

    public void setFinalDue(int finalDue) {
        this.finalDue = finalDue;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
    
    
}
