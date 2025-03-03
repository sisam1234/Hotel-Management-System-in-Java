
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Dashboard extends JFrame implements ActionListener{
    JButton recep, admin;
    Dashboard(){
        super("Hotel Management System");
        recep = new JButton("Reception");
        recep.setBounds(425,510,140,30);
        recep.setFont(new Font("Tahoma",Font.BOLD,15));
        recep.setBackground(new Color(255,98,0));
        recep.setForeground(Color.WHITE);
        recep.addActionListener(this);
        add(recep);
         
        admin = new JButton("Admin");
        admin.setBounds(880,510,140,30);
        admin.setFont(new Font("Tahoma",Font.BOLD,15));
        admin.setBackground(new Color(255,98,0));
        admin.setForeground(Color.WHITE);
        admin.addActionListener(this);
        add(admin);
        
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("img/admin.png"));
        Image rimg = img.getImage().getScaledInstance(200,195,Image.SCALE_DEFAULT);
        ImageIcon reimg = new ImageIcon(rimg);
        JLabel lbl= new JLabel(reimg);
        lbl.setBounds(850,300,200,195);
        add(lbl);
        
        ImageIcon img2 = new ImageIcon(ClassLoader.getSystemResource("img/reception.png"));
        Image aimg = img2.getImage().getScaledInstance(200,195,Image.SCALE_DEFAULT);
        ImageIcon adimg = new ImageIcon(aimg);
        JLabel albl= new JLabel(adimg);
        albl.setBounds(400,300,200,195);
        add(albl);
        
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("img/Dashboard.gif"));
        Image i1 = imageIcon.getImage().getScaledInstance(1950,1090, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(0,0,1950,1090);
        add(label);
        setLayout(null);
        setSize(1950,1090);
        setVisible(true);
        
        
        
    }
    @Override 
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==recep){
            setVisible(false);
        }
        else{
            setVisible(false);
        }
    }
    public static void main(String[] args){
       Dashboard d = new Dashboard();
    }
    
}
