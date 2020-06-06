<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<%--
  Created by IntelliJ IDEA.
  User: davidza
  Date: 05/06/2020
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Meals</title>
    <style>
        table {
            border-collapse: separate; /* Способ отображения границы */
            width: 100%; /* Ширина таблицы */
        }
        td {
            padding: 12px; /* Поля вокруг текста */
            text-align: left;
        }
        tr:nth-child(even) {background-color: #f2f2f2;}
    </style>
</head>
<body>
<h2><a href="/topjava">Home</a></h2>
<h3 align="center">MEALS</h3>
<br>
<table align="center">
    <tr align="left">
        <th style="padding-left: 12px; padding-top: 12px; padding-bottom: 12px; background-color: forestgreen; color: white;">Date and time</th>
        <th style="padding-left: 12px; padding-top: 12px; padding-bottom: 12px; background-color: forestgreen; color: white;">Description</th>
        <th style="padding-left: 12px; padding-top: 12px; padding-bottom: 12px; background-color: forestgreen; color: white;">Calories</th>
    </tr>
<c:forEach var="meal" items="${meals}">
    <c:if test="${!meal.excess}">
        <tr style="color: red;">
    </c:if>
    <c:if test="${meal.excess}">
        <tr style="color: green;">
    </c:if>
    <javatime:format pattern="dd-MM-yyyy HH:mm" value="${meal.dateTime}" var="date"/>
    <td>${date}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
