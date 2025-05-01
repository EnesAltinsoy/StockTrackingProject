package ui;

import db.ProductDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

public class DeleteProductForm extends JFrame {
    private JPanel mainPanel;
    private JTextField idField;
    private JButton deleteButton;

    public DeleteProductForm() {
        setTitle("Delete Product");
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            Image icon = new ImageIcon(getClass().getResource("/ui/icons/app_icon_32x32.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Icon not found: " + e.getMessage());
        }


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });
    }

    private void deleteProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            int result = ProductDao.deleteProductById(id);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No product found with this ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
