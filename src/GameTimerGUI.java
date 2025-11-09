import javax.swing.*;
import java.awt.*;
import java.util.List;
// Kelas utama untuk tampilan (GUI)
public class GameTimerGUI extends JFrame {

    private JComboBox<Game> comboGames;
    private JButton btnPlay;
    private JLabel lblTimer, lblGame;
    private CountdownWorker countdown;

    // Konstruktor untuk mengatur komponen visual (GUI)
    public GameTimerGUI() {
        setTitle("🎮 Game Timer");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel atas dengan judul
        JPanel top = new JPanel(new GridLayout(2, 1));
        lblGame = new JLabel("Pilih Game untuk Dimainkan", SwingConstants.CENTER);
        lblGame.setFont(new Font("Arial", Font.BOLD, 18));
        lblTimer = new JLabel("⏳ Time Left: -", SwingConstants.CENTER);
        lblTimer.setFont(new Font("Arial", Font.BOLD, 16));
        top.add(lblGame);
        top.add(lblTimer);
        add(top, BorderLayout.NORTH);
        
        // Panel tengah dengan ComboBox
        JPanel center = new JPanel();
        center.add(new JLabel("Daftar Game: "));
        comboGames = new JComboBox<>();
        loadGames();
        center.add(comboGames);
        add(center, BorderLayout.CENTER);
        
        // Panel bawah dengan tombol play
        JPanel bottom = new JPanel();
        btnPlay = new JButton("▶️ Mainkan");
        bottom.add(btnPlay);
        add(bottom, BorderLayout.SOUTH);

        // Memberikan aksi pada tombol "Mainkan"
        btnPlay.addActionListener(e -> startGame());
    }

    // Fungsi untuk mengisi ComboBox dengan data dari database
    private void loadGames() {
        GameDAO dao = new GameDAO();
        List<Game> games = dao.getAllGames();
        for (Game g : games) {
            comboGames.addItem(g);
        }
    }

    // Fungsi yang berjalan saat tombol "Mainkan" diklik
    private void startGame() {
        Game selected = (Game) comboGames.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih game terlebih dahulu!");
            return;
        }

        lblGame.setText("🎮 Sedang bermain: " + selected.getName());
        lblTimer.setText("⏳ Time Left: 10 seconds");
        if (countdown != null && !countdown.isDone()) countdown.cancel(true);
        // BUat timer baru
        countdown = new CountdownWorker(10, lblTimer, () -> {
            // Aksi onFinish menampilkan pesan saat waktu habis
            JOptionPane.showMessageDialog(this,
                    "Waktu habis untuk bermain " + selected.getName() + "!",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
        });
        countdown.execute(); // MUlai timer
    }
}

