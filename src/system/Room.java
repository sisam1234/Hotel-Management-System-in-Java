package system;

import db.RoomDetails;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Room extends JFrame {

    JTable table;
    JButton back;

    Room() throws SQLException, ClassNotFoundException {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);
        String[] columnNames = {"Room No", "Availability", "Clean Status", "Bed Type", "Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model);
        table.setBounds(10, 40, 500, 400);
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        panel.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 860, 500);
        panel.add(scrollPane);
        loadRoomData(model);
        setLayout(null);
        setLocation(400, 150);
        setSize(850, 550);
        setVisible(true);
    }

    private void loadRoomData(DefaultTableModel model) throws ClassNotFoundException {
        try {
            RoomDetails room = new RoomDetails();
            ResultSet rs = room.getRoom();
            while (rs.next()) {
                int roomNo = rs.getInt("roomno");
                String availability = rs.getString("availability");
                String cleanStatus = rs.getString("cleanstatus");
                String bedType = rs.getString("bedtype");
                double price = rs.getDouble("price");

                model.addRow(new Object[]{roomNo, availability, cleanStatus, bedType, price});
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Room();
    }

}
