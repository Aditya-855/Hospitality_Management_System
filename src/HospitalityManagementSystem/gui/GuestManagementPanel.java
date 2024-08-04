package HospitalityManagementSystem.gui;

import HospitalityManagementSystem.Entity.Guest;
import HospitalityManagementSystem.dao.GuestDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuestManagementPanel extends JPanel {

    private JTable guestTable;
    private GuestTableModel guestTableModel;
    private GuestDAO guestDAO;

    public GuestManagementPanel() {
        guestDAO = new GuestDAO();
        guestTableModel = new GuestTableModel();
        guestTable = new JTable(guestTableModel);

        setLayout(new BorderLayout());
        add(new JScrollPane(guestTable), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        refreshGuestTable();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add Guest");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuestDialog dialog = new GuestDialog((Frame) SwingUtilities.getWindowAncestor(GuestManagementPanel.this));
                dialog.setVisible(true);
                if (dialog.isSucceeded()) {
                    guestDAO.addGuest(dialog.getGuest());
                    refreshGuestTable();
                }
            }
        });

        JButton editButton = new JButton("Edit Guest");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = guestTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Guest guest = guestTableModel.getGuestAt(selectedRow);
                    GuestDialog dialog = new GuestDialog((Frame) SwingUtilities.getWindowAncestor(GuestManagementPanel.this), guest);
                    dialog.setVisible(true);
                    if (dialog.isSucceeded()) {
                        guestDAO.updateGuest(dialog.getGuest());
                        refreshGuestTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(GuestManagementPanel.this, "Please select a guest to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Guest");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = guestTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int confirmation = JOptionPane.showConfirmDialog(GuestManagementPanel.this, "Are you sure you want to delete this guest?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        Guest guest = guestTableModel.getGuestAt(selectedRow);
                        guestDAO.deleteGuest(guest.getId());
                        refreshGuestTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(GuestManagementPanel.this, "Please select a guest to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);

        return panel;
    }

    public void refreshGuestTable() {
        List<Guest> guests = guestDAO.getAllGuests();
        guestTableModel.setGuests(guests);
    }
}
