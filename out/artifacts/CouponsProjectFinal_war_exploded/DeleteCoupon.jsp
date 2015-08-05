
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>
   <div class="container">
	   <div class="row vertical-center-row">
		   <div class="col">
				<form action="/MainController" method="GET">
					<h3>Are you sure?</h3>
					<button class='btn btn-danger btn-md' type="submit" value="delete">Delete</button>
					<input type="hidden" name="JspName" value="DeleteCoupon">
					<!-- TO get A right a coupon there is hidden value of id coupon -->
					<input type="hidden" value="<%=(String)request.getAttribute("id")%>" name="id">
				</form>
				<form action="/MainController" method="GET">
					<input type="hidden" name="JspName" value="CheckLogin">
					<button class='btn  btn-md' type="submit" value="delete">Cancel</button>
				</form>
			   </div>
		   </div>
   </div>
</body>
</html>
