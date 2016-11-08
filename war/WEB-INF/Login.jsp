<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<center>${message}</center>

<form action="/LoginValidate" method="post">
E-mail:<input type="email" name="email" required/><br>
Password:<input type="password" name="password" required/><br>
<input type="submit" value="Login"/>
<input type="reset" value="Reset"/><br><br><br>
<a href="/RegisterPage">New user click to register</a>

</form>




</body>
</html>