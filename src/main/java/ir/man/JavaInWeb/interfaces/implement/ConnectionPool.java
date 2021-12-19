package ir.man.JavaInWeb.interfaces.implement;

import ir.man.JavaInWeb.interfaces.IConnectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.InflaterInputStream;

public class ConnectionPool implements IConnectionPool {

    private String url;
    private String user;
    private String password;
    private static int MAX_POOL_SIZE;
    private static List<Connection> pool;
    private static List<Connection> used;
    //private static ConnectionPool connectionPool;

    private ConnectionPool() {
        this.setMAX_POOL_SIZE(10);
    }

    private ConnectionPool(String url, String user, String password) {
        this();
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static int getMAX_POOL_SIZE() {
        return MAX_POOL_SIZE;
    }

    public void setMAX_POOL_SIZE(int MAX_POOL_SIZE) {
        this.MAX_POOL_SIZE = MAX_POOL_SIZE;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSize() {
        if (pool != null)
            return pool.size();
        return 0;
    }

    public int getUsedSize() {
        if (used != null)
            return used.size();
        return 0;
    }

    public static ConnectionPool connectionPoolInstantiate(String url, String user, String password)  {
        pool = new ArrayList<>(getMAX_POOL_SIZE());
        used = new ArrayList<>(getMAX_POOL_SIZE());
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            for (int i = 0; i < pool.size(); i++) {
                Connection connection = createConnection(url, user, password);
                connection.setAutoCommit(false);
                pool.add(connection);
            }
            return new ConnectionPool(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            return null;
        }
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (pool.isEmpty()) {
            if (used.size() < MAX_POOL_SIZE) {
                pool.add(createConnection(url, user, password));
            } else {
                System.out.println("Maximum pool size reached, no available connections!");
                return null;
            }
        }

        Connection newConnection = pool.remove(pool.size() - 1);
        if (!newConnection.isValid(2)) {
            newConnection = createConnection(url, user, password);
        }

        used.add(newConnection);
        return newConnection;
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(url, user, password);
    }

    public boolean releaseConnection(Connection connection) {
        pool.add(connection);
        boolean connectionRemoved = used.remove(connection);
        System.out.println(connection.toString() + " released");
        return connectionRemoved;
    }

    public void clear(Connection connection) throws SQLException {
        connection.close();
    }

    public void shutdown() throws SQLException {
        if (used.size() > 0) {
            //used.forEach(this::releaseConnection);
            for (int i = used.size() - 1; i >= 0; i--) {
                used.get(i).close();
                this.releaseConnection(used.get(i));
            }
        }
        pool.clear();
        System.out.println("All connections cleared");
    }
}
