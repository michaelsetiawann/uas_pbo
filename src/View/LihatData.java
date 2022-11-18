package View;

import Database.DatabaseHandler;
import Model.CategoryUser;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Lenovo
 */
public class LihatData {

    static DatabaseHandler conn = new DatabaseHandler();
    JComboBox kategori = null;

    public LihatData() {

        JFrame frame = new JFrame("Lihat Data");
        Font myFont = new Font("Serif", Font.BOLD, 24);
        Font myFont2 = new Font("Serif", Font.BOLD, 18);

        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();

        label.setText("SELAMAT DATANG DI MENU LIHAT DATA");
        label.setBounds(80, 60, 500, 30);
        label.setFont(myFont);

        label2.setText("||");
        label2.setBounds(310, 100, 500, 30);
        label2.setFont(myFont);

        label3.setText("||");
        label3.setBounds(310, 120, 500, 30);
        label3.setFont(myFont);

        label4.setText("SILAHKAN MEMILIH MENU");
        label4.setBounds(200, 160, 300, 30);
        label4.setFont(myFont2);

        conn.connect();
        String query = "SELECT categoryId, categoryName FROM categoryUser";
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
            kategori.setRenderer(new LihatData.ItemRenderer());
            kategori.setBounds(80, 300, 200, 30);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton buttonCari = new JButton("LIHAT");
        buttonCari.setBounds(80, 240, 150, 40);

        JButton buttonBack = new JButton("BACK");
        buttonBack.setBounds(80, 370, 100, 40);

        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(label3);
        frame.add(label4);
        frame.add(kategori);
        frame.add(buttonCari);
        frame.add(buttonBack);
        frame.setSize(680, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        buttonCari.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                CategoryUser cat = (CategoryUser) kategori.getSelectedItem();
                new UserDetail(cat.getCategoryId());
            }
        });

        buttonBack.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
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
