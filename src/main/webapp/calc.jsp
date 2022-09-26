<%--
  Created by IntelliJ IDEA.
  User: ilyan
  Date: 26.09.2022
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calc</title>
</head>
<body>
<form action="max-number" method="POST">
  Number_1: <input name="number_1" />
  <br><br>
  Number_2: <input name="number_2" />
  <br><br>
  Number_2: <input name="number_3" />
  <input type="submit" value="Submit" />
  <br><br>
  Operation: <input type="radio" name="operation" value="max" checked />Max
  <input type="radio" name="operation" value="min" />Min
  <input type="radio" name="operation" value="avg" />AVG
</form>
</body>
</html>
