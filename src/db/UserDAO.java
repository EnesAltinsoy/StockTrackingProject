package db;

import model.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Bu sınıf, veritabanındaki kullanıcı bilgilerine erişmek için kullanılır.
 * Giriş yapan kullanıcıyı kullanıcı adı ve şifreye göre kontrol eder.
 */
public class UserDAO {

    /**
     * Kullanıcı adı ve şifreye göre kullanıcıyı veritabanında arar.
     *

     * @return Eşleşen kullanıcı varsa user nesnesi, yoksa null
     */
    public user getUserByUsernameAndPassword(String username, String password) {
        // Veritabanına bağlan
        Connection conn = DBConnection.connect();

        user user = null;

        if (conn != null) {
            // SQL sorgusu: kullanıcıyı kullanıcı adı ve şifreye göre bul
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";

            try {
                // Sorguyu hazırla ve parametreleri yerleştir
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);

                // Sorguyu çalıştır ve sonucu al
                ResultSet rs = stmt.executeQuery();

                // Eğer eşleşen kullanıcı varsa user nesnesi oluştur
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String auth = rs.getString("authorization");

                    user = new user(id, username, password, auth);
                }

                // Kaynakları kapat
                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("UserDAO error: " + e.getMessage());
            }
        }

        return user; // Giriş başarılıysa user nesnesi, başarısızsa null döner
    }
}


