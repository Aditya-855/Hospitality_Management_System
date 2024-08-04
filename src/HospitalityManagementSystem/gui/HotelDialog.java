package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.Entity.Hotel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelDialog extends JDialog {

    private JTextField nameField;
    private JTextField locationField;
    private JTextArea amenitiesField;
    private boolean succeeded;
    private Hotel hotel;

    public HotelDialog(Frame parent) {
        super(parent, "Add/Edit Hotel", true);
        initComponents();
    }

    public HotelDialog(Frame parent, Hotel hotel) {
        super(parent, "Add/Edit Hotel", true);
        this.hotel = hotel;
        initComponents();
        fillFields();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(nameLabel, cs);

        nameField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(nameField, cs);

        JLabel locationLabel = new JLabel("Location:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(locationLabel, cs);

        locationField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(locationField, cs);

        JLabel amenitiesLabel = new JLabel("Amenities:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(amenitiesLabel, cs);

        amenitiesField = new JTextArea(3, 20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(new JScrollPane(amenitiesField), cs);

        panel.setBorder(new LineBorder(Color.GRAY));

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        JPanel bp = new JPanel();
        bp.add(btnOk);
        bp.add(btnCancel);

        btnOk.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (hotel == null) {
                    hotel = new Hotel();
                }
                hotel.setName(nameField.getText());
                hotel.setLocation(locationField.getText());
                hotel.setAmenities(amenitiesField.getText());
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
        if (hotel != null) {
            nameField.setText(hotel.getName());
            locationField.setText(hotel.getLocation());
            amenitiesField.setText(hotel.getAmenities());
        }
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Hotel getHotel() {
        return hotel;
    }
}
