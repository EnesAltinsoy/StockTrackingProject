package model;

/**
 * user sınıfı, sisteme giriş yapan kullanıcıyı temsil eder.
 * Kullanıcının ID, kullanıcı adı, şifresi ve yetki bilgilerini içerir.
 */
public class user {
    private int id;
    private String username;
    private String password;
    private String authorization;

    /**
     * Tüm kullanıcı bilgileriyle user nesnesi oluşturur.
     */
    public user(int id, String username, String password, String authorization) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorization = authorization;
    }

    // --- Getter metodları ---
    // Kullanıcı bilgilerini okumak için kullanılır

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthorization() {
        return authorization;
    }

    // --- Setter metodları ---
    // Kullanıcı bilgilerini değiştirmek için kullanılır

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
