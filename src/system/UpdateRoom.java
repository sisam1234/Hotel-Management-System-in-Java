package system;
import db.Customer;
import db.dbConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;


public class UpdateRoom extends JFrame {
    UpdateRoom(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(3,45,48));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("img/update.png"));
        Image image = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);

        JLabel label1  = new JLabel("Update Room Status");
        label1.setBounds(124,11,222,25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2  = new JLabel("ID :");
        label2.setBounds(25,88,46,14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice c = new Choice();
        c.setBounds(248,85,140,20);
        panel.add(c);
         try{
            Customer cus = new Customer();
            ResultSet rs = cus.getCustomer();
            while(rs.next()){
            c.add(rs.getString("id_number"));
        }
        }catch(Exception e){}

       

        JLabel label3  = new JLabel("Room Number :");
        label3.setBounds(25,129,107,14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textField3 = new JTextField();
        textField3.setBounds(248,129,140,20);
        panel.add(textField3);

        JLabel label4  = new JLabel("Availability :");
        label4.setBounds(25,174,97,14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField textField4 = new JTextField();
        textField4.setBounds(248,174,140,20);
        panel.add(textField4);

        JLabel label5  = new JLabel("Clean Status :");
        label5.setBounds(25,216,97,14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField textField5 = new JTextField();
        textField5.setBounds(248,216,140,20);
        panel.add(textField5);



        JButton update = new JButton("Update");
        update.setBounds(120,315,89,23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = dbConnection.getConnections()){
                   Statement st  = con.createStatement();
                    String status = textField5.getText();
                    st.executeUpdate("update room set cleanstatus = '"+status+"' where roomno = " + textField3.getText());
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    setVisible(false);

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        



        JButton back = new JButton("Back");
        back.setBounds(180,355,89,23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
         back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(60,355,89,23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
         check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = c.getSelectedItem();
                String q = "select * from guests where id_number = '"+id+"'";
                try(Connection con = dbConnection.getConnections()){
                    Statement st  = con.createStatement();
                    ResultSet resultSet = st.executeQuery(q);

                    while (resultSet.next()){
                        textField3.setText(resultSet.getString("allocated_room_number"));
                    }

                    ResultSet resultSet1 = st.executeQuery("select * from room where roomno =  '"+textField3.getText()+"'");

                    while (resultSet1.next()){
                        textField4.setText(resultSet1.getString("availability"));
                        textField5.setText(resultSet1.getString("cleanstatus"));
                    }
                }catch (Exception E ){
                    E.printStackTrace();
                }
            }
        });
        
        setLayout(null);
         setLocation(400, 150);
        setSize(850, 550);
        setVisible(true);
    }
    public static void main(String[] args) {
        new UpdateRoom();
    }
}