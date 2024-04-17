package concurrency.thread;

public class MyThread extends Thread {
    public void run() {
        System.out.println("Thread executed using Thread class: " + Thread.currentThread().getName());
    }
}
