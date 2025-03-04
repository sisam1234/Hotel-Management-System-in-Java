package system;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Reception extends JFrame {
    Reception(){
        JPanel p1  = new JPanel();
        p1.setBounds(280,5,1238,820);
        p1.setLayout(null);
        p1.setBackground(new Color(3,45,48));
        add(p1);
        
        JPanel p2  = new JPanel();
        p2.setBounds(5,5,270,820);
        p2.setLayout(null);
        p2.setBackground(new Color(3,45,48));
        add(p2);
        
        
        JButton ncf  = new JButton("New CustomerForm");
        ncf.setBounds(30,30,200,30);
        ncf.setBackground(Color.BLACK);
        ncf.setForeground(Color.WHITE);
        p2.add(ncf);
        ncf.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new NewCustomerForm();
        }
        });
        
        JButton room  = new JButton("Room");
       room.setBounds(30,80,200,30);
        room.setBackground(Color.BLACK);
        room.setForeground(Color.WHITE);
        p2.add(room);
        room.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                new Room();
            } catch (SQLException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        
        
        JButton allemp  = new JButton("All Employee Info");
        allemp.setBounds(30,130,200,30);
        allemp.setBackground(Color.BLACK);
        allemp.setForeground(Color.WHITE);
        p2.add(allemp);
        
        JButton cus  = new JButton("Customer Info");
        cus.setBounds(30,180,200,30);
        cus.setBackground(Color.BLACK);
        cus.setForeground(Color.WHITE);
        p2.add(cus);
        
        JButton checkout  = new JButton("CheckOut");
        checkout.setBounds(30,230,200,30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        p2.add(checkout);
        
        JButton updatecheckin  = new JButton("Update Check-in Details");
        updatecheckin.setBounds(30,280,200,30);
        updatecheckin.setBackground(Color.BLACK);
        updatecheckin.setForeground(Color.WHITE);
        p2.add(updatecheckin);
        
        JButton updateroom  = new JButton("Update Room");
        updateroom.setBounds(30,330,200,30);
        updateroom.setBackground(Color.BLACK);
        updateroom.setForeground(Color.WHITE);
        p2.add(updateroom);
        
        JButton searchroom  = new JButton("Search Room");
        searchroom.setBounds(30,380,200,30);
        searchroom.setBackground(Color.BLACK);
        searchroom.setForeground(Color.WHITE);
        p2.add(searchroom);
        
        
        JButton back  = new JButton("Back");
        back.setBounds(30,430,200,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        p2.add(back);
        
         getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1950,1090);
        setVisible(true);
        
    }
    public static void main(String[] args){
        new Reception();
    }
    
}
