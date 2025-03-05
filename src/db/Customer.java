/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
public class Customer {
     private Connection con;
    
    private Statement stmt;
    private ResultSet rs;

    public Customer() throws ClassNotFoundException, SQLException {
        // Establish connection only once when the object is created
        con = dbConnection.getConnections();
    }
    public void CheckInData(String id_type, String id_no, String name, String gender, String country, int allocated_room, String date, double deposit ) throws SQLException, ClassNotFoundException{
        try{
            String sql = "insert into guests(id_type,id_number,name,gender,country,allocated_room_number,checked_in,deposit) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps  = con.prepareStatement(sql);
            ps.setString(1, id_type);
            ps.setString(2,id_no);
            ps.setString(3, name);
            ps.setString(4, gender);
            ps.setString(5, country);
            ps.setInt(6,allocated_room);
            ps.setString(7, date);
            ps.setDouble(8, deposit);
           ps.executeUpdate();
           con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    public ResultSet getCustomer() throws SQLException {
        String sql = "SELECT * FROM guests";
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery(sql);
        return rs;
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
