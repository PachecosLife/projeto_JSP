<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tela de erro</title>
</head>
<body>

	<h1>ERRO, tá tudo errado irmão, arruma essa poha aí</h1>
	<% 	out.print(request.getAttribute("msg")); %>

</body>
</html>