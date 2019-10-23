/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.model;

import java.util.Date;

/**
 *
 * @author REZA
 */
public class UsersRoomInfoModel {
    String mrNo;
    int roomNo;
    String roomType;
    Date arrivalDate;
    Date departureDate;
    int discountPercent;
    int discountCash;
    int roomTarrif;
    int roomBill;
    String status;

    public UsersRoomInfoModel() {
    }

    public UsersRoomInfoModel(String mrNo, int roomNo, String roomType, Date arrivalDate, Date departureDate, int discountPercent, int discountCash, int roomTarrif, int roomBill, String status) {
        this.mrNo = mrNo;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.discountPercent = discountPercent;
        this.discountCash = discountCash;
        this.roomTarrif = roomTarrif;
        this.roomBill = roomBill;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getDiscountCash() {
        return discountCash;
    }

    public void setDiscountCash(int discountCash) {
        this.discountCash = discountCash;
    }

    public int getRoomTarrif() {
        return roomTarrif;
    }

    public void setRoomTarrif(int roomTarrif) {
        this.roomTarrif = roomTarrif;
    }

    public int getRoomBill() {
        return roomBill;
    }

    public void setRoomBill(int roomBill) {
        this.roomBill = roomBill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
