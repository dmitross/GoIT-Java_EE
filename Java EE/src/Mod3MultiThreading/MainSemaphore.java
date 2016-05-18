package Mod3MultiThreading;

public class MainSemaphore {

    /**
     // Запрашивает разрешение. Если есть свободное захватывает его.
     // Если нет - приостанавливает поток до тех пор пока не появится свободное разрешение.

     public void acquire();

     // Запрашивает переданое количество разрешений. Если есть переданое количество свободных разрешений захватывает их.
     // Если нет - приостанавливает поток до тех пор пока не появится переданое количество свободных разрешений.

     public void acquire(int permits);

     // Отпускает разрешение возвращая его семафору.

     public void release();

     // Отпускает переданое количество разрешений возварщая их семафору.

     public void release(int permits);

     // Возвращает количество свободных разрешений на данный момент.

     public int getAvailablePermits();
     */

    private final Object lock = new Object();
    private volatile int permits = 0;



    public MainSemaphore(int permits) {
        this.permits = permits;
    }

    public static void main(String[] args) throws InterruptedException {

        MainSemaphore sem = new MainSemaphore(5);

        sem.getAvailablePermits();
        sem.acquire();
        sem.acquire();
        sem.acquire();
        sem.release();
        sem.getAvailablePermits();


    }

    public void acquire () throws InterruptedException {       // acquire - purchase, get, buy, take on;
        synchronized (lock) {

            while (permits == 0) {
                lock.wait();
            }
            permits--;
            }
        }

    public void release () {
        synchronized (lock) {
            lock.notify();
            permits++;
        }
    }

    public void acquire (int permits) throws InterruptedException {
        synchronized (lock) {
            while (this.permits < permits) {
                lock.wait();
            }
            this.permits--;
        }
    }

    public void release (int permits) {
        synchronized (lock) {
            this.permits += permits;

            for (int i = 0; i < permits; i++) {
                lock.notify();
            }
        }
    }

    public int getAvailablePermits() {
        System.out.println("Available permits: " + permits);
        return permits;
    }

}
