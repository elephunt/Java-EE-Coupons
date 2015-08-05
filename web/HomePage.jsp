<%@ page import="Model.Coupon" %>
<%@ page import="java.util.List" %>
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
    <link rel="stylesheet" href="bootstrap/fonts/glyphicons-halflings-regular.eot">
    <style>
        table{
            width: 75%;
        }
    </style>
</head>
<body>
<%
    // Messages if item added or already in cart
    String messageFromCart = (String)request.getAttribute("messageCart");
    if(messageFromCart !=null)
    {
%>
<br><strong>
    <%
        out.println(messageFromCart);
    %>
</strong>
<%
    }
    //Getin All Coupons From DB
    List<Coupon> coupons = (List<Coupon>)request.getAttribute("ListOfAllCouponsInDB");
%>

<a href="AdminLogin.jsp">Admin Login</a>
<a href="MyCart.jsp" ><span class="glyphicon glyphicon-shopping-cart">Cart</span></a>

<div class="row">
    <div class="col-lg-4">
        <form action="/MainController" class="form-group" method="get">
            <input type="hidden" name="JspName" value="HomePage">
            <select name="Category">
                <option value="Food">Food</option>
                <option value="Sport">Sport</option>
                <option value="Spa">Spa</option>
                <option value="All Coupons">All Coupons</option>
            </select>
                <button class="btn btn-info btn-md">Filter</button>
        </form>
    </div>
</div>
<div class="container">
    <table class="table table-condensed" style="width: 70%" align="center">
        <!-- Get All Coupon Details To The Table -->
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Category</th>
            <th>Date</th>
            <th>Description</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <%
            for(int i=0; i<coupons.size(); i++)
            {
        %>

        <tr>
            <td><%=coupons.get(i).getId()%></td>
            <td><%=coupons.get(i).getName()%></td>
            <td><%=coupons.get(i).getPrice()%></td>
            <td><%=coupons.get(i).getCategory()%></td>
            <td><%=coupons.get(i).getDate()%></td>
            <td><%=coupons.get(i).getDescription()%></td>
            <td>
                <form action="/MainController" method="post">
                    <input type="hidden" name="JspName" value="AddToCart">
                    <!-- TO get A right a coupon there is hidden value of id coupon -->
                    <input hidden="hidden" value ="<%=coupons.get(i).getId()%>" name="id">
                    <input type="submit" class="btn btn-info btn-md" value="Add to cart"/>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>