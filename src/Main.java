import javax.swing.SwingUtilities;
// Kelas untuk menjalankan aplikasi
public class Main { 
    // Fungsi utama program
    public static void main(String[] args) {
        // Memulai aplikasi Swing
        SwingUtilities.invokeLater(() -> new GameTimerGUI().setVisible(true));
    }
}
