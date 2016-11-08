<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<form action="/Registration" method="post">

Name:<input type="text" name="name" required/><br>
e-mail:<input type="email" name="email" required/><br>
Password:<input type="password" name="pass" required/>Password should contain 6 letters<br>

<input type="submit" value="submit"/>
<input  type="reset" value="Reset"/>
<a href="/"><input type="button" value="home"/></a>
</form>


</body>
</html>