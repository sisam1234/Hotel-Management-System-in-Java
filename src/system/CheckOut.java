package system;

import db.Customer;
import db.dbConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.ResultSet;

import java.util.Date;

public class CheckOut extends JFrame {
    CheckOut(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(3,45,48));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(100,20,100,30);
        label.setFont(new Font("Tahoma",Font.PLAIN,20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel UserId = new JLabel("Customer Id");
        UserId.setBounds(30,80,150,30);
        UserId.setFont(new Font("Tahoma",Font.BOLD,14));
        UserId.setForeground(Color.WHITE);
        panel.add(UserId);

        Choice c = new Choice();
        c.setBounds(200,80,150,25);
        panel.add(c);
try{
            Customer cus = new Customer();
            ResultSet rs = cus.getCustomer();
            while(rs.next()){
            c.add(rs.getString("id_number"));
        }
        }catch(Exception e){}
        JLabel roomNum = new JLabel("Room Number");
        roomNum.setBounds(30,130,150,30);
        roomNum.setFont(new Font("Tahoma",Font.BOLD,14));
        roomNum.setForeground(Color.WHITE);
        panel.add(roomNum);

        JLabel labelRoomnumber = new JLabel();
        labelRoomnumber.setBounds(200,130,150,30);
        labelRoomnumber.setFont(new Font("Tahoma",Font.BOLD,14));
        labelRoomnumber.setForeground(Color.WHITE);
        panel.add(labelRoomnumber);

        JLabel checkintime = new JLabel("Check-In Time");
        checkintime.setBounds(30,180,150,30);
        checkintime.setFont(new Font("Tahoma",Font.BOLD,14));
        checkintime.setForeground(Color.WHITE);
        panel.add(checkintime);

        JLabel labelcheckintime = new JLabel();
        labelcheckintime.setBounds(200,180,200,30);
        labelcheckintime.setFont(new Font("Tahoma",Font.BOLD,14));
        labelcheckintime.setForeground(Color.WHITE);
        panel.add(labelcheckintime);

        JLabel checkouttime = new JLabel("Check-Out Time");
        checkouttime.setBounds(30,230,150,30);
        checkouttime.setFont(new Font("Tahoma",Font.BOLD,14));
        checkouttime.setForeground(Color.WHITE);
        panel.add(checkouttime);

        Date date = new Date();

        JLabel labelcheckouttime = new JLabel(""+date);
        labelcheckouttime.setBounds(200,230,200,30);
        labelcheckouttime.setFont(new Font("Tahoma",Font.BOLD,14));
        labelcheckouttime.setForeground(Color.WHITE);
        panel.add(labelcheckouttime);




        JButton checkOut = new JButton(" Check-Out");
        checkOut.setBounds(30,300,120,30);
        checkOut.setForeground(Color.WHITE);
        checkOut.setBackground(Color.BLACK);
        panel.add(checkOut);
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection con = dbConnection.getConnections()) {
                    Statement st = con.createStatement();
                    st.executeUpdate("delete from guests where id_number = '"+c.getSelectedItem()+"'");
                    st.executeUpdate("update room set availability = 'Available' where roomno = '"+Integer.parseInt(labelRoomnumber.getText())+"'");
                    JOptionPane.showMessageDialog(null, "Done");
                    setVisible(false);
                }catch (Exception E ){
                    E.printStackTrace();
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(300,300,120,30);
        check.setForeground(Color.WHITE);
        check.setBackground(Color.BLACK);
        panel.add(check);
         check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try(Connection con = dbConnection.getConnections()){
                    Statement st = con.createStatement();
                    ResultSet resultSet = st.executeQuery("select * from guests where id_number = '"+c.getSelectedItem()+"'");
                    while (resultSet.next()){
                        labelRoomnumber.setText(resultSet.getString("allocated_room_number"));
                        labelcheckintime.setText(resultSet.getString("checked_in"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(170,300,120,30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });



        
        setLayout(null);
        setSize(800,400);
        setLocation(500,210);
        setVisible(true);
    }
    public static void main(String[] args) {
        new CheckOut();
    }
}