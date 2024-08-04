package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.Entity.Room;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class RoomDialog extends JDialog {

    private JTextField hotelIdField;
    private JTextField roomNumberField;
    private JTextField typeField;
    private JTextField priceField;
    private JTextField statusField;
    private boolean succeeded;
    private Room room;

    public RoomDialog(Frame parent) {
        super(parent, "Add/Edit Room", true);
        initComponents();
    }

    public RoomDialog(Frame parent, Room room) {
        super(parent, "Add/Edit Room", true);
        this.room = room;
        initComponents();
        fillFields();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel hotelIdLabel = new JLabel("Hotel ID:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(hotelIdLabel, cs);

        hotelIdField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(hotelIdField, cs);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(roomNumberLabel, cs);

        roomNumberField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(roomNumberField, cs);

        JLabel typeLabel = new JLabel("Type:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(typeLabel, cs);

        typeField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(typeField, cs);

        JLabel priceLabel = new JLabel("Price:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(priceLabel, cs);

        priceField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(priceField, cs);

        JLabel statusLabel = new JLabel("Status:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        panel.add(statusLabel, cs);

        statusField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 2;
        panel.add(statusField, cs);

        panel.setBorder(new LineBorder(Color.GRAY));

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        JPanel bp = new JPanel();
        bp.add(btnOk);
        bp.add(btnCancel);

        btnOk.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (room == null) {
                    room = new Room();
                }
                room.setHotelId(Integer.parseInt(hotelIdField.getText()));
                room.setRoomNumber(roomNumberField.getText());
                room.setType(typeField.getText());
                room.setPrice(Double.parseDouble(priceField.getText()));
                room.setStatus(statusField.getText());
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
        if (room != null) {
            hotelIdField.setText(String.valueOf(room.getHotelId()));
            roomNumberField.setText(room.getRoomNumber());
            typeField.setText(room.getType());
            priceField.setText(String.valueOf(room.getPrice()));
            statusField.setText(room.getStatus());
        }
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Room getRoom() {
        return room;
    }
}
