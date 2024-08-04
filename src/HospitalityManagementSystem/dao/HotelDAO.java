package HospitalityManagementSystem.dao;

import HospitalityManagementSystem.Entity.Hotel;
import HospitalityManagementSystem.DatabaseConnector;
import HospitalityManagementSystem.Entity.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    public void addHotel(Hotel hotel) {
        String query = "INSERT INTO Hotels (name, location, amenities) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setString(3, hotel.getAmenities());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotels";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id"));
                hotel.setName(resultSet.getString("name"));
                hotel.setLocation(resultSet.getString("location"));
                hotel.setAmenities(resultSet.getString("amenities"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public void updateHotel(Hotel hotel) {
        String query = "UPDATE Hotels SET name = ?, location = ?, amenities = ? WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setString(3, hotel.getAmenities());
            statement.setInt(4, hotel.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHotel(int hotelId) {
        String query = "DELETE FROM Hotels WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, hotelId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
