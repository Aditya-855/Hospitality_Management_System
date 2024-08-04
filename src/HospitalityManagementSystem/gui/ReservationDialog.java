package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.Entity.Reservation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationDialog extends JDialog {

    private JTextField guestIdField;
    private JTextField roomIdField;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private boolean succeeded;
    private Reservation reservation;

    public ReservationDialog(Frame parent) {
        super(parent, "Add/Edit Reservation", true);
        initComponents();
    }

    public ReservationDialog(Frame parent, Reservation reservation) {
        super(parent, "Add/Edit Reservation", true);
        this.reservation = reservation;
        initComponents();
        fillFields();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel guestIdLabel = new JLabel("Guest ID:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(guestIdLabel, cs);

        guestIdField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(guestIdField, cs);

        JLabel roomIdLabel = new JLabel("Room ID:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(roomIdLabel, cs);

        roomIdField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(roomIdField, cs);

        JLabel checkInDateLabel = new JLabel("Check-In Date:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(checkInDateLabel, cs);

        checkInDateField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(checkInDateField, cs);

        JLabel checkOutDateLabel = new JLabel("Check-Out Date:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(checkOutDateLabel, cs);

        checkOutDateField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(checkOutDateField, cs);

        panel.setBorder(new LineBorder(Color.GRAY));

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        JPanel bp = new JPanel();
        bp.add(btnOk);
        bp.add(btnCancel);

        btnOk.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (reservation == null) {
                    reservation = new Reservation();
                }
                reservation.setGuestId(Integer.parseInt(guestIdField.getText()));
                reservation.setRoomId(Integer.parseInt(roomIdField.getText()));
                reservation.setCheckInDate(String.valueOf(java.sql.Date.valueOf(checkInDateField.getText()).toLocalDate()));
                reservation.setCheckOutDate(String.valueOf(java.sql.Date.valueOf(checkOutDateField.getText()).toLocalDate()));
                succeeded = true;
                dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                succeeded = false;
                dispose();
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(getParent());
    }

    private void fillFields() {
        if (reservation != null) {
            guestIdField.setText(String.valueOf(reservation.getGuestId()));
            roomIdField.setText(String.valueOf(reservation.getRoomId()));
            checkInDateField.setText(reservation.getCheckInDate().toString());
            checkOutDateField.setText(reservation.getCheckOutDate().toString());
        }
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
