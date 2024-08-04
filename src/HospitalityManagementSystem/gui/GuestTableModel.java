package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.Entity.Guest;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GuestTableModel extends AbstractTableModel {

    private final List<Guest> guests;
    private final String[] columnNames = {"ID", "Name", "Email", "Phone Number"};

    public GuestTableModel() {
        this.guests = new ArrayList<>();
    }

    public void setGuests(List<Guest> guests) {
        this.guests.clear();
        this.guests.addAll(guests);
        fireTableDataChanged();
    }

    public Guest getGuestAt(int rowIndex) {
        return guests.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return guests.size();
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
        Guest guest = guests.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return guest.getId();
            case 1:
                return guest.getName();
            case 2:
                return guest.getEmail();
            case 3:
                return guest.getPhoneNumber();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
