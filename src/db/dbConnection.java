/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.*;

public class dbConnection {
 public static Connection getConnections() throws SQLException, ClassNotFoundException{
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HMS","root","root");
        System.out.println("successfuly connected");
        return con;
        
    }catch(SQLException e){
        throw new SQLException("Database connection failed: " + e.getMessage(), e);
    }
 }   
}
