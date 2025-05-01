package ui;

import db.ProductDao;
import model.Product;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProduct extends JFrame {
    // .form dosyasındaki bileşenlerin binding adları
    private JPanel mainPanel;
    private JTextField idField;
    private JButton loadButton;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockAmountField;
    private JButton updateButton;

    public UpdateProduct() {
        setTitle("Update Product");
        setContentPane(mainPanel);               // .form'daki ana panel
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            Image icon = new ImageIcon(getClass().getResource("/ui/icons/app_icon_32x32.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Icon not found: " + e.getMessage());
        }


        // Load butonu: ID'ye göre ürünü veritabanından getir, alanları doldur
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProduct();
            }
        });

        // Update butonu: alanlardaki verilerle güncelleme işlemini yap
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });
    }

    private void loadProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            Product product = ProductDao.getProductById(id);
            if (product != null) {
                nameField.setText(product.getName());
                priceField.setText(String.valueOf(product.getPrice()));
                stockAmountField.setText(String.valueOf(product.getStockAmount()));
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stockAmount = Integer.parseInt(stockAmountField.getText());

            Product updated = new Product(id, name, price, stockAmount);
            ProductDao.updateProduct(updated);

            JOptionPane.showMessageDialog(this, "Product updated successfully!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please fill all fields with valid values.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // İsteğe bağlı olarak buraya test amaçlı main ekleyebilirsin:
    // public static void main(String[] args) {
    //     new UpdateProduct();
    // }

}
