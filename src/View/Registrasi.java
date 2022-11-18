package View;

import Database.DatabaseHandler;
import Model.CategoryUser;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Lenovo
 */
public class Registrasi {

    static DatabaseHandler conn = new DatabaseHandler();
    CategoryUser model = new CategoryUser();
    JComboBox kategori = null;

    public Registrasi() {

        JFrame frame = new JFrame("Registrasi");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        Font myFont = new Font("Serif", Font.BOLD, 24);
        Font myFont2 = new Font("Serif", Font.BOLD, 18);

        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
      JLabel label6 = new JLabel();

        label6.setText("User Followers");
        label6.setBounds(80, 60, 200, 30);
        label6.setFont(myFont2);
        
        JTextField userFollowers = new JTextField();
        userFollowers.setBounds(170, 60, 200, 30);

        label5.setText("Username");
        label5.setBounds(80, 150, 200, 30);
        label5.setFont(myFont2);

        JTextField username = new JTextField();
        username.setBounds(170, 150, 200, 30);
        
        label2.setText("Email");
        label2.setBounds(80, 100, 200, 30);
        label2.setFont(myFont2);

        JTextField email = new JTextField();
        email.setBounds(170, 100, 200, 30);

        label3.setText("Password");
        label3.setBounds(80, 200, 200, 30);
        label3.setFont(myFont2);
        JPasswordField pass = new JPasswordField();
        pass.setBounds(170, 200, 200, 30);

        JRadioButton jk1 = new JRadioButton(" Laki-laki");
        JRadioButton jk2 = new JRadioButton(" Perempuan");

        jk1.setBounds(80, 250, 200, 30);
        jk1.setFont(myFont);
        jk2.setBounds(300, 250, 200, 30);
        jk2.setFont(myFont);

        conn.connect();
        String query = "SELECT categoryId, categoryName FROM categoryuser";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            Vector model = new Vector();
            while (rs.next()) {
                int id = rs.getInt("categoryId");
                String name = rs.getString("categoryName");
                model.addElement(new CategoryUser(id, name));
            }
            kategori = new JComboBox(model);
            kategori.setRenderer(new ItemRenderer());
            kategori.setBounds(80, 300, 200, 30);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton btnRegis = new JButton("REGISTRASI");
        btnRegis.setBounds(80, 350, 150, 40);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(250, 350, 150, 40);

        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
        frame.add(username);
        frame.add(kategori);
        frame.add(btnRegis);
        frame.add(btnBack);
        frame.add(email);
        frame.add(pass);
        frame.add(jk1);
        frame.add(jk2);
        frame.add(userFollowers);
        frame.setLayout(null);
        frame.setVisible(true);
        
        jk1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jk1.isSelected()) {
                    jk2.setEnabled(false);
                } else {
                    jk2.setEnabled(true);
                }
            }
        });

        jk2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jk2.isSelected()) {
                    jk1.setEnabled(false);
                } else {
                    jk1.setEnabled(true);
                }
            }

        });

        btnRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CategoryUser cat = (CategoryUser) kategori.getSelectedItem();
                if (username.getText().isEmpty() || email.getText().isEmpty() || pass.getPassword().equals("") || Controller.UserController.findJK(jk1, jk2).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua kolom wajib diisi!");
                } else {
                    Controller.UserController.register(username.getText(), email.getText(), pass.getText(), Controller.UserController.findJK(jk1, jk2), cat.getCategoryId(), userFollowers.getText().toString());
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new MainMenu();
            }
        });
    }

    public class ItemRenderer extends BasicComboBoxRenderer {

        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);

            if (value != null) {
                CategoryUser cat = (CategoryUser) value;
                setText(cat.getCategoryName().toUpperCase());
            }

            return this;
        }
    }
}
