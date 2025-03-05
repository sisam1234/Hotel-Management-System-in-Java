package system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame implements ActionListener {

    JButton add_Employee, add_Room,  logout, back;
    Admin(){

        add_Employee = new JButton( "ADD EMPLOYEE");
        add_Employee.setBounds(250,230,200,30);
        add_Employee.setBackground(Color.WHITE);
        add_Employee.setForeground(Color.BLACK);
        add_Employee.setFont(new Font("Tahoma", Font.BOLD, 15));
        add_Employee.addActionListener(this);
        add(add_Employee);

        add_Room = new JButton( "ADD ROOM");
        add_Room.setBounds(250,380,200,30);
        add_Room.setBackground(Color.WHITE);
        add_Room.setForeground(Color.BLACK);
        add_Room.setFont(new Font("Tahoma", Font.BOLD, 15));
        add_Room.addActionListener(this);
        add(add_Room);

        

        logout = new JButton( "Logout");
        logout.setBounds(250,450,200,30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("Tahoma", Font.BOLD, 15));
        logout.addActionListener(this);
        add(logout);

        back = new JButton( "Back");
        back.setBounds(500,450,200,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.addActionListener(this);
        add(back);


        ImageIcon l1 = new ImageIcon(ClassLoader.getSystemResource("img/employees.png"));
        Image l11 = l1.getImage().getScaledInstance(120,120, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(l11);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(70,180,120,120);
        add(label);

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("img/room.png"));
        Image image = imageIcon1.getImage().getScaledInstance(120,120, Image.SCALE_DEFAULT);
        ImageIcon imageIcon11 = new ImageIcon(image);
        JLabel label1 = new JLabel(imageIcon11);
        label1.setBounds(70,340,120,120);
        add(label1);

       

       


        getContentPane().setBackground(new Color(3,45,48));
        setLayout(null);
        setSize(1950,1090);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add_Employee){
            new AddEmployee();
        } else if (e.getSource() == add_Room) {
            new AddRoom();
  
        } else if (e.getSource() == logout){
            System.exit(102);
        } else if (e.getSource() == back) {
            new Dashboard();
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new Admin();
    }

   
}