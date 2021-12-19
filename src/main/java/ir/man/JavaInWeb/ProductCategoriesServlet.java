package ir.man.JavaInWeb;

import ir.man.JavaInWeb.interfaces.implement.ConnectionPool;
import ir.man.JavaInWeb.model.ProductCategory;
import ir.man.JavaInWeb.service.ProductCategoryDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Optional;

@WebServlet(name = "ProductCategoriesServlet", urlPatterns = "/productCategories")
public class ProductCategoriesServlet extends HttpServlet {

    ProductCategoryDAO pcDAO;
    ConnectionPool connection = ConnectionPool.connectionPoolInstantiate
            ("jdbc:sqlserver://DESKTOP-GCBNIVU\\INS2014ENT;databaseName=Products", "sqluser", "123");

    @Override
    public void init() throws ServletException {
        super.init();
        if (connection != null) {
            try {
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
                request.setAttribute("productCategories", pcDAO.getAll());
            } catch (SQLException throwables) {
                //////////////////////////////////////////
                throwables.printStackTrace();
            }
            getServletContext().getRequestDispatcher("newProductCategory").forward(request, response);
            //getServletContext().getRequestDispatcher("/productCategories.jsp").forward(request, response);
            return;
        } else if (action.equals("edit") || action.equals("delete")) {
            if (id.matches("\\d+")) {
                int _id = Integer.parseInt(id);
                try {
                    Optional<ProductCategory> productCategory = pcDAO.getById(_id);
                    if (productCategory.isPresent()) {
                        request.setAttribute("productCategory", productCategory.get());
                        request.setAttribute("action", action);
                        System.out.println(productCategory.get().getName());
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/productCategory.jsp");
                        rd.forward(request, response);
                    } else {
                        response.sendRedirect("/productCategories.jsp");
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
        String description = request.getParameter("description");

        try {
            if (action.equals("insert")) {
                pcDAO.insert(new ProductCategory(name, description));
                response.sendRedirect(getServletContext().getContextPath() + "/productCategories");
            } else { //delete
                int id = Integer.parseInt(request.getParameter("id"));
                if (action.equals("edit")) {
                    pcDAO.update(new ProductCategory(id, name, description));
                } else {
                    pcDAO.delete(id);
                }
                response.sendRedirect(getServletContext().getContextPath() + "/productCategories");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return;
        }
    }
}