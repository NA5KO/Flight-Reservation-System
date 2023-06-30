package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class WelcomePage extends JPanel implements ActionListener  {

    private NaskoFlights naskoFlights;

    public WelcomePage() {
        BackgroundPanel bp = new BackgroundPanel();
        this.setLayout(new BorderLayout());
        
        JButton here = new JButton("GET STARTED");
        JLabel label1 = new JLabel("Book your Tickets & Fly Safely With NaskoFlights!");
        label1.setHorizontalAlignment(JLabel.CENTER);
        here.setPreferredSize(new Dimension(100, 50));

        here.addActionListener(this);

        label1.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        label1.setForeground(Color.DARK_GRAY);
        here.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        here.setForeground(Color.DARK_GRAY);
        here.setBackground(Color.WHITE);
        here.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        this.add(label1, BorderLayout.NORTH);
        this.add(bp, BorderLayout.CENTER);
        this.add(here, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
        Signup signup = new Signup();
        naskoFlights.setContentPane(signup);
        
    };

  
}

