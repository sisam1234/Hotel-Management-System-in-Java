
import javax.swing.*;


public class SplashScreen  extends JFrame{
    SplashScreen(){
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("img/Dashboard.gif"));
        JLabel  lbl = new JLabel(img);
        lbl.setBounds(0,0,858,680);
        add(lbl);
        setLayout(null);
        setLocation(300,80);
        setSize(858,680);
        setVisible(true);
        Timer timer = new Timer(5000, e -> {
             dispose();
            new Dashboard().setVisible(true);
            
        });
        timer.setRepeats(false); 
        timer.start();
    }
    public static void main(String[] args){
        SplashScreen sp = new SplashScreen();
    }
}
