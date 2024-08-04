package HospitalityManagementSystem.dao;


import HospitalityManagementSystem.DatabaseConnector;
import HospitalityManagementSystem.Entity.Room;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void addRoom(Room room) {
        String query = "INSERT INTO Rooms (hotel_id, room_number, type, price, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getHotelId());
            statement.setString(2, room.getRoomNumber());
            statement.setString(3, room.getType());
            statement.setBigDecimal(4, BigDecimal.valueOf(room.getPrice()));
            statement.setString(5, room.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Rooms";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setRoomNumber(resultSet.getString("room_number"));
                room.setType(resultSet.getString("type"));
                room.setPrice(resultSet.getDouble("price"));
                room.setStatus(resultSet.getString("status"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void updateRoom(Room room) {
        String query = "UPDATE Rooms SET hotel_id = ?, room_number = ?, type = ?, price = ?, status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getHotelId());
            statement.setString(2, room.getRoomNumber());
            statement.setString(3, room.getType());
            statement.setBigDecimal(4, BigDecimal.valueOf(room.getPrice()));
            statement.setString(5, room.getStatus());
            statement.setInt(6, room.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int roomId) {
        String query = "DELETE FROM Rooms WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
