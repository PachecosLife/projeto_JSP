<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tela de erro</title>
</head>
<body>

	<h1>ERRO, t� tudo errado irm�o, arruma essa poha a�</h1>
	<% 	out.print(request.getAttribute("msg")); %>

</body>
</html>