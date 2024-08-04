package HospitalityManagementSystem.gui;



import HospitalityManagementSystem.Entity.Hotel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class HotelTableModel extends AbstractTableModel {

    private final List<Hotel> hotels;
    private final String[] columnNames = {"ID", "Name", "Location", "Amenities"};

    public HotelTableModel() {
        this.hotels = new ArrayList<>();
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels.clear();
        this.hotels.addAll(hotels);
        fireTableDataChanged();
    }

    public Hotel getHotelAt(int rowIndex) {
        return hotels.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return hotels.size();
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
        Hotel hotel = hotels.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return hotel.getId();
            case 1:
                return hotel.getName();
            case 2:
                return hotel.getLocation();
            case 3:
                return hotel.getAmenities();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
