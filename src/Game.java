// Kelas untuk menyimpan data satu game
public class Game {
    private int id;
    private String name;
    private String genre;

    // Konstruktor untuk mengatur data game saat objek dibuat
    public Game(int id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    // Fungsi untuk mengambil nilai
    public int getId() { return id; }
    public String getName() { return name; }
    public String getGenre() { return genre; }

    // Mengubah objek menjadi teks
    @Override
    public String toString() {
        return name + " (" + genre + ")";
    }
}
