package model;

/**
 * Product sınıfı, bir ürünün temel bilgilerini tutar.
 * Bu sınıf sayesinde ürünlerin ID, ad, kategori, stok ve fiyat gibi bilgilerine erişilir.
 */
public class Product {
    private int id;
    private String name;
    private String category;
    private int stockAmount;
    private double price;

    /**
     * Ürünün tüm bilgileriyle oluşturulmasını sağlar.
     */
    public Product(int id, String name, String category, int stockAmount, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stockAmount = stockAmount;
        this.price = price;
    }

    /**
     * Kategori olmadan ürün nesnesi oluşturmak için alternatif constructor.
     */
    public Product(int id, String name, double price, int stockAmount) {
        this(id, name, null, stockAmount, price);
    }

    // --- Getter metodları ---
    // Ürünün alan bilgilerini okumak için kullanılır

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public double getPrice() {
        return price;
    }

    // --- Setter metodları ---
    // Ürünün alan bilgilerini değiştirmek için kullanılır

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
