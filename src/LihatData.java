import java.awt.FlowLayout;
import java.sql.*;
import javax.swing.*;

public class LihatData extends JFrame {
    String[][] data = new String[500][3];
    String[] kolom = {"Nim", "Nama", "Alamat"};
    JTable tabel;
    JScrollPane scrollPane;
    String DB_URL = "jdbc:mysql://localhost/praktikum";
    String USER = "root";
    String PASS = "";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public LihatData() {
        setTitle("Data Mahasiswa!");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String query = "select * from mahasiswa";
            resultSet = statement.executeQuery(query);

            int p = 0;
            while (resultSet.next()) {
                data[p][0] = resultSet.getString("nim");
                data[p][1] = resultSet.getString("nama");
                data[p][2] = resultSet.getString("alamat");
                p++;
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);

        setLayout(new FlowLayout());
        add(scrollPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new LihatData();
    }
}
