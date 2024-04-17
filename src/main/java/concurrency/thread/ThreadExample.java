package concurrency.thread;

public class ThreadExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable());
        MyThread thread2 = new MyThread();

        thread1.start();
        thread2.start();
    }
}
