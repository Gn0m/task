<%--
  Created by IntelliJ IDEA.
  User: ilyan
  Date: 26.09.2022
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<form action="calculator" method="POST">
    Number_1: <input name="number_1"/>
    <br><br>
    Number_2: <input name="number_2"/>
    <br><br>
    Operation: <select name="operation">
    <option>+</option>
    <option>-</option>
    <option>/</option>
    <option>*</option>
    <option>^</option>
    <option>%</option>
</select>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
