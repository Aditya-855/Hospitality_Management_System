package HospitalityManagementSystem.Entity;


import java.awt.*;

public class Hotel  {
    private int id;
    private String name;
    private String location;
    private String amenities;

    // Constructors
    public Hotel() {}

    public Hotel(int id, String name, String location, String amenities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.amenities = amenities;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "Hotel [id=" + id + ", name=" + name + ", location=" + location + ", amenities=" + amenities + "]";
    }
}
