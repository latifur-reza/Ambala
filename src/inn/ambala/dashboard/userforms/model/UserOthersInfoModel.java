/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.dashboard.userforms.model;

/**
 *
 * @author REZA
 */
public class UserOthersInfoModel {
    String mrNo;
    int roomNo;
    String registrationMadeBy;
    String localCompany;
    String localCompanyAddress;
    String probableStay;
    int totalBillCollected;
    String whereIssued;
    int noOfGuest;

    public UserOthersInfoModel() {
    }

    public UserOthersInfoModel(String mrNo, int roomNo, String registrationMadeBy, String localCompany, String localCompanyAddress, String probableStay, int totalBillCollected, String whereIssued, int noOfGuest) {
        this.mrNo = mrNo;
        this.roomNo = roomNo;
        this.registrationMadeBy = registrationMadeBy;
        this.localCompany = localCompany;
        this.localCompanyAddress = localCompanyAddress;
        this.probableStay = probableStay;
        this.totalBillCollected = totalBillCollected;
        this.whereIssued = whereIssued;
        this.noOfGuest = noOfGuest;
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

    public String getRegistrationMadeBy() {
        return registrationMadeBy;
    }

    public void setRegistrationMadeBy(String registrationMadeBy) {
        this.registrationMadeBy = registrationMadeBy;
    }

    public String getLocalCompany() {
        return localCompany;
    }

    public void setLocalCompany(String localCompany) {
        this.localCompany = localCompany;
    }

    public String getLocalCompanyAddress() {
        return localCompanyAddress;
    }

    public void setLocalCompanyAddress(String localCompanyAddress) {
        this.localCompanyAddress = localCompanyAddress;
    }

    public String getProbableStay() {
        return probableStay;
    }

    public void setProbableStay(String probableStay) {
        this.probableStay = probableStay;
    }

    public int getTotalBillCollected() {
        return totalBillCollected;
    }

    public void setTotalBillCollected(int totalBillCollected) {
        this.totalBillCollected = totalBillCollected;
    }

    public String getWhereIssued() {
        return whereIssued;
    }

    public void setWhereIssued(String whereIssued) {
        this.whereIssued = whereIssued;
    }

    public int getNoOfGuest() {
        return noOfGuest;
    }

    public void setNoOfGuest(int noOfGuest) {
        this.noOfGuest = noOfGuest;
    }

}
