package ir.man.JavaInWeb;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/secured/*" , dispatcherTypes = DispatcherType.REQUEST)  //or servletName={"servlet1","servlet2"}
public class SecurityFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession() != null && req.getSession().getAttribute("man_seq_data") != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
