package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.dao.HotelDAO;
import HospitalityManagementSystem.Entity.Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HotelManagementPanel extends JPanel {

    private HotelDAO hotelDAO;
    private JTable hotelTable;
    private HotelTableModel hotelTableModel;

    public HotelManagementPanel() {
        hotelDAO = new HotelDAO();
        setLayout(new BorderLayout());

        // Create table model
        hotelTableModel = new HotelTableModel();
        hotelTable = new JTable(hotelTableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(hotelTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Add Hotel");
        JButton editButton = new JButton("Edit Hotel");
        JButton deleteButton = new JButton("Delete Hotel");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Add button listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelDialog hotelDialog = new HotelDialog((Frame) SwingUtilities.getWindowAncestor(HotelManagementPanel.this));
                hotelDialog.setVisible(true);
                if (hotelDialog.isSucceeded()) {
                    hotelDAO.addHotel(hotelDialog.getHotel());
                    refreshHotelTable();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = hotelTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Hotel hotel = hotelTableModel.getHotelAt(selectedRow);
                    HotelDialog hotelDialog = new HotelDialog((Frame) SwingUtilities.getWindowAncestor(HotelManagementPanel.this), hotel);
                    hotelDialog.setVisible(true);
                    if (hotelDialog.isSucceeded()) {
                        hotelDAO.updateHotel(hotelDialog.getHotel());
                        refreshHotelTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(HotelManagementPanel.this, "Please select a hotel to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = hotelTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(HotelManagementPanel.this, "Are you sure you want to delete the selected hotel?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Hotel hotel = hotelTableModel.getHotelAt(selectedRow);
                        hotelDAO.deleteHotel(hotel.getId());
                        refreshHotelTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(HotelManagementPanel.this, "Please select a hotel to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        refreshHotelTable();
    }

    private void refreshHotelTable() {
        List<Hotel> hotels = hotelDAO.getAllHotels();
        hotelTableModel.setHotels(hotels);
    }
}
