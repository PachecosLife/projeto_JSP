<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

    
<!DOCTYPE html>
<html lang="en">

<!--  CABEÇALHO  -->
<jsp:include page="head.jsp"></jsp:include>

  <body>
  
 <jsp:include page="theme-loader.jsp"></jsp:include>
  
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      
          <jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  
                  <jsp:include page="mainmenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      
                      <jsp:include page="page-header.jsp"></jsp:include>
                      
                    
                        <div class="pcoded-inner-content">
                            
                            <div class="main-body">
                                <div class="page-wrapper">
                                
                                    <!-- PAGE BODY START -->
                                    <div class="page-body">
                                        <div class="row">
                                        
											<div class="col-sm-12">
												<div class="card">
													<div class="card-header">
														<h5>Relatório</h5>
													</div>
														
														<div class="card-block">
															<h4 class="sub-title">Relatório por Datas</h4>
															
														
															<form class="form-material" method="get" id="formUsuario" 
																action="<%=request.getContextPath()%>/ServletUsuarioControle">
																
																<input type="hidden" id="acaoImprimirRelatorio" name="acao" value="imprimirRelatorioUser">
															
																<div class="form-row align-items-center">
																
																    <div class="col-auto">
																      <label class="sr-only" for="dataInicial">Data Inicial</label>
																      <input type="text" class="form-control mb-2" id="dataInicial" name="dataInicial" 
																      placeholder="Data Início" value="${dataInicial}">
																    </div>
																    
																    <div class="col-auto">
																      <label class="sr-only" for="dataFinal">Data Final</label>
																      <div class="input-group mb-2">
																        <input type="text" class="form-control" id="dataFinal" name="dataFinal" 
																        placeholder="Data Final" value="${dataFinal}">
																      </div>
																      
																    </div>
																    <div class="col-auto">
																      <button type="button" onclick="imprimirHTML()" class="btn waves-effect waves-light btn-grd-success">Abrir Relatório</button>
																      <button type="button" onclick="imprimirPDF()" class="btn waves-effect waves-light hor-grd btn-grd-primary">Baixar PDF</button>
																    </div>
																  </div>
																  
															</form>

														<div style="height: 500px; overflow: scroll;">
															<table class="table" id="tabelaresultadosview">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Nome</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${listaUser}" var='ml'>
																		<tr>
																			<td><c:out value="${ml.id}"></c:out></td>
																			<td><c:out value="${ml.nome}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</div>

													</div>
												</div>
											</div>
													
                                          
                                        </div>
                                    </div>
                                    <!-- PAGE BODY END -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    
		<!-- JAVASCRIPT   -->
<jsp:include page="script.jsp"></jsp:include>

<script type="text/javascript">

function imprimirHTML() {
	document.getElementById("acaoImprimirRelatorio").value = 'imprimirRelatorioUser';
	$("#formUsuario").submit();
}

function imprimirPDF() {
	document.getElementById("acaoImprimirRelatorio").value = 'imprimirRelatorioPDF';
    $("#formUsuario").submit();
    return false;
	
}

$( function() {
	  
	  $("#dataInicial").datepicker({
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

$( function() {
	  
	  $("#dataFinal").datepicker({
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
</script>
</body>

</html>
    