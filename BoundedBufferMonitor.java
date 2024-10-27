import java.util.LinkedList;
import java.util.Queue;

public class BoundedBufferMonitor<T> {
    private final Queue<T> buffer;
    private final int capacity;

    public BoundedBufferMonitor(int capacity) {
        this.capacity = capacity;
        this.buffer = new LinkedList<>();
    }

    public synchronized void deposit(T item) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait();
        }
        buffer.add(item);
        notifyAll();
    }

    public synchronized T fetch() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        T item = buffer.remove();
        notifyAll();
        return item;
    }
}
