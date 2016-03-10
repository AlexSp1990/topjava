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
        <td>
            <form method="post" enctype="application/x-www-form-urlencoded">
                <dl>
                    <dd><input type="text" name="description"  size=50 value=""></dd>
                </dl>
            </form>
        </td>
        <td width="119" style="color: <%=color%>"><%=userMealWithExceed.isExceed()%></td>
        <td>
            <a href="meals?id=<%=userMealWithExceed.getId()%>&action=delete">
                <button type="submit">Delete</button>
            </a>
        </td>
    </tr>
    <%}%>
</table>

<form id="resume" method="post" enctype="application/x-www-form-urlencoded">
    <dl>
        <dt>Date time:</dt>
        <dd><input type="datetime" name="datetime" id="datetime" size=50 value=""></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd><input type="number" name="calories" id="calories" size=50 value=""></dd>
    </dl>
    <dl>
        <dt>Description:</dt>
        <dd><input type="text" name="description" id="description" size=50 value=""></dd>
    </dl>
    <button type="submit"><%-- onclick="return validate()--%>Save</button>
</form>
</body>
</html>
