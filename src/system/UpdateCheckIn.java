package system;
import db.Customer;
import db.dbConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCheckIn extends JFrame {
    UpdateCheckIn() throws ClassNotFoundException, SQLException{

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

        JLabel label1  = new JLabel("Check-In Details");
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

        JTextField roomno = new JTextField();
        roomno.setBounds(248,129,140,20);
       panel.add(roomno);

        JLabel label4  = new JLabel("Name :");
        label4.setBounds(25,174,97,14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField name = new JTextField();
        name.setBounds(248,174,140,20);
        panel.add(name);

        JLabel label5  = new JLabel("Checked-in :");
        label5.setBounds(25,216,97,14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField time = new JTextField();
        time.setBounds(248,216,140,20);
        panel.add(time);

        JLabel label6  = new JLabel("Amount Paid (Rs) :");
        label6.setBounds(25,261,150,14);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        JTextField amtpaid = new JTextField();
        amtpaid.setBounds(248,261,140,20);
        panel.add(amtpaid);

        JLabel label7  = new JLabel("Pending Amount (Rs) :");
        label7.setBounds(25,302,150,14);
        label7.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label7.setForeground(Color.WHITE);
        panel.add(label7);

        JTextField pendingamt = new JTextField();
        pendingamt.setBounds(248,302,140,20);
        panel.add(pendingamt);


        JButton update = new JButton("Update");
        update.setBounds(56,378,89,23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
 panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        try (Connection con = dbConnection.getConnections()){
                 Statement st = con.createStatement();
                 String q = c.getSelectedItem();
                 String room = roomno.getText();
                 String cname = name.getText();
                 String check =time.getText();
                  double pending = Double.parseDouble(pendingamt.getText());
                 st.executeUpdate("update guests  set allocated_room_number = '"+room+"', name = '"+cname+"', checked_in= '"+check+"', deposit = '"+pending+"' where id_number = '"+q+"'");
                 JOptionPane.showMessageDialog(null, "Updated Successfully");
                 setVisible(false);

             }catch (Exception E){
                 E.printStackTrace();
             }
            }
});

        JButton back = new JButton("Back");
        back.setBounds(168,378,89,23);
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
        check.setBounds(281,378,89,23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String id = c.getSelectedItem();
                try(Connection con = dbConnection.getConnections()){
                    String sql = "select * from guests where id_number = '"+id+"'";
                    Statement st = con.createStatement();
                    ResultSet rs= st.executeQuery(sql);
                    while(rs.next()){
                        roomno.setText(rs.getString("allocated_room_number"));
                         name.setText(rs.getString("name"));
                        time.setText(rs.getString("checked_in"));
                        amtpaid.setText(String.valueOf(rs.getDouble("deposit")));

                    }
                    ResultSet rs2 = st.executeQuery("select * from room where roomno='"+roomno.getText()+"'");
                    while(rs2.next()){
                       double price = rs2.getDouble("price");
double pendingamts = price - Double.parseDouble(amtpaid.getText());
pendingamt.setText(String.valueOf(pendingamts));  // Correct way to set the value

                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateCheckIn.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UpdateCheckIn.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        
        });

        setLayout(null);
        setLocation(400, 150);
        setSize(850, 550);
        setVisible(true);
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new UpdateCheckIn();
    }
}
