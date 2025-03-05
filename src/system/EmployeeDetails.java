package system;

import db.Employee;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeDetails extends JFrame {

    JTable table;
    JButton back;

    EmployeeDetails() throws SQLException, ClassNotFoundException {
        setTitle("Employee Details");
        setLayout(null);
       setLocation(350, 150);
        setSize(900, 600);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        String[] columnNames = {"Name", "Age", "Gender", "Job", "Salary", "Phone", "Gmail"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable(model);
        table.setBackground(new Color(3, 45, 48));
        table.setForeground(Color.WHITE);
        table.setRowHeight(25); // Set row height for better visibility

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 860, 0); // Initially height is 0
        panel.add(scrollPane);

        int rowCount = loadEmployeeData(model);
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

    private int loadEmployeeData(DefaultTableModel model) throws ClassNotFoundException {
        int rowCount = 0;
        try {
            Employee emp = new Employee();
            try (ResultSet rs = emp.getEmployee()) { // Using try-with-resources
                while (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String job = rs.getString("job");
                    double salary = rs.getDouble("salary");
                    String phone = rs.getString("phone");
                    String gmail = rs.getString("gmail");

                    model.addRow(new Object[]{name, age, gender, job, salary, phone, gmail});
                    rowCount++;
                }
            }
            emp.closeResources(); // Ensure DB connection is closed
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rowCount;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new EmployeeDetails();
    }
}
