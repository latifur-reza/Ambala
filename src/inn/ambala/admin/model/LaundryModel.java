/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.admin.model;

/**
 *
 * @author REZA
 */
public class LaundryModel {
    private int laundryId;
    private int laundryCode;
    private String laundryType;
    private String clothType;
    private int price;

    public LaundryModel() {
    }

    public LaundryModel(int laundryId, int laundryCode, String laundryType, String clothType, int price) {
        this.laundryId = laundryId;
        this.laundryCode = laundryCode;
        this.laundryType = laundryType;
        this.clothType = clothType;
        this.price = price;
    }

    public int getLaundryId() {
        return laundryId;
    }

    public void setLaundryId(int laundryId) {
        this.laundryId = laundryId;
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
    
    
}
