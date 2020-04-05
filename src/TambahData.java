import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TambahData extends JFrame {
    JPanel panel = new JPanel();
    JLabel lNama,lNIM,lAlamat;
    JTextField tfNama, tfNIM;
    JTextArea taAlamat;
    JButton btnTambah;


    String DB_URL = "jdbc:mysql://localhost/praktikum";
    String USER = "root";
    String PASS = "";
    Connection connection;
    Statement statement;

    public TambahData(){
        setTitle("Database Connection");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(600,400);

        lNIM = new JLabel("NIM : ");
        tfNIM = new JTextField();
        lNama = new JLabel("Nama : ");
        tfNama = new JTextField();
        lAlamat = new JLabel("Alamat : ");
        taAlamat = new JTextArea();
        btnTambah = new JButton("Tambah");

        JLabel jLabel = new JLabel("Tambah Data");
        panel.add(jLabel);
        jLabel.setBounds(50,20,300,20);

        add(panel);
        panel.setBounds(10,10,755,525);
        panel.setLayout(null);

        panel.add(lNIM);
        lNIM.setBounds(50,100,90,20);
        panel.add(tfNIM);
        tfNIM.setBounds(150,100,120,20);

        panel.add(lNama);
        lNama.setBounds(50,125,90,20);
        panel.add(tfNama);
        tfNama.setBounds(150,125,120,20);

        panel.add(lAlamat);
        lAlamat.setBounds(50,150,90,20);
        panel.add(taAlamat);
        taAlamat.setBounds(150,150,120,20);

        panel.add(btnTambah);
        btnTambah.setBounds(130,180,80,20);

        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputData();
            }
        });
    }

    private void InputData() {
        String sNIM, sNama, sAlamat;
        sNIM = tfNIM.getText();
        sNama = tfNama.getText();
        sAlamat = taAlamat.getText();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            String query = "INSERT INTO `mahasiswa` (`nim`,`nama`,`alamat`) VALUES ('" + sNIM + "','" + sNama + "','" + sAlamat + "')";
            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Data Tersimpan", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Hasil", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!",
                    "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TambahData();
    }
}
