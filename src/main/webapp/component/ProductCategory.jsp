<%--
  Created by IntelliJ IDEA.
  User: Aghil.Mashayekhi
  Date: ۱۷/۱۲/۲۰۲۱
  Time: ۱۹:۳۴
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tr>
    <td><%=request.getParameter("name")%>
    </td>
    <td><%=request.getParameter("description")%>
    </td>
    <td>
        <a href=<%=application.getContextPath() + "/productCategories?id=" + request.getParameter("id") + "&action=edit"%>>
            edit </a>
        <a href=<%=application.getContextPath() + "/productCategories?id=" + request.getParameter("id") + "&action=delete"%>>
            delete </a>
    </td>
</tr>
