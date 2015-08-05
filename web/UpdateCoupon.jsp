<%@ page import="Model.Coupon" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <!-- -------------------DatePiker---------------------------- -->
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.0/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="gmaps-autocomplete-master/js/gmaps.js"></script>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <link type="text/css" href="styles/jquery-ui/jquery-ui-1.8.16.custom.css" rel="stylesheet" />

</head>
<body>
<div class="container">
    <form action="/MainController">
        <input type="hidden" name="JspName" value="AdminLogin">
        <button type="submit"  class="btn btn-info btn-md">Back</button>
    </form>
</div>
<%
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
<%
Coupon coupon = (Coupon)request.getAttribute("coupon");
%>
<table align="center" bgcolor="#66cdaa" border="3"  width="300" height="300">
    <tr align="center" dir="ltr">
        <form action="/MainController" method="get">
            <input type="hidden" name="JspName" value="UpdateCoupon">
            <tr>
                <td><b>Id:</b></td>
                <td>
                    <input type="text" name="Id" value="<%=coupon.getId()%>" readonly>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Name:</b></td>
                <td>
                    </strong> <input type="text" name="Name" value="<%=coupon.getName()%>">
                </td>
            </tr>
            <tr>
                <td><b>Price:</b></td>
                <td>
                    <input type="text" name="Price" value="<%=coupon.getPrice()%>">
                </td>
            </tr>
            <tr>
                <td><b>Category:</b></td>
                <td>
                    <select name="Category">
                        <option value="Food">Food</option>
                        <option value="Sport">Sport</option>
                        <option value="Spa">Spa</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><b>Description:</b></td>
                <td>
                    <input type="text" name="description" value="<%=coupon.getDescription()%>">
                </td>
            </tr>
            <tr>
                <td><b>Date:</b> </td>
                <td>
                    <input id="datetimepicker" value="<%=coupon.getDate()%>" name="Date" readonly>
                    <script type="text/javascript" src="Calendar/jquery-ui-timepicker-addon.js"></script>
                    <script type="text/javascript">
                        $(document).ready(function () {
                            $('#datetimepicker').datetimepicker(({ dateFormat: 'dd-mm-yy' })).val()});
                    </script>
                </td>
            </tr>
            <tr>
                <td>
                    Location:
                </td>
                     <td>
                         <input id='gmaps-input-address' placeholder='Start typing a place name...' type='text' />
                         <br/>
                         <br/>
                         <!-- Get Location Data To UpdateCoupon -->
                         <input type="hidden" id="gmaps-output-latitude" name="latitude" value="<%if(coupon.getLatitude()!=null)%><%=coupon.getLatitude()%>">
                         <br/>
                         <input type="hidden" id="gmaps-output-longitude" name="longitude" value="<%if(coupon.getLongitude()!=null)%><%=coupon.getLongitude()%>">
                         </div>
                         <div id="gmaps-canvas"></div>
                         </div>
                     </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Update Coupon" >

                </td>
            </tr>
        </form>
    </tr>
</table>

</body>
</html>