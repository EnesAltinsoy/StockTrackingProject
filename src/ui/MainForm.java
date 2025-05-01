package ui;

import model.user;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JLabel welcomeField;
    private JButton addProductButton;
    private JButton updateProductButton;
    private JButton deleteProductButton;
    private JButton viewProductsButton;
    private JButton logoutButton;

    public MainForm(user user) {
        setTitle("Stock Tracking System - Welcome " + user.getUsername());
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // ✅ İkonu koru
        try {
            Image icon = new ImageIcon(getClass().getResource("/ui/icons/app_icon_32x32.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Icon not found: " + e.getMessage());
        }

        // ✅ Arka plan ve yazı stili
        mainPanel.setBackground(new Color(240, 240, 240));
        welcomeField.setFont(new Font("Segoe UI", Font.BOLD, 16));
        welcomeField.setForeground(new Color(50, 50, 50));
        welcomeField.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeField.setText("Welcome, " + user.getUsername() + " (" + user.getAuthorization() + ")");

        // ✅ Butonları şıklaştır
        JButton[] buttons = {
                addProductButton, updateProductButton,
                deleteProductButton, viewProductsButton, logoutButton
        };

        for (JButton button : buttons) {
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);

            // Hover efekti
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(60, 120, 170));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(70, 130, 180));
                }
            });
        }

        // ✅ Aksiyonlar
        addProductButton.addActionListener(e -> new AddProductForm());
        updateProductButton.addActionListener(e -> new UpdateProduct());
        deleteProductButton.addActionListener(e -> new DeleteProductForm());
        viewProductsButton.addActionListener(e -> new ViewProductsForm());
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        user testUser = new user(0, "admin", "1234", "Manager");
        SwingUtilities.invokeLater(() -> new MainForm(testUser));
    }
}
