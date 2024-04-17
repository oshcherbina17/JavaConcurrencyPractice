package concurrency.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> pool;

    private ConnectionPool(int size) {
        pool = new ArrayBlockingQueue<>(size);

        for (int i = 0; i < size; i++) {
            pool.offer(new Connection());
        }
    }

    public static synchronized ConnectionPool getInstance(int size) {
        if (instance == null) {
            instance = new ConnectionPool(size);
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(Connection connection) {
        pool.offer(connection);
    }
}
