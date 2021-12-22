<%@ page import="ir.man.JavaInWeb.model.ProductCategory" %>
<%@ page import="ir.man.JavaInWeb.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۱۹/۱۲/۲۰۲۱
  Time: ۱۹:۳۰
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="products" class="java.util.Optional" scope="request"/>
<jsp:include page="newProduct.jsp">
    <jsp:param name="productCategories" value='${requestScope.productCategories}'/>
</jsp:include>
<table border="1" style="border: 1px solid black">
    <thead style="border: 1px solid black">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>category</td>
        <td>Action</td>
    </tr>
    </thead>
    <tbody>
    <%
        Optional<List<Product>> productList = (Optional<List<Product>>) request.getAttribute("products");
        if (productList.isPresent()) {
            for (Product product : productList.get()) {%>
    <jsp:include page="/component/Product.jsp">
        <jsp:param name="id" value='${product.getId()}'/>
        <jsp:param name="name" value='<%=product.getName()%>'/>
        <jsp:param name="category" value='<%=product.getCategory().getName()%>'/>
    </jsp:include>
    <% }
    } %>
    </tbody>
</table>
</body>
</html>