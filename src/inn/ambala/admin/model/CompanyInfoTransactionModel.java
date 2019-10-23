/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.admin.model;

import java.util.Date;

/**
 *
 * @author REZA
 */
public class CompanyInfoTransactionModel {
    private String id;
    private int companyCode;
    private String companyName;
    private int amount;
    private String status;
    private String reason;
    private String mrNo;
    private Date date;

    public CompanyInfoTransactionModel() {
    }

    public CompanyInfoTransactionModel(String id, int companyCode, String companyName, int amount, String status, String reason, String mrNo, Date date) {
        this.id = id;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.amount = amount;
        this.status = status;
        this.reason = reason;
        this.mrNo = mrNo;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
