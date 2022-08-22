<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1" content="width=device-width, initial-scale=1">
		
		<title>Java Server Pages</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
		
		<style type="text/css">
		
			body {
				background-image: url("https://cedem.com.br/2017/wp-content/uploads/2019/06/Background-site-04.jpg");	

			}
	
			form {
				position: absolute;
				top: 40%;
				left: 35%;
				color: white;
			}
			
			h1 {
			position: absolute;
			top: 32%;
			left: 33%;
			color: white;
			-webkit-text-stroke-width: 1px;
			-webkit-text-stroke-color: black;
			}
				
			h3{
			position: absolute;
			top: 55%;
			left: 39%;
			color: #ffd9e0;

			}
		
		</style>
	</head>
	
	<body>
	
		<h1>Olá Mundo! Bem vindos ao JSP </h1>
	
	<form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%= request.getParameter("url") %>" name="url">

	<div class="col-md-6">
		<label class="form-label">Login:</label> 
		<input name="Login" type="text" class="form-control" required="required">
		<div class="invalid-feedback">
       		Informe o Login
      </div>
	</div>
	
	<div class="col-md-6">
		<label class="form-label">Senha: </label>
		<input name="Senha" type="password" class="form-control" required="required">	
		<div class="invalid-feedback">
       		Informe a Senha
      	</div>
	</div>
	
		<input type="submit" value="Entrar" class="btn btn-primary">
	
	</form>

<h3>${msg}</h3>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	
<script type="text/javascript">

(function () {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()
		
</script>
</body>
</html>