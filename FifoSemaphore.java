import java.util.LinkedList;
import java.util.Queue;


public class FifoSemaphore {
    private int permits;
    private final Queue<Thread> waitQueue;

    public FifoSemaphore(int permits) {
        this.permits = permits;
        this.waitQueue = new LinkedList<>();
    }

    public synchronized void acquire() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        waitQueue.add(currentThread);
        while (waitQueue.peek() != currentThread || permits == 0) {
            wait();
        }
        waitQueue.remove();
        permits--;
    }

    public synchronized void release() {
        permits++;
        notifyAll();
    }
}
