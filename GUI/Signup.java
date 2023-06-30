package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.*;

import DBinterface.ConnectDB;

public class Signup extends JPanel implements ActionListener {
        private ConnectDB db;


    private JLabel l1,fn,ln,na,age,phone,mail,pid,pass,l2,bankaccount;
    JTextField fnf,lnf,pidf,naf,agef,phonef,mailf,bankf;
    JButton b1,b2;
    JPasswordField passf;

    public Signup(){
        l1 = new JLabel("Start Your Trip By Registering Here");
        l1.setForeground(Color.DARK_GRAY);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Welcome TO Nasko Flights");
        l2.setForeground(Color.DARK_GRAY);
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        pid= new JLabel("Passport ID :");
        fn= new JLabel("First Name :");
        ln= new JLabel("Last Name :");
        na= new JLabel("Nationality :");
        age= new JLabel("Age :");
        phone= new JLabel("Phone Number :");
        mail= new JLabel("Email :");
        pass = new JLabel("Password :");
        bankaccount = new JLabel("Bank Balance :");
        b1 = new JButton("SUBMIT" );
        b2 = new JButton("CLEAR" );
        fnf = new JTextField();  
        lnf = new JTextField();
        pidf= new JTextField();  
        naf = new JTextField();  
        agef = new JTextField();  
        phonef = new JTextField();  
        mailf = new JTextField();  
        passf = new JPasswordField();  
        bankf = new JTextField();
        b1.addActionListener(this);  
        b2.addActionListener(this);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        l2.setHorizontalAlignment(JLabel.CENTER);
        l2.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        // Make all the JLabels bigger in font and centered before the textfields
for (JLabel label: Arrays.asList(pid, fn, ln, na, age, phone, mail, pass,bankaccount)) {
    label.setFont(new Font("Serif", Font.BOLD, 20));
    label.setHorizontalAlignment(JLabel.CENTER);
        }
        // Adjust the textfields
for (JTextField textField: Arrays.asList(fnf, lnf, pidf, naf, agef, phonef, mailf,  passf,bankf)) {
    textField.setColumns(20);
    textField.setFont(new Font("Serif", Font.BOLD, 15));
}
b1.setPreferredSize(new Dimension(100, 50));
b2.setPreferredSize(new Dimension(100, 50));

        setLayout(new GridLayout(0, 2, 10, 10));
        this.add(l2);
        this.add(l1);  
        this.add(fn);  
        this.add(fnf);  
        this.add(ln);  
        this.add(lnf);
        this.add(na);
        this.add(naf);
        this.add(pid);
        this.add(pidf);
        this.add(pass);  
        this.add(passf);
        this.add(age);  
        this.add(agef);
        this.add(mail); 
        this.add(mailf); 
        this.add(phone);  
        this.add(phonef);  
        this.add(bankaccount);
        this.add(bankf);
        this.add(b1);  
        this.add(b2); 
        this.revalidate();
        
    }
        public void actionPerformed(ActionEvent e)   
    {  
        if (e.getSource() == b1)  
         {   
            String firstname = fnf.getText();  
            String lastname = lnf.getText();  
            Integer id = Integer.parseInt(pidf.getText());
            if(id < 0 || id instanceof Integer == false){
                JOptionPane.showMessageDialog(this, "Please enter a valid passport ID");
                return;
            }
            String password = new String(passf.getPassword());
            if(password.length() < 8){
                JOptionPane.showMessageDialog(this, "Please enter a password with at least 8 characters");
                return;
            }
            String nat = naf.getText();  
            int age = Integer.parseInt(agef.getText());  
            int phone = Integer.parseInt(phonef.getText());
            String mail = mailf.getText();
        Float bank = Float.parseFloat(bankf.getText());

        try // Create a new ConnectDB object
        {Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FRS","postgres","postgres");
        PreparedStatement Pstatement=connection.prepareStatement("insert into Passenger values(?,?,?,?,?,?,?,?,?)");
        Pstatement.setInt(1,id);
        Pstatement.setString(2,firstname);
        Pstatement.setString(3,lastname);
        Pstatement.setString(4,password);
        Pstatement.setString(5,nat);    
        Pstatement.setInt(6,age);
        Pstatement.setInt(7,phone);
        Pstatement.setString(8,mail);
        Pstatement.setFloat(9,bank);
        Pstatement.executeUpdate();
        Pstatement.close();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        // Clear the text fields
        fnf.setText("");
        lnf.setText("");
        pidf.setText("");
        naf.setText("");
        agef.setText("");
        phonef.setText("");
        mailf.setText("");
        passf.setText("");
        bankf.setText("");

        String welcomeMessage = "Welcome to NaskFlights, dear " + lastname + " " + firstname;
        JOptionPane.showMessageDialog(this, welcomeMessage);
    }
        else  
          {  
            fnf.setText("");  
            passf.setText("");
            lnf.setText("");  
            pidf.setText("");  
            naf.setText("");  
            agef.setText("");  
            phonef.setText("");
            mailf.setText("");
            bankf.setText(""); 
          }  
            
}

    
    
}
