<%@ page import="Model.AdminLogin" %>
<%@ page import="Model.Coupon" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.concurrent.CopyOnWriteArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: evgeni
  Date: 1/1/2015
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<%
    //All Coupons In DB to Admin , to make change in them
    List<Coupon> coupons = (List<Coupon>)request.getAttribute("ListOfAllCouponsInDB");
%>
<div class="container">
  <a href="AddNewCoupon.jsp">Add New Coupon</a>
</div>
<div class="container">
  <form action="/MainController"  method="get" >
    <input type="hidden" name="JspName" value="LogOut">
    <input type="submit" value="LogOut">
  </form>
</div>
<%
    //If There was A problem in Filling Form in Servlet , It Will return here
  String messageCoupon = (String)request.getAttribute("messageCoupon");
  if(messageCoupon != null)
  {
    out.print(messageCoupon);
  }
%>
<div class="container">
    <table class="table table-bordered">
    <thead>
    <th>Id</th>
    <th>Name</th>
    <th>Price</th>
    <th>Category</th>
    <th>Date</th>
    <th>Description</th>
    </thead>
    <tbody>
    <% for(Coupon tempCoupon:coupons)
{
  %>
    <form class="form-control"  action="/MainController" method="post">
      <input type="hidden" name="JspName" value="AdminAction">
       <tr>
           <!-- Get All Coupon Details To The Table -->
         <td><%=tempCoupon.getId()%></td>
         <td><%=tempCoupon.getName()%></td>
         <td><%=tempCoupon.getPrice()%></td>
         <td><%=tempCoupon.getCategory()%></td>
         <td><%=tempCoupon.getDate()%></td>
         <td><%=tempCoupon.getDescription()%></td>
         <td><button type="submit" class="btn btn-info btn-md" name="Button"  value="Update">Update</button></td>
         <td><button  type="submit" class="btn btn-danger btn-md" name="Button"  value="Delete">Delete</button></td>
           <!-- Put id To each item So to choose right coupon after -->
         <input hidden="hidden" value ="<%=tempCoupon.getId()%>" name="id">
        </tr>
    </form>
    <%
      }
    %>
    </tbody>
  </table>
  </form>
</div>
</body>
</html>