<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Name Updation</title>
</head>
<body>

<form action="/NameChange" method="post">
Enter the name<input type="text" name="name" required/><br>
<input type="submit" value="change"/>
<input type="reset" value="reset"/>



</form>
<a href="/Home"><button>Home</button></a>

</body>
</html>