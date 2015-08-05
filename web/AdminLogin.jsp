<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: RomaMarg
  Date: 02.01.2015
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>

<% //If There was A problem in Filling Form in Servlet , It Will return here
    List<String> errorMessage= (List<String>)request.getAttribute("errorMessage");
  if(errorMessage != null)
  {
    for(String message : errorMessage)
    {
%>
<br><strong>
  <%
    out.println(message);
  %>
</strong>
<%
    }
  }
%>
<div class="container">
<div class="row vertical-center">
    <div class="col-lg" >
<form action ="/MainController" method="get">
  <table class="tab-content">
    <thead>
    <th></th>
    <th></th>
    </thead>
    <tr>
        <input type="hidden" name="JspName" value="AdminLogin">
    <tr>
          <td>Admin Name:</td>
          <td><input type="text" name="AdminName" class="input-sm"></td>
    </tr>
    <tr>
          <td>Password:</td>
          <td><input type="password" name="Password" class="input-sm"></td>
    </tr>
  </table>
<button class="btn btn-info btn-md">Submit</button>
</form>
    </div>
</div>
</div>
</body>
</html>
