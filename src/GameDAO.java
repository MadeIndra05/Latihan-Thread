import java.sql.*;
import java.util.*;
// Kelas untuk mengambil data game dari database
public class GameDAO {
    // Fungsi untuk mengambil semua dat dari tabel games
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        // agar koneksi otomatis tertutup
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM games")) {
            
            // Looping untuk setiap baris data di tabel
            while (rs.next()) {
                // buat objek game dan menambahkan ke daftar
                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("genre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Pesan error
        }
        return games; // Mengembalikan daftar game
    }
}

