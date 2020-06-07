<%--
  Created by IntelliJ IDEA.
  User: davidza
  Date: 07/06/2020
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Edit</title>
    <style>
        input[type=text], select {
            width: 50%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type=datetime-local] {
             width: 50%;
             padding: 12px 20px;
             margin: 8px 0;
             display: inline-block;
             border: 1px solid #ccc;
             border-radius: 4px;
             box-sizing: border-box;
         }
        input[type=submit] {
            width: 50%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }
        div {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
    </style>
</head>
<body>
<form method="POST" action='meals' name="add">
    <input type="hidden" name="mealId" value="${meal.id}">
    Description : <br/>
    <label>
        <input type="text" name="description"
            value="<c:out value="${meal.description}" />" />
    </label> <br />
    Date/time : <br/>
    <label>
        <input type="datetime-local" name="dateTime"
            value="<c:out value="${meal.dateTime}" />" />
    </label> <br/>
    Calories : <br/>
    <label>
        <input type="text" name="calories"
            value="<c:out value="${meal.calories}" />" />
    </label> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>
