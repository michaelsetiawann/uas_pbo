package View;

import Database.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Lenovo
 */
public class UserDetail {

    static DatabaseHandler conn = new DatabaseHandler();

    public UserDetail(int Id) {

        String columns[] = {"Username", "User Email", "Nama Kategori", "User Gender", "User Folower"};
        DefaultTableModel model = new DefaultTableModel(null, columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        TableColumn eventColumn = table.getColumnModel().getColumn(2);
        eventColumn.setPreferredWidth(100);
        JScrollPane pane = new JScrollPane(table);

        conn.connect();
        String query = "SELECT b.categoryName AS 'Nama Kategori', a.userName, a.userEmail, a.userPassword, a.userGender, a.userCategory, a.userFollowers FROM users a INNER JOIN categoryuser b ON a.userCategory = b.categoryId WHERE b.categoryId='" + Id + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String username = rs.getString("userName");
                String userEmail = rs.getString("userEmail");
                String kategori = rs.getString("Nama Kategori");
                String gender = rs.getString("userGender");
                String followers = rs.getString("userFollowers");

                String[] dataUser = {username, userEmail, kategori, gender, followers};
                model.addRow(dataUser);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("User Detail");
        JPanel panel = new JPanel();

        JButton buttonBack = new JButton("BACK");
        buttonBack.setBounds(60, 440, 100, 40);

        panel.add(pane);
        frame.add(buttonBack);
        frame.add(panel);
        frame.add(panel);
        frame.setSize(600, 700);
        frame.setVisible(true);

        buttonBack.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                new LihatData();
            }
        });
    }
}
