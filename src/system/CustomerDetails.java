package system;

import db.Customer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class CustomerDetails extends JFrame {

    JTable table;
    JButton back;

    CustomerDetails() throws SQLException, ClassNotFoundException {
        setTitle("Guest Details");
        setLayout(null);
      setLocation(350, 150);
        setSize(900, 600);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        String[] columnNames = {"ID", "Id_no", "Name", "Gender", "Country", "Room", "CI Time", "Deposit"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model);
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        table.setRowHeight(25); // Set row height for better visibility

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 860, 0); // Initially height is 0
        panel.add(scrollPane);

        int rowCount = loadCustomerData(model);
        int tableHeight = rowCount * table.getRowHeight(); // Dynamically adjust height
        scrollPane.setPreferredSize(new Dimension(860, Math.min(tableHeight, 400))); // Max height limit
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

    private int loadCustomerData(DefaultTableModel model) throws ClassNotFoundException {
        int rowCount = 0;
        try {
            Customer c = new Customer();
            try (ResultSet rs = c.getCustomer()) { // Using try-with-resources
                while (rs.next()) {
                    String id = rs.getString("id_type");
                    String idno = rs.getString("id_number");
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    String country = rs.getString("country");
                    int  room = rs.getInt("allocated_room_number");
                    String time = rs.getString("checked_in");
                    double deposit = rs.getDouble("deposit");

                    model.addRow(new Object[]{id, idno, name, gender, country, room, time, deposit});
                    rowCount++;
                }
            }
            c.closeResources(); // Ensure DB connection is closed
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rowCount;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new CustomerDetails();
    }
}
