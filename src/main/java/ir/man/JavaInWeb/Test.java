package ir.man.JavaInWeb;

import ir.man.JavaInWeb.interfaces.implement.ConnectionPool;
import ir.man.JavaInWeb.model.ProductCategory;
import ir.man.JavaInWeb.service.ProductCategoryDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Test {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

//        ConnectionPool connection = ConnectionPool.connectionPoolInstantiate
//                ("jdbc:sqlserver://DESKTOP-GCBNIVU\\INS2014ENT;databaseName=Products", "sqluser", "123");
//        ProductCategoryDAO p = new ProductCategoryDAO(connection);
//        System.out.println("Count : " + p.getAll().get().size());
//        //System.out.println("Count : " + p.getById(5).get());
//        System.out.println(p.delete(4));
//       // p.insert(new ProductCategory("Play"));
//        p.releaseConnection();
    }

    //Employee newEmp = new Employee("Aghil","Mashayekhi",154451);
    //System.out.println(dao1.insert(newEmp));
}
