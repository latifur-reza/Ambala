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
public class ReferenceInfoModel {
    private int referenceId;
    private int referenceCode;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String mobile;

    public ReferenceInfoModel() {
    }

    public ReferenceInfoModel(int referenceId, int referenceCode, String name, String address, String email, String phone, String mobile) {
        this.referenceId = referenceId;
        this.referenceCode = referenceCode;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public int getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(int referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    
}
