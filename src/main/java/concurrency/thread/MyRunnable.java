package concurrency.thread;

public class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread executed using Runnable: " + Thread.currentThread().getName());
    }
}
