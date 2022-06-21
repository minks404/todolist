<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Spring Boot!</title>
</head>
<body>
	<p>Welcome to Spring Boot JSP! :)</p>
	
	<p><font color="red">${invalidCredentials}</font></p>

	<form method="post">
		<label>Name:</label> <input type="text" name="name" /> <label>Password:</label>
		<input type="password" name="password" /> <input type="submit"
			value="login" />
	</form>
</body>
</html>