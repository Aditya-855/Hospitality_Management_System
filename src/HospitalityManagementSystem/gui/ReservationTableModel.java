package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.Entity.Reservation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ReservationTableModel extends AbstractTableModel {

    private final List<Reservation> reservations;
    private final String[] columnNames = {"ID", "Guest ID", "Room ID", "Check-In Date", "Check-Out Date"};

    public ReservationTableModel() {
        this.reservations = new ArrayList<>();
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations.clear();
        this.reservations.addAll(reservations);
        fireTableDataChanged();
    }

    public Reservation getReservationAt(int rowIndex) {
        return reservations.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return reservations.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reservation reservation = reservations.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return reservation.getId();
            case 1:
                return reservation.getGuestId();
            case 2:
                return reservation.getRoomId();
            case 3:
                return reservation.getCheckInDate();
            case 4:
                return reservation.getCheckOutDate();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
