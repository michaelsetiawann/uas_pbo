package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class Login {

    public Login(String tipeUser) {
        JFrame frame = new JFrame("Login");
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        Font myFont = new Font("Serif", Font.BOLD, 24);
        Font myFont2 = new Font("Serif", Font.BOLD, 18);

        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel infoFoto = new JLabel();
        JLabel iconFoto = new JLabel();
        iconFoto.setVisible(false);

        label.setText("SELAMAT DATANG DI MENU LOGIN");
        label.setBounds(80, 60, 500, 30);
        label.setFont(myFont);
        
        Icon image = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\images (1).jpg");
        
        JButton btnFoto = new JButton(image);
        btnFoto.setBounds(400, 100, 100, 80);
        

        label3.setText("Username");
        label3.setBounds(80, 100, 200, 30);
        label3.setFont(myFont2);
        
        JTextField userName = new JTextField();
        userName.setBounds(170, 100, 200, 30);
        
        label4.setText("Password");
        label4.setBounds(80, 150, 200, 30);
        label4.setFont(myFont2);
        JPasswordField pass = new JPasswordField();
        pass.setBounds(170, 150, 200, 30);
        
        
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(80, 200, 150, 40);

        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(btnLogin);
        frame.add(btnFoto);
        frame.add(infoFoto);
        frame.add(iconFoto);
        frame.add(userName);
        frame.add(pass);
        frame.setLayout(null);
        frame.setVisible(true);
        
        
        btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String result = Controller.UserController.LoginController(tipeUser,userName.getText(),pass.getText());
                if(result.equals("Login Berhasil!")){
                    new LihatData();
                    frame.dispose();
                    JOptionPane.showMessageDialog(null,result);
                    //masukin tujuan dibawah
                    
                }else if(result.equals("Password Salah!")){
                    JOptionPane.showMessageDialog(null,result);
                    pass.setText("");
                    pass.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null,result);
                    userName.setText("");
                    pass.setText("");
                    userName.requestFocus();
                }
            }  
        });
    }
}
