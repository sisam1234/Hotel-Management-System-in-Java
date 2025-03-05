package system;

import db.Room;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class RoomDetails extends JFrame {

    JTable table;
    JButton back;

    RoomDetails() throws SQLException, ClassNotFoundException {
        setTitle("Room Details");
        setLayout(null);
        setLocation(350, 150);
        setSize(900, 600);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        String[] columnNames = {"Room No", "Availability", "Clean Status", "Bed Type", "Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model);
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        table.setRowHeight(25); // Set row height for better visibility

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 860, 0); // Set initial height to 0
        panel.add(scrollPane);

        int rowCount = loadRoomData(model);
        int tableHeight = rowCount * table.getRowHeight(); // Adjust table height dynamically
        scrollPane.setPreferredSize(new Dimension(860, Math.min(tableHeight, 400))); // Limit max height
        scrollPane.setBounds(10, 40, 860, Math.min(tableHeight, 400));

        // Back Button
        back = new JButton("Back");
        back.setBounds(750, 500, 100, 30);
        panel.add(back);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Close the current window
            }
        });

        setVisible(true);
    }

    private int loadRoomData(DefaultTableModel model) throws ClassNotFoundException {
        int rowCount = 0;
        try {
            Room room = new Room();
            try (ResultSet rs = room.getRoom()) { // Use try-with-resources
                while (rs.next()) {
                    int roomNo = rs.getInt("roomno");
                    String availability = rs.getString("availability");
                    String cleanStatus = rs.getString("cleanstatus");
                    String bedType = rs.getString("bedtype");
                    double price = rs.getDouble("price");

                    model.addRow(new Object[]{roomNo, availability, cleanStatus, bedType, price});
                    rowCount++;
                }
            }
            room.closeResources(); // Ensure DB connection is closed
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rowCount;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new RoomDetails();
    }
}
