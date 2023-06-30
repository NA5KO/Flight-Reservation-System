package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DBinterface.ConnectDB;

public class Search extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    private JLabel welcomelabel, stopoeverJLabel;
    private JTable table;
    private ConnectDB db;
    private JRadioButton yesRadioButton, noRadioButton;
    private JScrollPane scrollPane;
    private JPanel resultPanel;
    private JSplitPane splitPane;
    private int selectedFlightId;
    private int selectedFlightSeats;

    public Search() {
        this.setLayout(new FlowLayout());
        db = new ConnectDB("jdbc:postgresql://localhost:5432/FRS", "postgres", "postgres");

        welcomelabel = new JLabel("Welcome to NaskoFlights");
        welcomelabel.setFont(new Font("Serif", Font.BOLD, 30));
        welcomelabel.setHorizontalAlignment(JLabel.CENTER);

        stopoeverJLabel = new JLabel("Do you want a Stopover:");
        stopoeverJLabel.setFont(new Font("Serif", Font.BOLD, 20));
        stopoeverJLabel.setHorizontalAlignment(JLabel.CENTER);

        yesRadioButton = new JRadioButton("Yes");
        noRadioButton = new JRadioButton("No");
        yesRadioButton.setFont(new Font("Serif", Font.BOLD, 20));
        noRadioButton.setFont(new Font("Serif", Font.BOLD, 20));
        yesRadioButton.setHorizontalAlignment(JLabel.CENTER);
        noRadioButton.setHorizontalAlignment(JLabel.CENTER);

        ButtonGroup stopoverGroup = new ButtonGroup();
        stopoverGroup.add(yesRadioButton);
        stopoverGroup.add(noRadioButton);

        searchField = new JTextField(25);
        searchField.setFont(new Font("Serif", Font.BOLD, 25));

        JLabel searchLabel = new JLabel("Start your Trip by Choosing a Destination:");
        searchLabel.setFont(new Font("Serif", Font.BOLD, 20));
        searchLabel.setHorizontalAlignment(JLabel.CENTER);

        searchButton = new JButton("Search");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        topPanel.add(welcomelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        topPanel.add(stopoeverJLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        topPanel.add(yesRadioButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        topPanel.add(noRadioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        topPanel.add(searchLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        topPanel.add(searchField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        topPanel.add(searchButton, gbc);

        table = new JTable();
        scrollPane = new JScrollPane(table);

        resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder("Search Results"));
        resultPanel.setSize(1000, 500);
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, resultPanel);
        splitPane.setResizeWeight(0);
        splitPane.setDividerSize(0);

        this.add(splitPane, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destination = searchField.getText();
                String stopString = "";

                if (yesRadioButton.isSelected()) {
                    stopString = "Yes";
                } else if (noRadioButton.isSelected()) {
                    stopString = "No";
                }
                ResultSet results = db.searchFor(destination, stopString);

                try {
                    table.setModel(buildTableModel(results));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        TableModel model = table.getModel();
                        selectedFlightId = Integer.parseInt(model.getValueAt(row, 0).toString());
                        selectedFlightSeats = Integer.parseInt(model.getValueAt(row, 4).toString());
                        // random number between 1 and selected flightseats 
                        selectedFlightSeats = ThreadLocalRandom.current().nextInt(1, selectedFlightSeats + 1);
                        reserveFlight(selectedFlightId, selectedFlightSeats);
                    }
                }
            }
        });
    }

    private TableModel buildTableModel(ResultSet results) throws SQLException {
        ResultSetMetaData metaData = results.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        String[] columnNames = new String[numberOfColumns];
        for (int i = 1; i <= numberOfColumns; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }

        List<Object[]> rows = new ArrayList<>();
        while (results.next()) {
            Object[] row = new Object[numberOfColumns];
            for (int i = 0; i < numberOfColumns; i++) {
                row[i] = results.getObject(i + 1);
            }
            rows.add(row);
        }

        Object[][] data = new Object[rows.size()][numberOfColumns];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }

        return new DefaultTableModel(data, columnNames);
    }

   private void reserveFlight(int selectedFlightId, int selectedFlightSeats) {
    // Implement the reservation logic here
    String passportId = JOptionPane.showInputDialog("Enter your passport ID:");
    passportId = passportId.trim();
    if (passportId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Passport ID cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    int id = passportId.hashCode();

    // Deduct the price from the bank account
    String query = "UPDATE passengers SET bank_account = bank_account - 700 WHERE id = " + id + ";";
    db.executeUpdate(query);

    // Perform the reservation
    String query2 = "INSERT INTO reservations (flight_id, passport_id, seats, price) VALUES (" +
            selectedFlightId + ", " + id + ", " + selectedFlightSeats + ", 700);";
    db.executeUpdate(query2);

    JOptionPane.showMessageDialog(this, "Flight Reserved!", "Success", JOptionPane.INFORMATION_MESSAGE);
}

}

