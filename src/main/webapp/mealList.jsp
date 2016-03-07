<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2>Meal list</h2>

<table border="1" width="303">
    <tr>
        <td width="119"><b>Date and time</b></td>
        <td width="168"><b>Description</b></td>
        <td width="168"><b>Calories</b></td>
        <td width="168"><b>Exceed</b></td>
    </tr>
    <%Iterator itr;%>
    <% List data= (List)request.getAttribute("meals");
        for (itr=data.iterator(); itr.hasNext(); ) {
            UserMealWithExceed userMealWithExceed = (UserMealWithExceed)(itr.next());
            String color =  userMealWithExceed.isExceed()  ? ("red"): ("green");
    %>
    <tr>
        <td width="119" style="color: <%=color%>"><%=userMealWithExceed.getDateTime()%></td>
        <td width="119" style="color: <%=color%>"><%=userMealWithExceed.getCalories()%></td>
        <td width="119" style="color: <%=color%>"><%=userMealWithExceed.getDescription()%></td>
        <td width="119" style="color: <%=color%>"><%=userMealWithExceed.isExceed()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
