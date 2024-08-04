package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.Entity.Guest;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestDialog extends JDialog {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private boolean succeeded;
    private Guest guest;

    public GuestDialog(Frame parent) {
        super(parent, "Add/Edit Guest", true);
        initComponents();
    }

    public GuestDialog(Frame parent, Guest guest) {
        super(parent, "Add/Edit Guest", true);
        this.guest = guest;
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

        JLabel emailLabel = new JLabel("Email:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(emailLabel, cs);

        emailField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(emailField, cs);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(phoneNumberLabel, cs);

        phoneNumberField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(phoneNumberField, cs);

        panel.setBorder(new LineBorder(Color.GRAY));

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        JPanel bp = new JPanel();
        bp.add(btnOk);
        bp.add(btnCancel);

        btnOk.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (guest == null) {
                    guest = new Guest();
                }
                guest.setName(nameField.getText());
                guest.setEmail(emailField.getText());
                guest.setPhoneNumber(phoneNumberField.getText());
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
        if (guest != null) {
            nameField.setText(guest.getName());
            emailField.setText(guest.getEmail());
            phoneNumberField.setText(guest.getPhoneNumber());
        }
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Guest getGuest() {
        return guest;
    }
}
