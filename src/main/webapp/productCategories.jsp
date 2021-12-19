<%@ page import="ir.man.JavaInWeb.model.ProductCategory" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۱۷/۱۲/۲۰۲۱
  Time: ۰۱:۲۲
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="productCategories" class="java.util.Optional" scope="request"/>
<table border="1" style="border: 1px solid black">
    <thead style="border: 1px solid black">
    <tr>
        <td>Name</td>
        <td>Description</td>
        <td>Actions</td>
    </tr>
    </thead>
    <tbody>
    <%
        Optional<List<ProductCategory>> productCategoryList = (Optional<List<ProductCategory>>) request.getAttribute("productCategories");
        if (productCategoryList.isPresent()) {
            for (ProductCategory productCategory : productCategoryList.get()) {%>
    <jsp:include page="/component/ProductCategory.jsp">
        <jsp:param name="id" value='<%=productCategory.getId()%>'/>
        <jsp:param name="name" value='<%=productCategory.getName()%>'/>
        <jsp:param name="description" value='<%=productCategory.getDescription()%>'/>
    </jsp:include>
    <% }
    } %>
    </tbody>
</table>
</body>
</html>