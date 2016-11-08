<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Updation</title>
</head>
<body>

<form action="/PasswordUpdate" method="post">
Enter your old Password:<input type="password" name="pass" required/><br>
Enter your new Password:<input type="password" name="passnew" required/>Password should contain 6 characters<br>
<input type="submit" value="change"/>
<input type="reset" value="reset"/><br>


</form>
<a href="/Home"><button>Home</button></a>
</body>
</html>