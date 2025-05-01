package ui;

import db.ProductDao;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
public class AddProductForm extends JFrame {
    private JPanel mainPanel;
    private JTextField productNameField;
    private JTextField categoryField;
    private JTextField stockAmountField;
    private JTextField priceField;
    private JButton saveButton;

    public AddProductForm() {
        setTitle("➕ Add New Product");
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            Image icon = new ImageIcon(getClass().getResource("/ui/icons/app_icon_32x32.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Icon not found: " + e.getMessage());
        }


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProduct();
            }
        });
    }

    private void saveProduct() {
        try {
            String name = productNameField.getText().trim();
            String category = categoryField.getText().trim();
            int stockAmount = Integer.parseInt(stockAmountField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (name.isEmpty() || category.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            Product newProduct = new Product(0, name, category, stockAmount, price);
            int rows = ProductDao.insertProduct(newProduct);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Product saved successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Product could not be saved.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for stock and price.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}

