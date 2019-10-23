/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.checkout.model;

/**
 *
 * @author REZA
 */
public class PayAbout {
    String mrNo;
    int roomRent;
    int restaurant;
    int laundry;
    int others;
    int late;
    int discountTK;
    int discountPercent;
    int total;
    int serviceCharge;
    int vat;

    public PayAbout() {
    }

    public PayAbout(String mrNo, int roomRent, int restaurant, int laundry, int others, int late, int discountTK, int discountPercent, int total, int serviceCharge, int vat) {
        this.mrNo = mrNo;
        this.roomRent = roomRent;
        this.restaurant = restaurant;
        this.laundry = laundry;
        this.others = others;
        this.late = late;
        this.discountTK = discountTK;
        this.discountPercent = discountPercent;
        this.total = total;
        this.serviceCharge = serviceCharge;
        this.vat = vat;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public int getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(int roomRent) {
        this.roomRent = roomRent;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }

    public int getLaundry() {
        return laundry;
    }

    public void setLaundry(int laundry) {
        this.laundry = laundry;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getDiscountTK() {
        return discountTK;
    }

    public void setDiscountTK(int discountTK) {
        this.discountTK = discountTK;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(int serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }
    
    
}
