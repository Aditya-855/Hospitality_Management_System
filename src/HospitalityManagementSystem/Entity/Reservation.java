package HospitalityManagementSystem.Entity;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int guestId;
    private int roomId;
    private String checkInDate;
    private String checkOutDate;

    // Constructors
    public Reservation() {}

    public Reservation(int id, int guestId, int roomId, String checkInDate, String checkOutDate) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", guestId=" + guestId + ", roomId=" + roomId + ", checkInDate=" + checkInDate
                + ", checkOutDate=" + checkOutDate + "]";
    }
}
