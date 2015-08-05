<%@ page import="Model.Coupon" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: RomaMarg
  Date: 03.01.2015
  Time: 19:18
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

  <script>
    //auto refreshes
    var auto_refresh = setInterval(function() { submitform(); }, 10000);
    // Form submit function.
    function submitform(){
      // Calling validate function.
              document.getElementById("form").submit();
  }
</script>

<form  action="/MainController" method="get" id="form" >
<input type="hidden" name="JspName" value="CheckValidateCouponsInCart"/>
  </form>
<id class="navbar">
  <div class="container">
    <div class="row">
      <div class="col-sm-1">
        <a href="/MainController" class="btn btn-info btn-md" role="button">Back</a>
        </div>
      </div>
    </div>
</id>
<%! List<Coupon> coupons;%>
<%
  //sorted Coupon In Cart By Category
  coupons =(List<Coupon>)session.getAttribute("SortedCouponsInCart");
  if(coupons != null)
{
  session.removeAttribute("SortedCouponsInCart");
}
  else {
    //All Coupons In Cart
    coupons = (List<Coupon>)session.getAttribute("ListCouponsInCart");
  }
  if(coupons == null)
  {
    out.print("You Have Nothing in your cart,and chinese guy is not happy \n");
  }
  else
  {
%>
<form action="/MainController" method="get">
  <select name="Category">
    <option value="Food">Food</option>
    <option value="Sport">Sport</option>
    <option value="Spa">Spa</option>
    <option value="All Coupons">All Coupons</option>
    <input type="hidden" name="JspName" value="SortCart">
  </select>
  <input type="submit" class="btn btn-info btn-md" value="Filter">
</form>
<div class="container">
  <table class="table table-condensed" style="width: 70%" align="center">
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
    <!-- Get All Coupon Details To The Table -->
    <tr>
      <td><%=coupons.get(i).getId()%></td>
      <td><%=coupons.get(i).getName()%></td>
      <td><%=coupons.get(i).getPrice()%></td>
      <td><%=coupons.get(i).getCategory()%></td>
      <td><%=coupons.get(i).getDate()%></td>
      <td><%=coupons.get(i).getDescription()%></td>
      <td>
        <form action="/MainController" method="post">
          <input type="hidden" name="JspName" value="RemoveFromCart">
          <!-- TO get A right a coupon there is hidden value of id coupon -->
          <input hidden="hidden" value ="<%=coupons.get(i).getId()%>" name="id">
          <input type="submit" class="btn btn-info btn-md" value="Remove"/>
        </form>
      </td>
    </tr>
    <%}%>
    </tbody>
  </table>
</div>
<%}%>
</body>
</html>

