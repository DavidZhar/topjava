<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.io.IOException" %>
<%@ page import="ru.javawebinar.topjava.web.MealServlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: davidza
  Date: 05/06/2020
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<% response.getWriter().println("test1<br>"); %>
test2 <br>
<%="test3<br>" %>
<%=MealServlet.meals.get(0).toString() %>
<br>
<%=(String) request.getAttribute("meals") %>

<p><%


%></p>

<%--<%--%>
<%--    List<MealTo> meals = (List<MealTo>) request.getAttribute("meals");--%>
<%--    MealServlet.meals.forEach(meal -> {--%>
<%--        try {--%>
<%--            out.(meal.toString());--%>
<%--            out.--%>
<%--        } catch (IOException e) {--%>
<%--            e.printStackTrace();--%>
<%--        }--%>
<%--    }); %>--%>


</body>
</html>
