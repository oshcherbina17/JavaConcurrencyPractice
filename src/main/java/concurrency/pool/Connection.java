package concurrency.pool;

public class Connection {
    private static int counter = 0;
    private int id;

    public Connection() {
        this.id = ++counter;
        System.out.println("Connection created with ID: " + id);
    }

    @Override
    public String toString() {
        return "Connection{" + "id=" + id + '}';
    }
}
