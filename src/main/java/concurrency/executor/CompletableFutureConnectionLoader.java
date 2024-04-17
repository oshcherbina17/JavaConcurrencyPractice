package concurrency.executor;

import concurrency.pool.Connection;
import concurrency.pool.ConnectionPool;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureConnectionLoader {
    public static void main(String[] args) {
        final int poolSize = 5;
        ConnectionPool connectionPool = ConnectionPool.getInstance(poolSize);

        CompletableFuture<?>[] futures = new CompletableFuture[7];
        for (int i = 0; i < futures.length; i++) {
            futures[i] = CompletableFuture.runAsync(() -> {
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

        CompletableFuture.allOf(futures).join();
        System.out.println("All connections have been processed");
    }
}
