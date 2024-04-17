package concurrency.executor;

import concurrency.pool.Connection;
import concurrency.pool.ConnectionPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionLoader {
    public static void main(String[] args) {
        final int poolSize = 5;
        ConnectionPool connectionPool = ConnectionPool.getInstance(poolSize);

        ExecutorService executor = Executors.newFixedThreadPool(7);

        for (int i = 0; i < 7; i++) {
            executor.execute(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    System.out.println(Thread.currentThread().getName() + " got connection: " + connection);
                    Thread.sleep(1000);
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("All connections have been processed");
    }
}
