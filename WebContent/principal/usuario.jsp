<%@page import="model.ModeloLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html>
<html lang="en">

<!-- PÁGINA HEAD  -->
<jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <!-- PÁGINA THEME-LOADER -->
 <jsp:include page="theme-loader.jsp"></jsp:include>
  
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      
   <!-- PÁGINA NAV-BAR -->
  <jsp:include page="navbar.jsp"></jsp:include>
	 	<div class="pcoded-main-container">
	 		<div class="pcoded-wrapper">

  <!-- PÁGINA MAIN MENU -->
  <jsp:include page="mainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
					
  <!-- PÁGINA PAGE-HEADER -->
  <jsp:include page="page-header.jsp"></jsp:include>

						<div class="pcoded-inner-content">
							<div class="main-body">
								<div class="page-wrapper">

									<!-- COMEÇO DO CORPO -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-header">
														<h5>Formulário</h5>
													</div>
													
													<d	iv class="card-block">
														<h4 class="sub-title">Cadastro</h4>

														<!-- FORMULÁRIO DE CADASTRO  -->
														<form class="form-material" enctype="multipart/form-data" action="<%=request.getContextPath()%>/ServletUsuarioControle" 
																method="post" id="formUsuario">
																
															<input type="hidden" name="acao" id="acao" value="">
														
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modolLogin.id}"> 
																<span class="form-bar"></span> 
																<label class="float-label">ID</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="nome" id="nome" class="form-control" required="required" value="${modolLogin.nome}"> 
																<span class="form-bar"></span> 
																<label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="dataNascimento" id="dataNascimento" class="form-control" required="required" value="${modolLogin.dataNascimento}"> 
																<span class="form-bar"></span> 
																<label class="float-label">Data de Nascimento:</label>
															</div>
															<div class="form-group form-default">
																<input type="email" name="email" id="email" class="form-control" required="required" value="${modolLogin.email}"> 
																<span class="form-bar"></span> 
																<label class="float-label">E-mail:</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login" id="login"class="form-control" required="required" value="${modolLogin.login}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Login:</label>
															</div>
															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"class="form-control" required="required"value="${modolLogin.senha}"> 
																<span class="form-bar"></span> 
																<label class="float-label">Senha:</label>
															</div>
															
															<!--  PERFIL DO USUÁRIO  -->
															<div class="form-group row form-default">
																<div class="col-sm-2">
																<select class="custom-select" name="perfil">
																	<option selected>Selecione o setor</option>
																	
																	<option  value="GERENTE" <%
																	ModeloLogin modeloLogin = (ModeloLogin) request.getAttribute("modolLogin");
																	
																	if(modeloLogin != null && modeloLogin.getPerfil().equals("GERENTE")){
																		out.println(" ");
																		out.println("selected=\"selected\"");
																		out.println(" ");
																		} %>	>Gerente</option>
																	
																	<option value="ADMINISTRATIVO" <%
																	modeloLogin = (ModeloLogin) request.getAttribute("modolLogin");
																	
																	if(modeloLogin != null && modeloLogin.getPerfil().equals("ADMINISTRATIVO")){
																		out.println(" ");
																		out.println("selected=\"selected\"");
																		out.println(" ");
																		} %> 	>Administrativo</option>
																	
																	<option value="FINANCEIRO" <%
																	modeloLogin = (ModeloLogin) request.getAttribute("modolLogin");
																	
																	if(modeloLogin != null && modeloLogin.getPerfil().equals("FINANCEIRO")){
																		out.println(" ");
																		out.println("selected=\"selected\"");
																		out.println(" ");
																		} %>	>Financeiro</option>
																</select>
																</div>
																
																<div class="col-sm-2" style="padding: 10px"> 
																	<input type="radio" name="sexo" value="MASCULINO" <%
																		modeloLogin = (ModeloLogin) request.getAttribute("modolLogin");
																	
																	if(modeloLogin != null && modeloLogin.getSexo().equals("MASCULINO")){
																		out.println(" ");
																		out.println("checked=\"checked\"");
																		out.println(" ");
																		}
																	%>>Masculino
																	
																	<input type="radio" name="sexo" value="FEMININO" <%
																		modeloLogin = (ModeloLogin) request.getAttribute("modolLogin");
																	
																	if(modeloLogin != null && modeloLogin.getSexo().equals("FEMININO")){
																		out.println(" ");
																		out.println("checked=\"checked\"");
																		out.println(" ");
																		}
																	
																	%>>Feminino
																
																 </div>
																 
																<!-- FOTO USUÁRIO  -->
																<div class="form-group form-default input-group mb-4" style="padding: 20px">
																	 <div class="input-group-prepend">
																	
																	  <c:if test="${modolLogin.fotoUser != '' && modolLogin.fotoUser != null}">
																		 <img alt="Imagem User" id="fotoembase64" src="${modolLogin.fotoUser}" width="90px">																	  
																	  </c:if>
																	  
																	  <c:if test="${modolLogin.fotoUser == null || modolLogin.fotoUser == ''}">
																	   	 <img alt="Imagem User" id="fotoembase64"  src="assets/images/faq_man.png" width="90px">	
																	  </c:if>		
																	  																
																	</div>
																	<input type="file" class="form-control-file" id="fileFoto" name="fileFoto" style="margin-top: 15px; margin-left: 5px" 
																			accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto')">
																</div>

															</div>
															
															

															<!-- BOTÕES DO FORMULÁRIO -->
															<button type="button" class="btn waves-effect waves-light btn-grd-success" onclick="limparForm()">Novo</button>
															<button type="submit" class="btn waves-effect waves-light btn-grd-primary">Salvar</button>
															<button type="button" class="btn waves-effect waves-light btn-grd-danger" onclick="deleteAjax()">Excluir</button>
															<button type="button" class="btn waves-effect waves-light btn-grd-info" data-toggle="modal" data-target="#usuarioModal">Procurar</button>
														

														</form>
													</div>
												</div>
											</div>
										</div>
											<!-- MENSAGEM QUE CONFIRMA A OPERAÇÃO -->
											<span style="font-size: 18px; color: green;">${msg}</span>
											
										<!-- LISTA DE USUÁRIOS ABAIXO DO FORMULÁRIO -->
										<div>
											<table class="table" id="tabelaResultadoView">
												<thead class="thead-dark">
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Nome</th>
														<th scope="col">Ver</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${modeloLogins}" var="ml">
														<tr>
															<td><c:out value="${ml.id}"></c:out></td>
															<td><c:out value="${ml.nome}"></c:out></td>
															<td><a class="btn btn-success waves-effect waves-light" 
															href="<%= request.getContextPath() %>/ServletUsuarioControle?acao=buscarEditar&id=${ml.id}">Ver</a>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
										
										<!-- PÁGINAÇÃO  -->
										<nav aria-label="Page navigation example">
											<ul class="pagination">
												
												<% int totalPaginas = (int) request.getAttribute("totalPaginas");
													
												for(int p = 0; p < totalPaginas; p++){
													String url = request.getContextPath() + "/ServletUsuarioControle?acao=paginar&pagina=" + (p * 5);
													out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"\">"+(p+1)+"</a></li>");
												}
												
												%>												
											</ul>
										</nav>

									</div>
									<!-- FIM DO CORPO -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
    </div>

	<!--  MODAL / JANELA DE PESQUISA -->
	<div class="modal fade" id="usuarioModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="tituloModal">Usuários</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="input-group mb-3">
						<input type="text" class="form-control"placeholder="Informe o Nome" aria-label="Informe o Nome" aria-describedby="basic-addon2" id="buscarNome">
						<div class="input-group-append">	
							<button type="button" class="btn btn-success waves-effect waves-light" onclick="buscarUsuario();">Pesquisar</button>
						</div>
					</div>
					
				<div>
					<table class="table" id="tabelaResultado">
						<thead class="thead-dark">
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Nome</th>
								<th scope="col">Ver</th>
							</tr>
						</thead>
						<tbody>
							
							
						</tbody>
					</table>
				</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn waves-effect waves-light btn-danger btn-outline-danger" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>


