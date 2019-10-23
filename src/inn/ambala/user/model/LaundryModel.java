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
public class LaundryModel {
    String mrNo;
    int roomNo;
    int laundryCode;
    String laundryType;
    String clothType;
    int price;
    int quantity;
    int total;
    Date date;
    String status;

    public LaundryModel() {
    }

    public LaundryModel(String mrNo, int roomNo, int laundryCode, String laundryType, String clothType, int price, int quantity, int total, Date date, String status) {
        this.mrNo = mrNo;
        this.roomNo = roomNo;
        this.laundryCode = laundryCode;
        this.laundryType = laundryType;
        this.clothType = clothType;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getLaundryCode() {
        return laundryCode;
    }

    public void setLaundryCode(int laundryCode) {
        this.laundryCode = laundryCode;
    }

    public String getLaundryType() {
        return laundryType;
    }

    public void setLaundryType(String laundryType) {
        this.laundryType = laundryType;
    }

    public String getClothType() {
        return clothType;
    }

    public void setClothType(String clothType) {
        this.clothType = clothType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
    
    
}
