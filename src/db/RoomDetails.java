package db;

import java.sql.*;

public class RoomDetails {

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public RoomDetails() throws ClassNotFoundException, SQLException {
        // Establish connection only once when the object is created
        con = dbConnection.getConnections();
    }

    public ResultSet getRoom() throws SQLException {
        String sql = "SELECT * FROM room";
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
