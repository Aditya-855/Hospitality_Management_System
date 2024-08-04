package HospitalityManagementSystem.gui;


import HospitalityManagementSystem.dao.ReservationDAO;
import HospitalityManagementSystem.Entity.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReservationPanelManagement extends JPanel {

    private final ReservationDAO reservationDAO;
    private final ReservationTableModel reservationTableModel;
    private final JTable reservationTable;

    public ReservationPanelManagement() {
        reservationDAO = new ReservationDAO();
        reservationTableModel = new ReservationTableModel();
        reservationTable = new JTable(reservationTableModel);

        setLayout(new BorderLayout());

        // Add table to JScrollPane
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add buttons panel
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Reservation");
        JButton editButton = new JButton("Edit Reservation");
        JButton deleteButton = new JButton("Delete Reservation");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservationDialog(null);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = reservationTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Reservation reservation = reservationTableModel.getReservationAt(selectedRow);
                    showReservationDialog(reservation);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a reservation to edit.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = reservationTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int reservationId = reservationTableModel.getReservationAt(selectedRow).getId();
                    int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this reservation?");
                    if (confirmation == JOptionPane.YES_OPTION) {
                        reservationDAO.deleteReservation(reservationId);
                        loadReservations();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a reservation to delete.");
                }
            }
        });

        loadReservations();
    }

    private void showReservationDialog(Reservation reservation) {
        ReservationDialog dialog = new ReservationDialog((Frame) SwingUtilities.getWindowAncestor(this), reservation);
        dialog.setVisible(true);
        if (dialog.isSucceeded()) {
            Reservation updatedReservation = dialog.getReservation();
            if (updatedReservation.getId() == 0) {
                reservationDAO.addReservation(updatedReservation);
            } else {
                reservationDAO.updateReservation(updatedReservation);
            }
            loadReservations();
        }
    }

    private void loadReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        reservationTableModel.setReservations(reservations);
    }
}
