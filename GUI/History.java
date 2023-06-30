package GUI;

import java.awt.BorderLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import DBinterface.ConnectDB;

public class History extends JPanel implements ActionListener  {
    private JLabel label1 = new JLabel("You can check your booking history here !");
    private JLabel label2 = new JLabel("Please enter your passport ID :");
    private JTextField text = new JTextField();
    private JButton check = new JButton("Check");
    private JButton clear = new JButton("clear");
    private JLabel  label3 = new JLabel("Please enter your password :");
    private JPasswordField text2 = new JPasswordField();
    private ConnectDB db ;
    private JPanel panel = new JPanel();



    public History() {
        this.setLayout(new BorderLayout());
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        label1.setForeground(Color.DARK_GRAY);
        this.add(label1, BorderLayout.NORTH);
        check.addActionListener(this);
        clear.addActionListener(this);
        text.setColumns(20);
        text2.setColumns(20);
        check.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        check.setForeground(Color.DARK_GRAY);
        check.setBackground(Color.WHITE);
        check.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        clear.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        clear.setForeground(Color.DARK_GRAY);
        clear.setBackground(Color.WHITE);
        clear.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        label2.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        label2.setForeground(Color.DARK_GRAY);
        label3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        label3.setForeground(Color.DARK_GRAY);
        text.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        text.setForeground(Color.DARK_GRAY);
        text2.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        text2.setForeground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label2, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(text, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(label3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(text2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(check, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(clear, gbc);

        this.add(panel, BorderLayout.CENTER);
        


        


    }

    @Override
    public void actionPerformed(ActionEvent e) {
   if (e.getSource() == check) {
        int id = Integer.parseInt(text.getText());
        String pass = text2.getText();
        // check if the id and password are correct
        db = new ConnectDB("jdbc:postgresql://localhost:5432/FRS", "postgres", "postgres");
        try {
            if (db.checkPassenger(id, pass) != null) {
                // if correct then show the history
                JPanel panel2 = db.checkPassenger(id, pass);
                panel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel,panel2);
                splitPane.setEnabled(false); 
                this.add(splitPane, BorderLayout.CENTER);
                this.revalidate();
                this.repaint();

            } else {
                JOptionPane.showMessageDialog(null, "Wrong ID or password");
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    if (e.getSource() == clear) {
        text.setText("");
        text2.setText("");

    }
}
}

