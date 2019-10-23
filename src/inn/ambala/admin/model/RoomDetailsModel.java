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
public class RoomDetailsModel {
    private int roomId;
    private int roomNo;
    private int floorNo;
    private String roomType;
    private String aircondition;
    private String status;

    public RoomDetailsModel() {
    }

    public RoomDetailsModel(int roomId, int roomNo, int floorNo, String roomType, String aircondition, String status) {
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.floorNo = floorNo;
        this.roomType = roomType;
        this.aircondition = aircondition;
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAircondition() {
        return aircondition;
    }

    public void setAircondition(String aircondition) {
        this.aircondition = aircondition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
