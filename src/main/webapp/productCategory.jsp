<%@ page import="ir.man.JavaInWeb.model.ProductCategory" %>
<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۱۷/۱۲/۲۰۲۱
  Time: ۰۴:۵۹
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% String action = request.getAttribute("action").toString(); %>
<% ProductCategory productCategory = (ProductCategory) request.getAttribute("productCategory"); %>
<form method="post" action="productCategories">
    <table>
        <thead>
        <tr>
            <td>Name</td>
            <td>Description</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <%if (action.equals("edit")) {%>
                <input type="text" value='<%=productCategory.getName()%>' name="name">
                <%} else {%>
                <label><%=productCategory.getName()%>
                </label>
                <%}%>
            </td>
            <td>
                <%if (action.equals("edit")) {%>
                <input type="text" value='<%=productCategory.getDescription()%>' name="description">
                <%} else {%>
                <label><%=productCategory.getDescription()%>
                </label>
                <%}%>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" value=<%=productCategory.getId()%> name="id">
    <input type="hidden" value=<%=action%> name="action">
    <input type="submit" value=<%=action%>>
</form>
</body>
</html>
