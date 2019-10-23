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
public class OthersModel {
    private String othersNo;
    private String mrNo;
    private int roomNo;
    private String description;
    private int amount;
    private Date date;

    public OthersModel() {
    }

    public OthersModel(String othersNo, String mrNo, int roomNo, String description, int amount, Date date) {
        this.othersNo = othersNo;
        this.mrNo = mrNo;
        this.roomNo = roomNo;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getOthersNo() {
        return othersNo;
    }

    public void setOthersNo(String othersNo) {
        this.othersNo = othersNo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
