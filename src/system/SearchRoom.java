package system;

import db.dbConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchRoom extends JFrame {
    JCheckBox checkBox;
    Choice choice;
    JTable table;
    JButton search, back;
    DefaultTableModel model;

    SearchRoom() {
        // Main panel setup
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 45, 48));
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        add(panel);

        // Heading
        JLabel searchForRoom = new JLabel("Search For Room");
        searchForRoom.setBounds(250, 11, 200, 31);
        searchForRoom.setForeground(Color.WHITE);
        searchForRoom.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(searchForRoom);

        // Labels
        JLabel rbt = new JLabel("Room Bed Type:");
        rbt.setBounds(50, 73, 120, 20);
        rbt.setForeground(Color.WHITE);
        rbt.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(rbt);

        JLabel rn = new JLabel("Room Number");
        rn.setBounds(23, 162, 150, 20);
        rn.setForeground(Color.WHITE);
        rn.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(rn);

        JLabel available = new JLabel("Availability");
        available.setBounds(175, 162, 150, 20);
        available.setForeground(Color.WHITE);
        available.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(available);

        JLabel price = new JLabel("Price");
        price.setBounds(458, 162, 150, 20);
        price.setForeground(Color.WHITE);
        price.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(price);

        JLabel BT = new JLabel("Bed Type");
        BT.setBounds(580, 162, 150, 20);
        BT.setForeground(Color.WHITE);
        BT.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(BT);

        JLabel SS = new JLabel("Clean Status");
        SS.setBounds(306, 162, 150, 20);
        SS.setForeground(Color.WHITE);
        SS.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(SS);

        // CheckBox
        checkBox = new JCheckBox("Only Display Available");
        checkBox.setBounds(400, 69, 205, 23);
        checkBox.setForeground(Color.WHITE);
        checkBox.setBackground(new Color(3, 45, 48));
        panel.add(checkBox);

        // Choice Dropdown
        choice = new Choice();
        choice.add("Single");
        choice.add("Double Bed");
        choice.setBounds(170, 70, 120, 20);
        panel.add(choice);

        // Table setup
        table = new JTable();
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));

        // Table model
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Room No", "Availability", "Clean Status", "Bed Type", "Price"});
        table.setModel(model);

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 187, 700, 150);
        panel.add(scrollPane);

        // Buttons
        search = new JButton("Search");
        search.setBounds(200, 400, 120, 30);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        panel.add(search);

        back = new JButton("Back");
        back.setBounds(380, 400, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);

        // Button Actions
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRooms();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Frame setup
        setUndecorated(true);
        setLayout(null);
        setLocation(500, 200);
        setSize(700, 500);
        setVisible(true);
    }

    // Method to search rooms
    private void searchRooms() {
        model.setRowCount(0); // Clear table before new search
        String query;
        boolean onlyAvailable = checkBox.isSelected();
        
        if (onlyAvailable) {
            query = "SELECT * FROM room WHERE availability = 'Available' AND bedtype = ?";
        } else {
            query = "SELECT * FROM room WHERE bedtype = ?";
        }

        try (Connection con = dbConnection.getConnections()){
            
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, choice.getSelectedItem());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("roomno"),
                        rs.getString("availability"),
                        rs.getString("cleanstatus"),
                        rs.getString("bedtype"),
                        rs.getString("price")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
