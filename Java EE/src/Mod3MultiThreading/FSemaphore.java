package Mod3MultiThreading;


public class FSemaphore {

    private volatile int permits = 0;
    private final Object lock = new Object();

    public FSemaphore(int permits) {
        this.permits = permits;
    }

    public void acquire () throws InterruptedException {

        synchronized (lock) {
            while (permits == 0) {
                lock.wait();
            }
            permits--;
        }
    }

    public void release() {
        synchronized (lock) {
            lock.notify();
            permits++;
        }
    }

    public void acquire(int permits) throws InterruptedException {
        synchronized (lock) {
            while (this.permits < permits) {
                lock.wait();
            }
            this.permits--;
        }
    }

    public void release(int permits) {
        synchronized (lock) {
            this.permits += permits;

            for (int i = 0; i < permits; i++) {
                lock.notify();
            }
        }
    }

    public int getAvailablePermits() {
        return permits;
    }
}