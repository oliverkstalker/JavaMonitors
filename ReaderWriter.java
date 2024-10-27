public class ReaderWriter {
    private int readers = 0;
    private boolean writer = false;

    public synchronized void startRead() throws InterruptedException {
        while (writer) {
            wait();
        }
        readers++;
    }

    public synchronized void endRead() {
        readers--;
        notifyAll();
    }

    public synchronized void startWrite() throws InterruptedException {
        while (writer || readers > 0) {
            wait();
        }
        writer = true;
    }

    public synchronized void endWrite() {
        writer = false;
        notifyAll();
    }
}
