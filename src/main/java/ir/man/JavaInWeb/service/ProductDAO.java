package ir.man.JavaInWeb.service;

import ir.man.JavaInWeb.interfaces.IConnectionPool;
import ir.man.JavaInWeb.interfaces.IDao;
import ir.man.JavaInWeb.interfaces.implement.ConnectionPool;
import ir.man.JavaInWeb.model.Product;
import ir.man.JavaInWeb.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductDAO implements IDao<Product> {
    private ConnectionPool dbConnection;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public ProductDAO(IConnectionPool dbConnection) throws SQLException, ClassNotFoundException {
        this.dbConnection = (ConnectionPool) dbConnection;
        this.connection = dbConnection.getConnection();
    }

    @Override
    public Optional<List<Product>> getAll() throws SQLException {
        // Jdbc
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select p.id as ProductId,p.name as ProductName,c.id as ProductCategoryId,c.name as ProductCategoryName,c.description as ProductCategoryDesc\n" +
                        "from Product p,ProductCategory c\n" +
                        "where p.category = c.id");
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(Product.newBuilder().setId(resultSet.getInt("ProductId"))
                    .setName(resultSet.getString("ProductName"))
                    .setCategory(ProductCategory.newBuilder().setId(resultSet.getInt("ProductCategoryId"))
                            .setName(resultSet.getString("ProductCategoryName"))
                            .setDescription(resultSet.getString("ProductCategoryDesc"))
                            .createProductCategory()).createProduct());
        }
        return Optional.ofNullable(products);
    }

    @Override
    public Optional<Product> getById(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM Product WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return Optional.of(Product.newBuilder().setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setCategory(null).createProduct());
        return Optional.empty();
    }

    @Override
    public boolean insert(Product product) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO Product (name)VALUES(?)");
        preparedStatement.setString(1, product.getName());
        preparedStatement.executeUpdate();
        connection.commit();
        return true;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        try {
            Objects.requireNonNull(product);
        } catch (NullPointerException ex) {
            return false;
        }
        if (getById(product.getId()).isPresent()) {
            preparedStatement = connection.prepareStatement("UPDATE Product SET name = ? WHERE id = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if (getById(id).isPresent()) {
            preparedStatement = connection.prepareStatement("DELETE Product WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        try {
            Objects.requireNonNull(product);
        } catch (NullPointerException ex) {
            return false;
        }
        if (getById(product.getId()).isPresent()) {
            preparedStatement = connection.prepareStatement("DELETE Product WHERE id = ?");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } else
            return false;
    }

    @Override
    public void releaseConnection() {
        dbConnection.releaseConnection(connection);
    }
}
