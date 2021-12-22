package ir.man.JavaInWeb;

import ir.man.JavaInWeb.interfaces.implement.ConnectionPool;
import ir.man.JavaInWeb.model.Product;
import ir.man.JavaInWeb.model.ProductCategory;
import ir.man.JavaInWeb.service.ProductCategoryDAO;
import ir.man.JavaInWeb.service.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "ProductsServlet", urlPatterns = "/secured/products")
public class ProductsServlet extends HttpServlet {

    ProductDAO prodDAO;
    ProductCategoryDAO pcDAO;
    ConnectionPool connection = ConnectionPool.connectionPoolInstantiate
            ("jdbc:sqlserver://DESKTOP-GCBNIVU\\INS2014ENT;databaseName=Products", "sqluser", "123");

    @Override
    public void init() throws ServletException {
        super.init();
        if (connection != null) {
            try {
                prodDAO = new ProductDAO(connection);
                pcDAO = new ProductCategoryDAO(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        if (action == null) {
            try {
                request.setAttribute("products", prodDAO.getAll());
                request.setAttribute("productCategories", pcDAO.getAll().get());
            } catch (SQLException throwables) {
                //////////////////////////////////////////
                throwables.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
            return;
        } else if (action.equals("edit") || action.equals("delete")) {
            if (id.matches("\\d+")) {
                int _id = Integer.parseInt(id);
                try {
                    Optional<Product> product = prodDAO.getById(_id);
                    if (product.isPresent()) {
                        request.setAttribute("product", product.get());
                        request.setAttribute("action", action);
                        System.out.println(product.get().getName());
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/products.jsp");
                        rd.forward(request, response);
                    } else {
                        response.sendRedirect("/products.jsp");
                        return;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

//        try {
//            if (action.equals("insert")) {
//                prodDAO.insert(Product.newBuilder().setName(name).setCategory(categoryId).createProduct());
//                response.sendRedirect(getServletContext().getContextPath() + "/productCategories");
//            } else { //delete
//                int id = Integer.parseInt(request.getParameter("id"));
//                if (action.equals("edit")) {
//                    pcDAO.update(new ProductCategory(id, name, description));
//                } else {
//                    pcDAO.delete(id);
//                }
//                response.sendRedirect(getServletContext().getContextPath() + "/productCategories");
//            }
//        } catch (SQLException throwables) {
//            System.out.println(throwables.getMessage());
//            return;
//        }
    }
}