<!-- JAVASCRIPT   -->
<jsp:include page="script.jsp"></jsp:include>
<script type="text/javascript">

var dataNascimento = $("#dataNascimento").val();

var dateFormat = new Date(dataNascimento);

$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR',{timeZone: 'UTC'}));

$("#nome").focus();

$( function() {
	  
	  $("#dataNascimento").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );



function visualizarImg(fotoembase64, filefoto) {
	
    var preview = document.getElementById(fotoembase64);
    var fileUser = document.getElementById(filefoto).files[0];
    var reader = new FileReader();
    
    reader.onloadend = function (){
	    preview.src = reader.result; 
    };
    
    if (fileUser) {
	  reader.readAsDataURL(fileUser);
    }else {
	 preview.src=  '';
    }
    
}

function buscarUsuario() {
    var nomeBusca = document.getElementById('buscarNome').value;
    
    if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){
		var urlAction = document.getElementById('formUsuario').action;
	
		$.ajax({
			method: "get",
			url: urlAction,
			data: "nomeBusca=" + nomeBusca + '&acao=buscarUser', 
			success: function(response) {
				var json = JSON.parse(response);
				 $('#tabelaResultado > tbody > tr').remove();
				 
				 for(var p = 0; p < json.length; p++){
				      $('#tabelaResultado > tbody').append( '<tr> <td>'+json[p].id+'</td> <td> '+json[p].nome+
				   '</td> <td><button type="button" class="btn waves-effect waves-dark btn-info btn-outline-info" onclick="verEditar('+json[p].id+')">Ver</button></td></tr>');
				  }
			}
		}).fail(function(xhr, status, errorThrown){
			alert('Deu ruim' + xrh.responseText);
		});
	
	
    }
    
}

	function verEditar(id) {
		var urlAction = document.getElementById('formUsuario').action;
		
		window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
	}

	function deleteAjax() {
		if (confirm('Deseja realmente excluir?')) {
			var urlAction = document.getElementById('formUsuario').action;
			var idUser = document.getElementById('id').value;
			
			$.ajax({
				method: "get",
				url: urlAction,
				data: "id=" + idUser + '&acao=deletarajax', 
				success: function(response) {
					limparForm();
					alert(response);
				}
			}).fail(function(xhr, status, errorThrown){
				alert('Deu ruim' + xrh.responseText);
			});
		}
	}

	function limparForm() {
		var elementos = document.getElementById('formUsuario').elements;

		for (p = 0; p < elementos.length; p++) {
			elementos[p].value = '';
		}
	}
</script>
</body>

</html>
    