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
public class RoomRentModel {
    private int roomId;
    private String roomType;
    private int roomRent;

    public RoomRentModel() {
    }

    public RoomRentModel(int roomId, String roomType, int roomRent) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomRent = roomRent;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(int roomRent) {
        this.roomRent = roomRent;
    }
    
    
}
