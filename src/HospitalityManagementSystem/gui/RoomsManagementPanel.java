package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.Entity.Room;
import HospitalityManagementSystem.dao.RoomDAO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RoomsManagementPanel extends JPanel {
    private final RoomDAO roomDAO;
    private final RoomTableModel roomTableModel;
    private final JTable roomTable;

    public RoomsManagementPanel() {
        roomDAO = new RoomDAO();
        roomTableModel = new RoomTableModel();
        roomTable = new JTable(roomTableModel);

        setLayout(new BorderLayout());

        // Create and set up the table
        JScrollPane scrollPane = new JScrollPane(roomTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create buttons
        JButton addButton = new JButton("Add Room");
        JButton editButton = new JButton("Edit Room");
        JButton deleteButton = new JButton("Delete Room");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load room data
        loadRoomData();

        // Add button listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomDialog dialog = new RoomDialog((Frame) SwingUtilities.getWindowAncestor(RoomsManagementPanel.this));
                dialog.setVisible(true);
                if (dialog.isSucceeded()) {
                    Room room = dialog.getRoom();
                    roomDAO.addRoom(room);
                    loadRoomData();  // Refresh table data
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = roomTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Room room = roomTableModel.getRoomAt(selectedRow);
                    RoomDialog dialog = new RoomDialog((Frame) SwingUtilities.getWindowAncestor(RoomsManagementPanel.this), room);
                    dialog.setVisible(true);
                    if (dialog.isSucceeded()) {
                        Room updatedRoom = dialog.getRoom();
                        roomDAO.updateRoom(updatedRoom);
                        loadRoomData();  // Refresh table data
                    }
                } else {
                    JOptionPane.showMessageDialog(RoomsManagementPanel.this, "Please select a room to edit.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = roomTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Room room = roomTableModel.getRoomAt(selectedRow);
                    int confirm = JOptionPane.showConfirmDialog(RoomsManagementPanel.this, "Are you sure you want to delete this room?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        roomDAO.deleteRoom(room.getId());
                        loadRoomData();  // Refresh table data
                    }
                } else {
                    JOptionPane.showMessageDialog(RoomsManagementPanel.this, "Please select a room to delete.");
                }
            }
        });
    }

    private void loadRoomData() {
        List<Room> rooms = roomDAO.getAllRooms();
        roomTableModel.setRooms(rooms);
    }
}
