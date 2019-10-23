/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.conference.model;

/**
 *
 * @author REZA
 */
public class AmountDetailsModel {
    String voucharNo;
    int rent;
    int foodCharges;
    int projector;
    int osp;
    int videos;
    int soundSystem;
    int others;
    int discount;
    int total;

    public AmountDetailsModel() {
    }

    public AmountDetailsModel(String voucharNo, int rent, int foodCharges, int projector, int osp, int videos, int soundSystem, int others, int discount, int total) {
        this.voucharNo = voucharNo;
        this.rent = rent;
        this.foodCharges = foodCharges;
        this.projector = projector;
        this.osp = osp;
        this.videos = videos;
        this.soundSystem = soundSystem;
        this.others = others;
        this.discount = discount;
        this.total = total;
    }

    public String getVoucharNo() {
        return voucharNo;
    }

    public void setVoucharNo(String voucharNo) {
        this.voucharNo = voucharNo;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getFoodCharges() {
        return foodCharges;
    }

    public void setFoodCharges(int foodCharges) {
        this.foodCharges = foodCharges;
    }

    public int getProjector() {
        return projector;
    }

    public void setProjector(int projector) {
        this.projector = projector;
    }

    public int getOsp() {
        return osp;
    }

    public void setOsp(int osp) {
        this.osp = osp;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public int getSoundSystem() {
        return soundSystem;
    }

    public void setSoundSystem(int soundSystem) {
        this.soundSystem = soundSystem;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
