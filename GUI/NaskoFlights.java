package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NaskoFlights extends JFrame implements ActionListener {
    private JMenuBar jmb = new JMenuBar();
    private JMenu Home = new JMenu("Home");
    private JMenu infoJMenu = new JMenu("Info");
    private JMenuItem AboutUs = new JMenuItem("About Us");
    private final JMenuItem back = new JMenuItem("Welcome");
    private JMenuItem Signup = new JMenuItem("Sign Up");
    private JMenuItem Login = new JMenuItem("Login");
    private JMenuItem Search = new JMenuItem("Search");
    private JMenuItem Quit = new JMenuItem("Quit");
    private JMenuItem History = new JMenuItem("History");

    public NaskoFlights() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("NaskoFlights ");
        this.setLocationRelativeTo(null);
        this.setSize(1200, 1200);

        Home.add(back);
        Home.addSeparator();
        Home.add(Signup);
        Home.add(Login);
        Home.addSeparator();
        Home.add(Search);
        Home.add(History);
        Home.addSeparator();
        Home.add(Quit);
        infoJMenu.add(AboutUs);
        jmb.add(Home);
        jmb.add(infoJMenu);
        Dimension menuBarSize = new Dimension(600, 50);
        jmb.setPreferredSize(menuBarSize);
        this.setJMenuBar(jmb);
        this.setVisible(true);

        Login.addActionListener(this);
        Signup.addActionListener(this);
        Quit.addActionListener(this);
        Search.addActionListener(this);
        back.addActionListener(this);
        AboutUs.addActionListener(this);
        History.addActionListener(this);

        JPanel panel = new JPanel();
        BackgroundPanel bp = new BackgroundPanel();
        panel.setLayout(new BorderLayout());

        JButton here = new JButton("GET STARTED");
        JLabel label1 = new JLabel("Book your Tickets & Fly Safely With NaskoFlights!");
        label1.setHorizontalAlignment(JLabel.CENTER);
        here.setPreferredSize(new Dimension(100, 50));

        here.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Signup signup = new Signup();
                setContentPane(signup);
            }
        });

        label1.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        label1.setForeground(Color.DARK_GRAY);
        here.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        here.setForeground(Color.DARK_GRAY);
        here.setBackground(Color.WHITE);
        here.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel.add(label1, BorderLayout.NORTH);
        panel.add(bp, BorderLayout.CENTER);
        panel.add(here, BorderLayout.SOUTH);
        this.setContentPane(panel);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }
 public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == Signup) {
        Signup page = new Signup();
        this.setContentPane(page);
    } else if (source == Login) {
        Login page2 = new Login();
        this.setContentPane(page2);
    } else if (source == Quit) {
        System.exit(0);
    } else if (source == Search) {
        Search searchPage = new Search();
        this.setContentPane(searchPage);
    } else if (source == back) {
        WelcomePage welcomePage = new WelcomePage();
        setContentPane(welcomePage);
    }else if (source == AboutUs) {
        AboutUs aboutUs = new AboutUs();
        setContentPane(aboutUs);
    }else if (source == History) {
        History history = new History();
        setContentPane(history);
    }


    this.revalidate();
    this.repaint();
}
}

