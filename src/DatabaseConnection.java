import java.sql.*;

// Kelas untuk menyambungkan ke database
public class DatabaseConnection {
    // Informasi database
    private static final String URL = "jdbc:mysql://localhost:3306/db_game";
    private static final String USER = "root";
    private static final String PASS = "";

    //Membuat koneksi ke database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
