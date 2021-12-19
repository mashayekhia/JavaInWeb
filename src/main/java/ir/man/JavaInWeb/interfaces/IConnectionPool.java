package ir.man.JavaInWeb.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {
    Connection getConnection() throws SQLException, ClassNotFoundException; // get a created connection from Pool
    boolean releaseConnection(Connection connection);
}
