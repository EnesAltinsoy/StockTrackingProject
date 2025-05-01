package ui;

import db.ProductDao;
import model.Product;

import javax.swing.*;
import java.awt.Image;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewProductsForm extends JFrame {
    private JPanel mainPanel;
    private JTable productTable;
    private JButton closeButton;

    public ViewProductsForm() {
        setTitle("View Products");
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            Image icon = new ImageIcon(getClass().getResource("/ui/icons/app_icon_32x32.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Icon not found: " + e.getMessage());
        }

        // Tabloyu doldur
        loadProductData();

        // Kapat butonu
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // pencereyi kapat
            }
        });
    }

    private void loadProductData() {
        List<Product> products = ProductDao.getAllProducts();

        String[] columnNames = {"ID", "Name", "Category", "Stock Amount", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Product product : products) {
            Object[] row = {
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    product.getStockAmount(),
                    product.getPrice()
            };
            model.addRow(row);
        }

        productTable.setModel(model);
    }
}

