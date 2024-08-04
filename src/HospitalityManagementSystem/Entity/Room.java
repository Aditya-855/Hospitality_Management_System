package HospitalityManagementSystem.Entity;

import java.math.BigDecimal;

public class Room {
    private int id;
    private int hotelId;
    private String roomNumber;
    private String type;
    private double price;
    private String status;

    // Constructors
    public Room() {}

    public Room(int id, int hotelId, String roomNumber, String type, double price, String status) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", hotelId=" + hotelId + ", roomNumber=" + roomNumber + ", type=" + type + ", price="
                + price + ", status=" + status + "]";
    }
}
