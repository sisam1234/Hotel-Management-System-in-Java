/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
public class Employee {

    private Connection con;
    
    private Statement stmt;
    private ResultSet rs;

    public Employee() throws ClassNotFoundException, SQLException {
        // Establish connection only once when the object is created
        con = dbConnection.getConnections();
    }

    public ResultSet getEmployee() throws SQLException {
        String sql = "SELECT * FROM employee";
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery(sql);
        return rs;
    }
 public void EmployeeData(String name, int age, String gender, String job, double salary, String phone, String gmail ) throws SQLException, ClassNotFoundException{
        try{
            String sql = "insert into employee(name,age, gender,job,salary,phone,gmail) values(?,?,?,?,?,?,?)";
            PreparedStatement ps  = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2,age);
            ps.setString(3, gender);
            ps.setString(4, job);
            ps.setDouble(5, salary);
            ps.setString(6,phone );
            ps.setString(7,gmail);
           
            
           ps.executeUpdate();
           con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
 }
    public void closeResources() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
}
