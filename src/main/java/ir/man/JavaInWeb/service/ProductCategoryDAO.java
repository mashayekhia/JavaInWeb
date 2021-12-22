package ir.man.JavaInWeb.service;

import ir.man.JavaInWeb.interfaces.IConnectionPool;
import ir.man.JavaInWeb.interfaces.IDao;
import ir.man.JavaInWeb.interfaces.implement.ConnectionPool;
import ir.man.JavaInWeb.model.ProductCategory;
import ir.man.JavaInWeb.model.builder.ProductCategoryBuilder;
import sun.font.CreatedFontTracker;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductCategoryDAO implements IDao<ProductCategory> {

    private ConnectionPool dbConnection;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    ///////////////////////////////////////////////////////////
    private long skip;

    public ProductCategoryDAO(IConnectionPool dbConnection) throws SQLException, ClassNotFoundException {
        this.dbConnection = (ConnectionPool) dbConnection;
        this.connection = dbConnection.getConnection();
    }

    @Override
    public Optional<List<ProductCategory>> getAll() throws SQLException {
        // Jdbc
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ProductCategory");
        List<ProductCategory> productCategories = new ArrayList<>();
        while (resultSet.next())
            productCategories.add(ProductCategory.newBuilder().setId(resultSet.getInt("id")).
                    setName(resultSet.getString("name"))
                    .setDescription(resultSet.getString("description")).createProductCategory());

        return Optional.ofNullable(productCategories);
    }

    @Override
    public Optional<ProductCategory> getById(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM ProductCategory WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return Optional.of(ProductCategory.newBuilder().setId(resultSet.getInt("id")).
                    setName(resultSet.getString("name"))
                    .setDescription(resultSet.getString("description")).createProductCategory());
        return Optional.empty();
    }

    @Override
    public boolean insert(ProductCategory productCategory) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO ProductCategory (name)VALUES(?)");
        preparedStatement.setString(1, productCategory.getName());
        preparedStatement.executeUpdate();
        connection.commit();
        return true;
    }

    @Override
    public boolean update(ProductCategory productCategory) throws SQLException {
        try {
            Objects.requireNonNull(productCategory);
        } catch (NullPointerException ex) {
            return false;
        }
        if (getById(productCategory.getId()).isPresent()) {
            preparedStatement = connection.prepareStatement("UPDATE ProductCategory SET name = ? , description = ? WHERE id = ?");
            preparedStatement.setString(1, productCategory.getName());
            preparedStatement.setString(2, productCategory.getDescription());
            preparedStatement.setInt(3, productCategory.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if (getById(id).isPresent()) {
            preparedStatement = connection.prepareStatement("DELETE ProductCategory WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(ProductCategory productCategory) throws SQLException {
        try {
            Objects.requireNonNull(productCategory);
        } catch (NullPointerException ex) {
            return false;
        }
        if (getById(productCategory.getId()).isPresent()) {
            preparedStatement = connection.prepareStatement("DELETE ProductCategory WHERE id = ?");
            preparedStatement.setInt(1, productCategory.getId());
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
