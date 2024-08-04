package HospitalityManagementSystem.gui;



import HospitalityManagementSystem.Entity.Room;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class RoomTableModel extends AbstractTableModel {

    private final List<Room> rooms;
    private final String[] columnNames = {"ID", "Hotel ID", "Room Number", "Type", "Price", "Status"};

    public RoomTableModel() {
        this.rooms = new ArrayList<>();
    }

    public void setRooms(List<Room> rooms) {
        this.rooms.clear();
        this.rooms.addAll(rooms);
        fireTableDataChanged();
    }

    public Room getRoomAt(int rowIndex) {
        return rooms.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return rooms.size();
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
        Room room = rooms.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return room.getId();
            case 1:
                return room.getHotelId();
            case 2:
                return room.getRoomNumber();
            case 3:
                return room.getType();
            case 4:
                return room.getPrice();
            case 5:
                return room.getStatus();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
