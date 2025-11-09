import java.sql.*;
import java.util.*;

public class GameDAO {
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM games")) {

            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("genre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }
}

