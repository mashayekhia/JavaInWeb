<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۱۷/۱۲/۲۰۲۱
  Time: ۲۰:۲۷
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="man" uri="http://man.ir/tags" %>

<man:header title="Products"/>
<form method="post" action="productCategory">
    <label for="name">Name</label><input type="text" id="name" name="name">
    <label>Category</label>
    <man:select name="id" list="${requestScope.productCategories}" labelProperty="name" valueProperty="id" width="150px" />
    <input type="hidden" name="insert">
    <input type="submit" value="add">
</form>
