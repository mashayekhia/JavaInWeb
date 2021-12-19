package ir.man.JavaInWeb.interfaces;

import ir.man.JavaInWeb.interfaces.implement.ConnectionPool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public interface IDao<T> {

    Optional<List<T>> getAll() throws SQLException;

    Optional<T> getById(int id) throws SQLException;

    boolean insert(T object) throws SQLException;

    boolean update(T object) throws SQLException; // get affected rows

    boolean delete(int id) throws SQLException;
    boolean delete(T object) throws SQLException;

    void releaseConnection();
}
