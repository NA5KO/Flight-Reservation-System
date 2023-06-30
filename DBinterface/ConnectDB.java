package DBinterface;
import GUI.History;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectDB{

    private static final String url = "jdbc:postgresql://localhost:5432/FRS";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    public static final String Statement = null;
    static Connection connection;
    Statement stm; 
    public ConnectDB(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Database connected!");
            stm = (Statement) connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Statement getStatement() {
        return (Statement) stm;
    }
    public Connection getConnection() {
        return connection;
    }
    
    public ResultSet searchFor(String des,String stopover) {
        ResultSet result = null;

        
        try {
            if (stopover == "No" ){
            String sql = "SELECT * FROM Flight WHERE destination LIKE ? AND stopover = 'No'" ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + des + "%");
            result = statement.executeQuery();
        } 
        else if (stopover == "Yes" ){
            String sql = "SELECT * FROM Flight WHERE destination LIKE ? AND stopover = 'Yes'" ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + des + "%");
            result = statement.executeQuery();
        }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
        public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public JPanel checkPassenger(int id, String pass) throws SQLException {
    String query = "SELECT * FROM passenger WHERE passport_id = ? AND pass = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    statement.setString(2, pass);
    ResultSet rs = statement.executeQuery();

    if (rs.next()) {
        JOptionPane.showMessageDialog(null, this, "Login Successful", id);

        String query2 = "select * from reservation where passport_id = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setInt(1, id);
        ResultSet rs2 = statement2.executeQuery();

        JPanel panel = new JPanel();
        JTable table = new JTable(buildTableModel(rs2));
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    } else {
        JOptionPane.showMessageDialog(null, this, "Wrong ID or Password", id);
        return null;
    }
}

private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();
    String[] columnNames = new String[columnCount];
    for (int i = 1; i <= columnCount; i++) {
        columnNames[i - 1] = metaData.getColumnName(i);
    }

    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    try {
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = rs.getObject(i);
            }
            tableModel.addRow(rowData);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tableModel;
}
public void executeUpdate(String query) {
    try {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}