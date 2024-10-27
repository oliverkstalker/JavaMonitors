class SemaphoreMonitor {
    private int permits;

    public SemaphoreMonitor(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException{
        while (permits == 0) {
            wait();
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notify();
    }
}
