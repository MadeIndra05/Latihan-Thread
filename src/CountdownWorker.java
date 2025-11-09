import javax.swing.*;
// Kelas untuk countdown timer pada background
public class CountdownWorker extends SwingWorker<Void, Integer> {
    private int seconds;
    private JLabel label;
    private Runnable onFinish; // aksi dijalankan ketika selesai

    // konstruktor untuk mengatur durasi, label, dan aksi onFinish
    public CountdownWorker(int seconds, JLabel label, Runnable onFinish) {
        this.seconds = seconds;
        this.label = label;
        this.onFinish = onFinish;
    }
    
    // Fungsi yang berjalan pada background thread
    @Override
    protected Void doInBackground() throws Exception {
        while (seconds >= 0 && !isCancelled()) {
            publish(seconds); // kirim data detik ke proses
            Thread.sleep(1000); // jeda 1 detik
            seconds--;
        }
        return null;
    }

    // Fungsi berjalan di thread GUI yang aman untuk meng update label
    @Override
    protected void process(java.util.List<Integer> chunks) {
        int current = chunks.get(chunks.size() - 1);
        label.setText("⏳ Time Left: " + current + " seconds");
    }

    // Fungsi berjalan di thread GUI ketika doInBackground selesai
    @Override
    protected void done() {
        label.setText("⏰ Time’s Up!");
        onFinish.run(); // Menjalankan aksi onFinish dengan menampilkan pesan
    }
}
