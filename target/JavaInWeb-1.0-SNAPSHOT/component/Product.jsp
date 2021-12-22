<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tr>
    <td><%=request.getParameter("id")%>
    </td>
    <td><%=request.getParameter("name")%>
    </td>
    <td><%=request.getParameter("category")%>
    </td>
    <td>
        <a href=<%=application.getContextPath() + "/product?id=" + request.getParameter("id") + "&action=edit"%>>
            edit </a>
        <a href=<%=application.getContextPath() + "/product?id=" + request.getParameter("id") + "&action=delete"%>>
            delete </a>
    </td>
</tr>
