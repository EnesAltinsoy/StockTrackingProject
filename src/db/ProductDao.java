package db;

import model.Product;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * ProductDao sınıfı ürünlerle ilgili veritabanı işlemlerini yönetir.
 * Ekleme, silme, güncelleme, listeleme gibi CRUD işlemleri içerir.
 */
public class ProductDao {
    private Connection connection;

    // Constructor (Bağlantı ile)
    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    // Parametresiz constructor (bazı durumlarda gerekebilir)
    public ProductDao() {}

    /**
     * Ürün eklemek için kullanılır.
     * @return boolean: ekleme başarılıysa true döner.
     */
    public boolean addProduct(String name, String category, int stockAmount, double price) {
        String query = "INSERT INTO products (name, category, stockAmount, price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setInt(3, stockAmount);
            statement.setDouble(4, price);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ID'ye göre ürün bilgilerini getirir.
     * @param id ürün ID’si
     * @return Product nesnesi
     */
    public static Product getProductById(int id) {
        Product product = null;
        try {
            Connection conn = DBConnection.connect();
            String query = "SELECT * FROM products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("StockAmount"),
                        rs.getDouble("price")
                );
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * Var olan bir ürünü günceller.
     * @return etkilenen satır sayısı
     */
    public static int updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, stockAmount = ? WHERE id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getStockAmount());
            ps.setInt(4, product.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Ürünü ID’ye göre siler. (instance metodu)
     */
    public void deleteProduct(int id) {
        Connection conn = DBConnection.connect();
        try {
            String query = "DELETE FROM products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Yeni ürün ekler (Product nesnesi üzerinden)
     */
    public static int insertProduct(Product product) {
        String sql = "INSERT INTO products (name, category, stockAmount, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setInt(3, product.getStockAmount());
            ps.setDouble(4, product.getPrice());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Belirtilen ID’ye sahip ürünü siler (static versiyon)
     */
    public static int deleteProductById(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Veritabanındaki tüm ürünleri listeler.
     * @return ArrayList<Product>
     */
    public static List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                int stockAmount = rs.getInt("stockAmount");
                double price = rs.getDouble("price");

                Product product = new Product(id, name, category, stockAmount, price);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}
