<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۱۷/۱۲/۲۰۲۱
  Time: ۲۰:۲۷
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="productCategory">
    <label for="name">Name</label><input type="text" id="name" name="name">
    <label for="description">Description</label><input type="text" id="description" name="description">
    <input type="hidden" name="insert">
    <input type="submit" value="add">
</form>
