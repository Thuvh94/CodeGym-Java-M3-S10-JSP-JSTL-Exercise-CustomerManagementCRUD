<%--
  Created by IntelliJ IDEA.
  User: thu
  Date: 11/1/2020
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Display all customers</title>
</head>
<body>
<h1>Customer List</h1>
<table>
    <tr>
<%--        public customer(int id, String name, String address, int age, boolean isMale)--%>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Age</th>
        <th>Sex</th>
    </tr>
    <c:forEach items='${requestScope["customerList"]}' var="customer">
        <tr>
            <td>${customer.getId()}</td>
            <td>${customer.getName()}</td>
            <td>${customer.getAddress()}</td>
            <td>${customer.getAge()}</td>
            <td>
                <c:if test="${customer.isMale()}">
                    Male
                </c:if>
                <c:if test="${!customer.isMale()}">
                    Female
                </c:if>
            </td>
<%--            <c:choose>--%>
<%--                <c:when test="${customer.isMale==true}">--%>
<%--                    <td><c:out value="Male"></c:out></td>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <td><c:out value="Female"></c:out></td>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
