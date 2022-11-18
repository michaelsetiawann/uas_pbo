package Controller;

import Database.DatabaseHandler;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Lenovo
 */
public class UserController {

    public static String LoginController(String tipe, String userName, String userPassword) {

        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(userPassword.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            userPassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            java.sql.Statement stat = conn.con.createStatement();
            ResultSet result = stat.executeQuery("select * from " + tipe + " where userName='" + userName + "'");
            if (result.next()) {
                if (userPassword.equals(result.getString("userPassword"))) {
                    return "Login Berhasil!";
                } else {
                    return "Password Salah!";
                }
            } else {
                return "User tidak ditemukan!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
    
    public static void register(String userName, String userEmail, String userPassword, String userGender, int userCategory) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        String query = "INSERT INTO users(userName, userEmail, userPassword, userGender, userCategory) VALUES(?,?,?,?,?)";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(userPassword.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            userPassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, userEmail);
            stmt.setString(3, userPassword);
            stmt.setString(4, userGender);
            stmt.setInt(5, userCategory);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil melakukan registrasi");
            
        } catch (SQLException e) {
            if (e.getMessage().contains("'email'")) {
                JOptionPane.showMessageDialog(null, "email sudah digunakan");
            } else if (e.getMessage().contains("'password'")) {
                JOptionPane.showMessageDialog(null, "password sudah digunakan");
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fatal pusing!");
            }
        }
    }
    
    public static String findJK(JRadioButton jk1, JRadioButton jk2) {
        String jk = "";
        if (jk1.isSelected()) {
            jk = "Laki Laki";
        } else if (jk2.isSelected()) {
            jk = "Perempuan";
        }
        return jk;
    }

    public void tampil_combobox() {
        JComboBox kategori = new JComboBox();
        Statement stmn = null;
        try {
            DatabaseHandler conn = new DatabaseHandler();
            conn.connect();
            String sql = "select * from kategori";
            ResultSet res = stmn.executeQuery(sql);
            kategori.addItem("-Pilih Kategori-");
            while (res.next()) {
                kategori.addItem(res.getString("Category"));
            }
        } catch (Exception ex) {

        }
    }
}
