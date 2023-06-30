package GUI;

import javax.swing.*;
import DBinterface.*;

import DBinterface.ConnectDB;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Login extends JPanel implements ActionListener{
    private JLabel notice,Passport_id,Password;
    private JTextField id_f;
    private JPasswordField passf;
    private JButton loginButton,clearButton;
    private JPanel toppanel,bottomPanel;

    public Login(){
        this.setLayout(new BorderLayout());
        notice = new JLabel("Welcome , In order to make a reservation you need to login first ");
        Passport_id = new JLabel("Passport ID :");
        Password = new JLabel("Password :");
        id_f = new JTextField();
        passf = new JPasswordField();
        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");
        
        toppanel = new JPanel();
        toppanel.add(notice);
        notice.setHorizontalAlignment(JLabel.CENTER);
        notice.setFont(new Font(Font.DIALOG, Font.BOLD, 30));

        //login panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(Passport_id, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(id_f, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(Password, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        for (JLabel label: Arrays.asList(Passport_id,Password)) {
    label.setFont(new Font("Serif", Font.BOLD, 25));
    label.setHorizontalAlignment(JLabel.CENTER);
        }
    for (JTextField textField: Arrays.asList(id_f,passf)) {
    textField.setColumns(30);
    textField.setFont(new Font("Serif", Font.BOLD, 25)); 
}
    // button panel
    bottomPanel = new JPanel();
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
 for (JButton button: Arrays.asList(loginButton,clearButton)) {
    button.setPreferredSize(new Dimension(100, 50));
}
    loginButton.addActionListener(this);
    clearButton.addActionListener(this);    
    bottomPanel.add(loginButton);
    bottomPanel.add(Box.createHorizontalStrut(10));
    bottomPanel.add(clearButton);
    loginPanel.add(bottomPanel, gbc);
    
    
    this.add(toppanel,BorderLayout.NORTH);
    this.add(loginPanel,BorderLayout.CENTER);
    

    


    }
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton) {
        int id = Integer.parseInt(id_f.getText());
        char[] passwordChars = passf.getPassword();
        String password = new String(passwordChars);

        try {
            // Create the ConnectDB instance outside the actionPerformed method
            ConnectDB db = new ConnectDB("jdbc:postgresql://localhost:5432/FRS", "postgres", "postgres");


            String query = "SELECT * FROM passenger WHERE passport_id = ? AND pass = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                this.setVisible(false);
                this.setVisible(true);
                new Search();
                JOptionPane.showMessageDialog(this, "Login Successful");
                passf.setText("");
                id_f.setText("");
                
            } else {
                JOptionPane.showMessageDialog(this, "Wrong ID or Password");
                this.setVisible(false);
                this.setVisible(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else if (e.getSource() == clearButton) {
        passf.setText("");
        id_f.setText("");
    }
}
}
