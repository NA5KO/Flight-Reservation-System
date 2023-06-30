package GUI;

import java.awt.BorderLayout;


import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AboutUs extends JPanel {

    private JLabel l1, l2, l3,l4, l5;
    private JPanel p1,p2;
    Image backgroundImage;

   
    public AboutUs() {
        setLayout(new BorderLayout());
        p1 = new JPanel();
        p2 = new JPanel();


        p1.setLayout(new GridLayout(0,1,0,1));
        l1 = new JLabel("Project Description:");
        l1.setFont(new Font("Serif", Font.BOLD, 30));
        l1.setForeground(Color.BLACK);
        l2 = new JLabel("Nasko Flights is a flight reservation system that allows passengers register, login to search for destinations and book flights.");
        l2.setFont(new Font("Serif", Font.PLAIN, 20));
        l2.setForeground(Color.BLACK);
        l3 = new JLabel("The system is implemented using Java GUI and PostgreSQL database.");
        l3.setFont(new Font("Serif", Font.PLAIN, 20));
        l3.setForeground(Color.BLACK);


        l1.setAlignmentX(Component.LEFT_ALIGNMENT);
        l2.setAlignmentX(Component.LEFT_ALIGNMENT);
        l3.setAlignmentX(Component.LEFT_ALIGNMENT);


        l4 = new JLabel("About the Developers:");
        l4.setFont(new Font("Serif", Font.BOLD, 30));
        l4.setForeground(Color.BLACK);
        l5 = new JLabel("Nasko Flights is developed by Amine Yahya a computer netowrking student at Insat.");
        l5.setFont(new Font("Serif", Font.PLAIN, 20));
        l5.setForeground(Color.BLACK);

        l4.setAlignmentX(Component.LEFT_ALIGNMENT);
        l5.setAlignmentX(Component.LEFT_ALIGNMENT);


        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
    
        //p2
        p2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };

        try {
            backgroundImage = ImageIO.read(new File("images/DB EDR.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, p2);
        splitPane.setResizeWeight(0);
        splitPane.setDividerSize(0);

        add(splitPane, BorderLayout.CENTER);
        

    }


}
