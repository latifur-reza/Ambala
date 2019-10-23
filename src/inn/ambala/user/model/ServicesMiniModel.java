/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.user.model;

import java.util.Date;

/**
 *
 * @author REZA
 */
public class ServicesMiniModel {
    String mrNo;
    int totalBill;
    int payBill;
    Date date;
    String status;
    String service;

    public ServicesMiniModel() {
    }

    public ServicesMiniModel(String mrNo, int totalBill, int payBill, Date date, String status, String service) {
        this.mrNo = mrNo;
        this.totalBill = totalBill;
        this.payBill = payBill;
        this.date = date;
        this.status = status;
        this.service = service;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public int getPayBill() {
        return payBill;
    }

    public void setPayBill(int payBill) {
        this.payBill = payBill;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    
}
