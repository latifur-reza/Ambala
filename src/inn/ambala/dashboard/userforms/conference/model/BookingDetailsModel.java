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
public class BookingDetailsModel {
    String voucherNo;
    Date arrivalDate;
    String company;
    String address;
    String area;
    String areaCode;
    String reservation;
    Date from;
    Date to;
    String reason;
    String status;

    public BookingDetailsModel() {
    }

    public BookingDetailsModel(String voucherNo, Date arrivalDate, String company, String address, String area, String areaCode, String reservation, Date from, Date to, String reason, String status) {
        this.voucherNo = voucherNo;
        this.arrivalDate = arrivalDate;
        this.company = company;
        this.address = address;
        this.area = area;
        this.areaCode = areaCode;
        this.reservation = reservation;
        this.from = from;
        this.to = to;
        this.reason = reason;
        this.status = status;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
