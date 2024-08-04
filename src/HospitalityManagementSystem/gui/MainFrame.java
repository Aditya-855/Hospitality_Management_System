package HospitalityManagementSystem.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel sidePanel;

    public MainFrame() {
        setTitle("Hospitality Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout manager
        setLayout(new BorderLayout());

        // Add side panel for buttons
        sidePanel = new JPanel();
        sidePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JButton manageHotelsButton = new JButton("Hotels");
        JButton manageRoomsButton = new JButton("Rooms");
        JButton manageGuestsButton = new JButton("Guests");
        JButton manageReservationsButton = new JButton("Reservations");

        // Center the text on buttons
        manageHotelsButton.setHorizontalAlignment(SwingConstants.CENTER);
        manageRoomsButton.setHorizontalAlignment(SwingConstants.CENTER);
        manageGuestsButton.setHorizontalAlignment(SwingConstants.CENTER);
        manageReservationsButton.setHorizontalAlignment(SwingConstants.CENTER);

        // Add buttons to side panel with GridBagConstraints
        gbc.gridy = 0;
        gbc.weighty = 0.25;
        sidePanel.add(manageHotelsButton, gbc);

        gbc.gridy = 1;
        sidePanel.add(manageRoomsButton, gbc);

        gbc.gridy = 2;
        sidePanel.add(manageGuestsButton, gbc);

        gbc.gridy = 3;
        sidePanel.add(manageReservationsButton, gbc);

        // Add side panel to frame
        add(sidePanel, BorderLayout.WEST);

        // Add main panel
        mainPanel = new JPanel(new CardLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        manageHotelsButton.addActionListener(e -> showHotelManagementPanel());
        manageRoomsButton.addActionListener(e -> showRoomManagementPanel());
        manageGuestsButton.addActionListener(e -> showGuestManagementPanel());
        manageReservationsButton.addActionListener(e -> showReservationManagementPanel());
    }

    private void showHotelManagementPanel() {
        mainPanel.removeAll();
        mainPanel.add(new HotelManagementPanel());
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showRoomManagementPanel() {
        mainPanel.removeAll();
        mainPanel.add(new RoomsManagementPanel());
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showGuestManagementPanel() {
        mainPanel.removeAll();
        mainPanel.add(new GuestManagementPanel());
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showReservationManagementPanel() {
        mainPanel.removeAll();
        mainPanel.add(new ReservationPanelManagement());
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
