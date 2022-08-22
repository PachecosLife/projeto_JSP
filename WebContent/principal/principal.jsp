<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<!DOCTYPE html>
<html lang="en">

<!--  CABEÇALHO  -->
<jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <!-- THEME LOADER -->
 <jsp:include page="theme-loader.jsp"></jsp:include>
  

  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      
      			<!-- NAV BAR -->
          <jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
                  <!-- MAIN MENU -->
                  <jsp:include page="mainmenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      
                      <!-- PAGE HEADER -->
                      <jsp:include page="page-header.jsp"></jsp:include>
                      
                    
                        <div class="pcoded-inner-content">
                            
                            <div class="main-body">
                                <div class="page-wrapper">
                                
                                    <!-- PAGE BODY START -->
                                    <div class="page-body">
                                        <div class="row">
                                            
                                          <div class="card">
                                                <div class="card-header">
                                                    <h3>Apresentação</h3>
                                                </div>
                                                <div class="card-block">
                                                    <!-- Row start -->
                                                    <div class="row">
                                                        <div class="col-lg-12 col-xl-6">
                                                            <div class="sub-title">Me chamo Fellipe e aqui eu apresento meu primeiro filho/projeto desenvolvido 100% em JAVA.</div>
															<ul class="nav nav-tabs  tabs" role="tablist">
																<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#home1" role="tab">O Projeto</a></li>
																<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#profile1" role="tab">Tecnologias</a> </li>
															</ul>
															<!-- Tab panes -->
                                                            <div class="tab-content tabs card-block">
                                                                <div class="tab-pane active" id="home1" role="tabpanel">
                                                                    <h5 class="m-0"> Uma aplicação desenvolvida visando a simplicidade sem perder a qualidade. Aqui você vai cadastrar e consultar funcionários/usuários/produtos, 
                                                                    				emitir e baixar relatório em PDF com as informações cadastradas tendo disponibilidade para impressão.</h5>
                                                                </div>
                                                                <div class="tab-pane" id="profile1" role="tabpanel">
                                                                
                                                                <p class="m-0"> Projeto Maven desenvolvido com IDE Eclipse <br>- PGAdmin III para gerenciar banco de Dados <br>- Servidor Apache Tomcat 10 <br> - Relatórios com iReport e Jasper</p>
                                                                
                                                                    <p class="m-0" style="padding-top: 10%"> <h5>Back-End:</h5> 
                                                                    <br> Arquitetura MVC
                                                                    <br> JSP - Servlets com Java Server Pages  
                                                                    <br> JDBC - Java Database Connectivity
                                                                    <br> Banco de Dados SQL - PostgreSQL
                                                                    <br> AJAX 
                                                                    
                                                                    <p class="m-4"> <h5>Front-End:</h5>
                                                                    <br> HTML e CSS
                                                                    <br> JavaScript
                                                                    <br> Bootstrap 4
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Row end -->
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
</body>

</html>
    