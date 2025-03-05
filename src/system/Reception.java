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
                new RoomDetails();
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
allemp.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                new EmployeeDetails();
            } catch (SQLException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });

        JButton cus  = new JButton("Customer Info");
        cus.setBounds(30,180,200,30);
        cus.setBackground(Color.BLACK);
        cus.setForeground(Color.WHITE);
        p2.add(cus);
        cus.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                new CustomerDetails();
            } catch (SQLException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        JButton out  = new JButton("CheckOut");
        out.setBounds(30,230,200,30);
        out.setBackground(Color.BLACK);
        out.setForeground(Color.WHITE);
        p2.add(out);
        out.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
           new CheckOut();
            
        
        }
        });
        
        JButton updatecheckin  = new JButton("Update Check-in Details");
        updatecheckin.setBounds(30,280,200,30);
        updatecheckin.setBackground(Color.BLACK);
        updatecheckin.setForeground(Color.WHITE);
        p2.add(updatecheckin);
        updatecheckin.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                new UpdateCheckIn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        
        JButton updater  = new JButton("Update Room");
        updater.setBounds(30,330,200,30);
        updater.setBackground(Color.BLACK);
        updater.setForeground(Color.WHITE);
        p2.add(updater);
        updater.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        new UpdateRoom(); // Make sure this constructor only throws SQLException if applicable
    }
});

        
        JButton searchroom  = new JButton("Search Room");
        searchroom.setBounds(30,380,200,30);
        searchroom.setBackground(Color.BLACK);
        searchroom.setForeground(Color.WHITE);
        p2.add(searchroom);
 searchroom.addActionListener(new ActionListener() {
        @Override
    public void actionPerformed(ActionEvent e) {
        new SearchRoom(); // Make sure this constructor only throws SQLException if applicable
    }
});

        
        
        JButton back  = new JButton("Back");
        back.setBounds(30,430,200,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        p2.add(back);
         back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
            }
        });

        
         getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1950,1090);
        setVisible(true);
        
    }
    public static void main(String[] args){
        new Reception();
    }

    
    
}
