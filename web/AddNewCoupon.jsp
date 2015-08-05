<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.0/themes/base/jquery-ui.css"/>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script src="gmaps-autocomplete-master/js/gmaps.js"></script>
</head>
<body>
<div class="container">
    <form action="/MainController">
        <input type="hidden" name="JspName" value="AdminLogin">
        <button type="submit"  class="btn btn-info btn-md">Back</button>
    </form>
</div>

<%
    //If There was A problem in Filling Form in Servlet , It Will return here
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
<table align="center" bgcolor="#66cdaa" border="3"  width="300" height="300">
    <tr align="center" dir="ltr">
        <form action="/MainController" method="get">
            <input type="hidden" name="JspName" value="AddNewCoupon">
            <tr>
                <td><b>Id:</b></td>
                <td>
                    <input type="text" name="Id">
                </td>
            </tr>
            <tr>
                <td>
                   <b>Name:</b></td>
                <td>
                    </strong> <input type="text" name="Name">
                </td>
            </tr>
            <tr>
                <td><b>Price:</b></td>
                <td>
                    <input type="text" name="Price">
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
                <td><b>Date:</b> </td>
                <td>

                    <input  id="datetimepicker" name="Date"  readonly ><br>
                    <script type="text/javascript" src="Calendar/jquery-ui-timepicker-addon.js"></script>
                        <script type="text/javascript">
                            $(document).ready(function () {
                                $('#datetimepicker').datetimepicker(({ dateFormat: 'dd-mm-yy' })).val()});
                     </script>
                </td>
            </tr>
            <tr>
                <td><b>Description:</b></td>
                <td>
                    <input type="text" name="description">
                </td>
            </tr>
            <tr>
                <td>
                    Location:
                </td>
                <td>
                    <div id='input'>
                        <input id='gmaps-input-address' placeholder='Start typing a place name...' type='text' />
                        <br/>
                        <br/>
                       <input type="hidden" id="gmaps-output-latitude" name="latitude">
                        <br/>
                        <input type="hidden" id="gmaps-output-longitude" name="longitude">
                        <br/>
                        <div id="gmaps-error"></div>
                    </div>
                    <div id="gmaps-canvas"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Add Coupon">
                </td>
            </tr>
        </form>
    </tr>
</table>
</body>
</html>