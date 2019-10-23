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
public class RestaurantModel {
    private int restaurantId;
    private int itemCode;
    private String itemName;
    private String foodType;
    private int itemPrice;

    public RestaurantModel() {
    }

    public RestaurantModel(int restaurantId, int itemCode, String itemName, String foodType, int itemPrice) {
        this.restaurantId = restaurantId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.foodType = foodType;
        this.itemPrice = itemPrice;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    
}
