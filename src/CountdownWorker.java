import javax.swing.*;

public class CountdownWorker extends SwingWorker<Void, Integer> {
    private int seconds;
    private JLabel label;
    private Runnable onFinish;

    public CountdownWorker(int seconds, JLabel label, Runnable onFinish) {
        this.seconds = seconds;
        this.label = label;
        this.onFinish = onFinish;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (seconds >= 0 && !isCancelled()) {
            publish(seconds);
            Thread.sleep(1000);
            seconds--;
        }
        return null;
    }

    @Override
    protected void process(java.util.List<Integer> chunks) {
        int current = chunks.get(chunks.size() - 1);
        label.setText("⏳ Time Left: " + current + " seconds");
    }

    @Override
    protected void done() {
        label.setText("⏰ Time’s Up!");
        onFinish.run();
    }
}
