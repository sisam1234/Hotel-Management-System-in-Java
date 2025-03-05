package db;

import java.sql.*;

public class Room {

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public Room() throws ClassNotFoundException, SQLException {
        // Establish connection only once when the object is created
        con = dbConnection.getConnections();
    }

    public ResultSet getRoom() throws SQLException {
        String sql = "SELECT * FROM room";
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery(sql);
        return rs;
    }
 public void RoomData(int roomno , String availability , String cleanstatus, String bedtype,int price) throws SQLException, ClassNotFoundException{
        try{
            String sql = "insert into room(roomno,availability,cleanstatus,bedtype,country,price) values(?,?,?,?,?)";
            PreparedStatement ps  = con.prepareStatement(sql);
            ps.setInt(1, roomno);
            ps.setString(2,availability);
            ps.setString(3,cleanstatus );
            ps.setString(4,bedtype );
            
            ps.setInt(5,price);
            
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
