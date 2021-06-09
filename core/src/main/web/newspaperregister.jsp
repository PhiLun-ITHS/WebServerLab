
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Insert title here</title>
</head>
<body>
<div align="center">
  <h1>Add article form</h1>
  <form action="<%= request.getContextPath() %>/register" method="post">
    <table style="with: 80%">
      <tr>
        <td>First Name</td>
        <td><input type="text" name="article" /></td>
      </tr>
      <tr>
        <td>Last Name</td>
        <td><input type="text" name="text" /></td>
      </tr>
    </table>
    <input type="submit" value="Submit" />
  </form>
</div>
</body>
</html>