package HospitalityManagementSystem.dao;

import HospitalityManagementSystem.DatabaseConnector;
import HospitalityManagementSystem.Entity.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
public class ReservationDAO {

    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO Reservations (guest_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getGuestId());
            statement.setInt(2, reservation.getRoomId());
            statement.setDate(3, java.sql.Date.valueOf(reservation.getCheckInDate()));
            statement.setDate(4, java.sql.Date.valueOf(reservation.getCheckOutDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setGuestId(resultSet.getInt("guest_id"));
                reservation.setRoomId(resultSet.getInt("room_id"));
                reservation.setCheckInDate(String.valueOf(resultSet.getDate("check_in_date").toLocalDate()));
                reservation.setCheckOutDate(String.valueOf(resultSet.getDate("check_out_date").toLocalDate()));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void updateReservation(Reservation reservation) {
        String query = "UPDATE Reservations SET guest_id = ?, room_id = ?, check_in_date = ?, check_out_date = ? WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getGuestId());
            statement.setInt(2, reservation.getRoomId());
            statement.setDate(3, java.sql.Date.valueOf(reservation.getCheckInDate()));
            statement.setDate(4, java.sql.Date.valueOf(reservation.getCheckOutDate()));
            statement.setInt(5, reservation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int reservationId) {
        String query = "DELETE FROM Reservations WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
