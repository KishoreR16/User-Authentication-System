<!DOCTYPE html>
<html>
<head>
<title>WELCOME PAGE</title>
</head>
<body style="background-color:powderblue;">

<%
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    System.out.print("in jsp page i think");
%>
	Welcome 
	<form action="Logout" method="post">
	<input type="submit" value="Logout">
	</form>
	
</body>
</html>

