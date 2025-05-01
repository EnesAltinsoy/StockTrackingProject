package ui;

import model.user;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        $$$setupUI$$$(); // GUI Designer tarafından ayarlanır

        setTitle("Login");
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Uygulama ikonu
        try {
            Image icon = new ImageIcon(getClass().getResource("/ui/icons/app_icon_32x32.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Icon not found: " + e.getMessage());
        }

        // Renk, font ve stiller
        mainPanel.setBackground(new Color(242, 242, 242)); // Açık gri
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Hover efekti
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(60, 120, 170));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(70, 130, 180));
            }
        });

        // Buton aksiyonu
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.equals("admin") && password.equals("1234")) {
            user fakeUser = new user(0, "admin", "1234", "Manager");
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new MainForm(fakeUser);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }

    private void $$$setupUI$$$() {
        // IntelliJ GUI Designer doldurur
    }

    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
